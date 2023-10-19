package in.ults.ipms.ui.mapoverlays;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.esri.arcgisruntime.layers.WmsLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.MapOverlayAdapter;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.databinding.BottomSheetMapOverlayBinding;
import in.ults.ipms.ui.base.BaseBottomSheet;
import in.ults.ipms.utils.AppConstants;

/**
 * Created by Mohammed Shafeeq on 21/03/17.
 */

@SuppressWarnings("SuspiciousListRemoveInLoop")
public class MapOverlayBottomSheet extends BaseBottomSheet<BottomSheetMapOverlayBinding> implements IMapOverlayBottomSheetView {

    public static final String TAG = MapOverlayBottomSheet.class.getSimpleName();

    @Inject
    IMapOverlayBottomSheetPresenter<IMapOverlayBottomSheetView, IMapOverlayBottomSheetInteractor> presenter;

    @Inject
    MapOverlayAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;

    private ArcGISMap arcGISMap;

    Set<String> selectedLayersID;

    ArrayList<DashboardResponse.LayerCategoryChild> layerList;

    @Override
    protected BottomSheetMapOverlayBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return BottomSheetMapOverlayBinding.inflate(inflater, container, attachToParent);
    }

    public void setArcGISMap(ArcGISMap arcGISMap) {
        this.arcGISMap = arcGISMap;
    }


    public void setSelectedLayersIDs(Set<String> selectedLayersID) {
        this.selectedLayersID = selectedLayersID;
    }

    public void setLayerList(ArrayList<DashboardResponse.LayerCategoryChild> layerList) {
        this.layerList = layerList;
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

    public static MapOverlayBottomSheet newInstance() {
        MapOverlayBottomSheet bottomSheet = new MapOverlayBottomSheet();
        bottomSheet.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomSheetDialogStyle);
        return bottomSheet;
    }

    @Override
    protected void init(View view) {
        getViewBinding().includeDial0gHeader.txtDialogTitle.setText(R.string.dashboard_layers);
        getViewBinding().includeDial0gHeader.btnCloseDialog.setOnClickListener(view1 -> dismiss());
        if (layerList != null && layerList.size() > 0) {
            getViewBinding().txtNoData.setVisibility(View.GONE);
        }
        getViewBinding().rvMapOverlay.setLayoutManager(layoutManager);
        adapter.setLocalDataSet(layerList);
        adapter.setSelectedMapOverlay(selectedLayersID);
        getViewBinding().rvMapOverlay.setAdapter(adapter);
    }


    @Override
    protected void setUp(View view) {

        adapter.setMapOverlayAddRemoveClickListener(new MapOverlayAdapter.MapOverlayAddRemoveClickListener() {
            @Override
            public void onAddSingleLayer(String keySelected, DashboardResponse.LayerCategoryChild selectedLayer) {
                try {
                    if (arcGISMap != null) {
                        showProgressDialog();
                        String layerType = selectedLayer.getLayerType();
                        List<String> baseLayerName = new ArrayList<>();
                        String baseLayerUrl = selectedLayer.getUrl() + "/wms";
                        String[] name = selectedLayer.getLayerName().split(":");
                        if (name.length == 2) {
                            baseLayerName.add(name[1]);
                            if (!selectedLayer.getUrl().contains(name[0])) {
                                baseLayerUrl = selectedLayer.getUrl() + name[0] + "/wms";
                            }
                        }else{
                            baseLayerName.add(selectedLayer.getLayerName());
                        }
                        WmsLayer baseLayer = new WmsLayer(baseLayerUrl, baseLayerName);
                        baseLayer.setOpacity(0.6f);
                        baseLayer.setId(AppConstants.BASE_MAP_SUB_LAYERS + "_" + selectedLayer.getLabel());
                        baseLayer.setName(selectedLayer.getLabel());
                        if(selectedLayer.getCqlFilter()!=null) {
                            baseLayer.getCustomParameters().put("cql_filter", selectedLayer.getCqlFilter());
                        }
                        if(layerType.equalsIgnoreCase("base_layer")){
                            arcGISMap.getBasemap().getBaseLayers().add(baseLayer);
                        }else{
                            arcGISMap.getOperationalLayers().add(baseLayer);
                        }
                        baseLayer.addDoneLoadingListener(() -> {
                            if (baseLayer.getLoadStatus() == LoadStatus.LOADED) {
                                if (selectedLayer.getStyle() != null && selectedLayer.getStyle().length() > 0) {
                                    baseLayer.getSublayers().get(0).setCurrentStyle(selectedLayer.getStyle());
                                }
                                if (selectedLayersID != null) {
                                    selectedLayersID.add(keySelected);
                                }
                                if (adapter != null) {
                                    adapter.notifyDataSetChanged();
                                }
                                showToast(R.string.layers_adding_success);
                            } else {
                                showToast(R.string.layers_adding_failure);
                                if (selectedLayersID != null) {
                                    selectedLayersID.remove(keySelected);
                                }
                                if(layerType.equalsIgnoreCase("base_layer")){
                                    arcGISMap.getBasemap().getBaseLayers().remove(baseLayer);
                                }else{
                                    arcGISMap.getOperationalLayers().remove(baseLayer);
                                }
                                if (adapter != null) {
                                    adapter.notifyDataSetChanged();
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
            public void onRemoveSingleLayer(String keySelected, DashboardResponse.LayerCategoryChild selectedLayer) {
                try {
                    String layerId = AppConstants.BASE_MAP_SUB_LAYERS + "_" + selectedLayer.getLabel();
                    String layerType = selectedLayer.getLayerType();
                    if (arcGISMap != null) {
                        if(layerType.equalsIgnoreCase("base_layer")){
                            for (int i = 0; i < arcGISMap.getBasemap().getBaseLayers().size(); i++) {
                                if (arcGISMap.getBasemap().getBaseLayers().get(i).getId().equals(layerId)) {
                                    arcGISMap.getBasemap().getBaseLayers().remove(i);
                                }
                            }
                        }else{
                            for (int i = 0; i < arcGISMap.getOperationalLayers().size(); i++) {
                                if (arcGISMap.getOperationalLayers().get(i).getId().equals(layerId)) {
                                    arcGISMap.getOperationalLayers().remove(i);
                                }
                            }
                        }

                        if (selectedLayersID != null) {
                            selectedLayersID.remove(keySelected);
                        }
                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

}