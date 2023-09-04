package in.ults.ipms.ui.dashboard.layers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.esri.arcgisruntime.layers.Layer;
import com.esri.arcgisruntime.layers.WmsLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.DashboardLayersExpandableListAdapter;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.databinding.BottomSheetLayersBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseBottomSheet;
import in.ults.ipms.utils.AppConstants;

/**
 * Created by Mohammed Shafeeq on 21/03/17.
 */

@SuppressWarnings("SuspiciousListRemoveInLoop")
public class LayersBottomSheet extends BaseBottomSheet<BottomSheetLayersBinding> implements ILayersBottomSheetView {

    public static final String TAG = LayersBottomSheet.class.getSimpleName();

    @Inject
    ILayersBottomSheetPresenter<ILayersBottomSheetView, ILayersBottomSheetInteractor> presenter;

    @Inject
    DashboardLayersExpandableListAdapter expandableListAdapter;

    private ArcGISMap arcGISMap;

    @Override
    protected BottomSheetLayersBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return BottomSheetLayersBinding.inflate(inflater, container, attachToParent);
    }

    public void setArcGISMap(ArcGISMap arcGISMap) {
        this.arcGISMap = arcGISMap;
    }


    @Override
    protected void initInjector() {
        getBottomSheetComponent().inject(this);
    }

    @Override
    protected void attachPresenter() {
        presenter.onAttach(this);
    }

    @Override
    protected void detachPresenter() {
        presenter.onDetach();
    }

    public static LayersBottomSheet newInstance() {
        LayersBottomSheet bottomSheet = new LayersBottomSheet();
        bottomSheet.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomSheetDialogStyle);
        return bottomSheet;
    }

    @Override
    protected void init(View view) {
        getViewBinding().includeDial0gHeader.txtDialogTitle.setText(R.string.dashboard_layers);
        getViewBinding().includeDial0gHeader.btnCloseDialog.setOnClickListener(view1 -> dismiss());
        if (AppCacheData.getOurInstance().getDashboardDetails().getLocalBody() != null &&
                AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getLayerCategory() != null) {
            if (AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getLayerCategory().size() > 0) {
                getViewBinding().txtNoData.setVisibility(View.GONE);
            }
            getViewBinding().expandableLayerListView.setAdapter(expandableListAdapter);
            expandableListAdapter.setExpandableListView(getViewBinding().expandableLayerListView);
            expandableListAdapter.setMainList(AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getLayerCategory());
        }
    }


    @Override
    protected void setUp(View view) {

        expandableListAdapter.setLayerAddRemoveClickListener(new DashboardLayersExpandableListAdapter.LayerAddRemoveClickListener() {

            @Override
            public void onAddSingleLayer(String keySelected, String layerCategory, DashboardResponse.LayerCategoryChild selectedLayer) {
                try {
                    if (arcGISMap != null) {
                        showProgressDialog();
                        List<String> baseLayerName = new ArrayList<>();
                        String[] name = selectedLayer.getLayerName().split(":");
                        baseLayerName.add(name.length > 0 ? name[name.length - 1] : "");
                        String baseLayerUrl = selectedLayer.getUrl() + (name.length > 0 ? name[0] : "") + "/wms?TILED=true";
                        WmsLayer baseLayer = new WmsLayer(baseLayerUrl, baseLayerName);
                        baseLayer.setOpacity(1);
                        baseLayer.setId(AppConstants.BASE_MAP_SUB_LAYERS + "_" + selectedLayer.getLabel());
                        baseLayer.setName(selectedLayer.getLabel());
                        if(selectedLayer.getCqlFilter()!=null) {
                            baseLayer.getCustomParameters().put("cql_filter", selectedLayer.getCqlFilter());
                        }
                        JSONObject job = new JSONObject();
                        job.put(AppConstants.KEY_LAYER_CATEGORY_NAME, layerCategory);
                        job.put(AppConstants.KEY_LAYER_NAME, selectedLayer.getLabel());
                        job.put(AppConstants.KEY_GEO_LAYER_NAME, selectedLayer.getLayerName());
                        job.put(AppConstants.KEY_LAYER_STYLE, selectedLayer.getStyle());
                        job.put(AppConstants.KEY_URL, selectedLayer.getUrl());
                        baseLayer.setDescription(job.toString());
                        arcGISMap.getOperationalLayers().add(baseLayer);
                        baseLayer.addDoneLoadingListener(() -> {
                            if (baseLayer.getLoadStatus() == LoadStatus.LOADED) {
                                if (selectedLayer.getStyle() != null && selectedLayer.getStyle().length() > 0) {
                                    baseLayer.getSublayers().get(0).setCurrentStyle(selectedLayer.getStyle());
                                }
                                showToast(R.string.layers_adding_success);
                            } else {
                                showToast(R.string.layers_adding_failure);
                                AppCacheData.getOurInstance().getSelectedDashboardLayerChild().remove(keySelected);
                                arcGISMap.getOperationalLayers().remove(baseLayer);
                                if (expandableListAdapter != null) {
                                    expandableListAdapter.notifyDataSetChanged();
                                }
                            }
                            hideProgressDialog();
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    hideProgressDialog();
                }
            }

            @Override
            public void onRemoveSingleLayer(String layerCategory, DashboardResponse.LayerCategoryChild selectedLayer) {
                try {
                    String layerId = AppConstants.BASE_MAP_SUB_LAYERS + "_" + selectedLayer.getLabel();
                    if (arcGISMap != null) {
                        for (int i = 0; i < arcGISMap.getOperationalLayers().size(); i++) {
                            if (arcGISMap.getOperationalLayers().get(i).getId().equals(layerId)) {
                                arcGISMap.getOperationalLayers().remove(i);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onRemoveAllLayers(String layerCategory, ArrayList<DashboardResponse.LayerCategoryChild> allLayers) {
                try {
                    ArrayList<String> allIds = new ArrayList<>();
                    for (DashboardResponse.LayerCategoryChild layer : allLayers) {
                        allIds.add(AppConstants.BASE_MAP_SUB_LAYERS + "_" + layer.getLabel());
                    }
                    if (arcGISMap != null) {
                        ArrayList<Layer> layers = new ArrayList<>();
                        for (Layer layer : arcGISMap.getOperationalLayers()) {
                            if (allIds.contains(layer.getId())) {
                                layers.add(layer);
                            }
                        }
                        if (layers.size() > 0) {
                            arcGISMap.getOperationalLayers().removeAll(layers);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });


    }

}