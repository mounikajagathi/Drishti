package in.ults.ipms.ui.dashboard.legends;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.esri.arcgisruntime.layers.Layer;
import com.esri.arcgisruntime.mapping.ArcGISMap;

import org.json.JSONObject;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.databinding.BottomSheetLegendsBinding;
import in.ults.ipms.ui.base.BaseBottomSheet;
import in.ults.ipms.utils.AppConstants;

/**
 * Created by Mohammed Shafeeq on 21/03/17.
 */

public class LegendsBottomSheet extends BaseBottomSheet<BottomSheetLegendsBinding> implements ILegendsBottomSheetView {

    public static final String TAG = LegendsBottomSheet.class.getSimpleName();

    private ArcGISMap arcGISMap;

    @Inject
    ILegendsBottomSheetPresenter<ILegendsBottomSheetView, ILegendsBottomSheetInteractor> presenter;


    @Override
    protected BottomSheetLegendsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return BottomSheetLegendsBinding.inflate(inflater, container, attachToParent);
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

    public static LegendsBottomSheet newInstance() {
        LegendsBottomSheet bottomSheet = new LegendsBottomSheet();
        bottomSheet.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomSheetDialogStyle);
        return bottomSheet;
    }

    public void setArcGISMap(ArcGISMap arcGISMap) {
        this.arcGISMap = arcGISMap;
    }


    @Override
    protected void init(View view) {
        getViewBinding().includeDial0gHeader.txtDialogTitle.setText(R.string.dashboard_legends);
        getViewBinding().includeDial0gHeader.btnCloseDialog.setOnClickListener(view1 -> dismiss());
    }


    @Override
    protected void setUp(View view) {
        try {
            if (arcGISMap != null) {
                if (arcGISMap.getOperationalLayers().size() > 0) {
                    for (Layer baseLayers : arcGISMap.getOperationalLayers()) {
                        if (baseLayers.getId().contains(AppConstants.BASE_MAP_SUB_LAYERS)) {
                            getViewBinding().txtNoData.setVisibility(View.GONE);
                            View legendView = LayoutInflater.from(getBaseActivity()).inflate(R.layout.view_legends, null);
                            getViewBinding().legendsContainers.addView(legendView);

                            if (baseLayers.getDescription() != null && baseLayers.getDescription().length() > 0) {
                                JSONObject job = new JSONObject(baseLayers.getDescription());
                                String url = job.getString(AppConstants.KEY_URL);
                                String layerName = job.getString(AppConstants.KEY_LAYER_NAME);
                                String categoryName = job.getString(AppConstants.KEY_LAYER_CATEGORY_NAME);
                                String layerGeoName = job.getString(AppConstants.KEY_GEO_LAYER_NAME);
                                String layerStyle = job.getString(AppConstants.KEY_LAYER_STYLE);
                                TextView title = legendView.findViewById(R.id.txtLegendLayerName);
                                title.setText(String.format("%s : %s", categoryName, layerName));
                                ImageView legend = legendView.findViewById(R.id.imgLegend);
                                String imageUrl = url + "wms?REQUEST=GetLegendGraphic&FORMAT=image/png&LAYER=" + layerGeoName + "&STYLE=" + layerStyle;
                                Glide.with(this)
                                        .load(imageUrl)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .skipMemoryCache(false)
                                        .into(new CustomTarget<Drawable>() {
                                            @Override
                                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                legend.getLayoutParams().width = (int) (((BitmapDrawable) resource).getBitmap().getWidth() * 3);
                                                legend.setImageDrawable(resource);
                                            }

                                            @Override
                                            public void onLoadCleared(@Nullable Drawable placeholder) {

                                            }
                                        });
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}