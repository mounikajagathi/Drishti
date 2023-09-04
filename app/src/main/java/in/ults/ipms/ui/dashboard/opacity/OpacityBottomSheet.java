package in.ults.ipms.ui.dashboard.opacity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.esri.arcgisruntime.layers.Layer;
import com.esri.arcgisruntime.mapping.ArcGISMap;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.databinding.BottomSheetOpacityBinding;
import in.ults.ipms.ui.base.BaseBottomSheet;
import in.ults.ipms.utils.AppConstants;

/**
 * Created by Mohammed Shafeeq on 21/03/17.
 */

public class OpacityBottomSheet extends BaseBottomSheet<BottomSheetOpacityBinding> implements IOpacityBottomSheetView {

    public static final String TAG = OpacityBottomSheet.class.getSimpleName();

    @Inject
    IOpacityBottomSheetPresenter<IOpacityBottomSheetView, IOpacityBottomSheetInteractor> presenter;

    private ArcGISMap arcGISMap;

    @Override
    protected BottomSheetOpacityBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return BottomSheetOpacityBinding.inflate(inflater, container, attachToParent);
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

    public static OpacityBottomSheet newInstance() {
        OpacityBottomSheet bottomSheet = new OpacityBottomSheet();
        bottomSheet.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomSheetDialogStyle);
        return bottomSheet;
    }

    public void setArcGISMap(ArcGISMap arcGISMap) {
        this.arcGISMap = arcGISMap;
    }


    @Override
    protected void init(View view) {
        getViewBinding().includeDial0gHeader.txtDialogTitle.setText(R.string.dashboard_opacity);
        getViewBinding().includeDial0gHeader.btnCloseDialog.setOnClickListener(view1 -> dismiss());
    }


    @Override
    protected void setUp(View view) {
        if (arcGISMap != null) {
            if (arcGISMap.getOperationalLayers().size() > 0) {
                for (Layer baseLayers : arcGISMap.getOperationalLayers()) {
                    if (baseLayers.getId().contains(AppConstants.BASE_MAP_SUB_LAYERS)) {
                        getViewBinding().txtNoData.setVisibility(View.GONE);
                        View seekbarView = LayoutInflater.from(getBaseActivity()).inflate(R.layout.view_opacity_seekbar, null);
                        getViewBinding().opacityContainers.addView(seekbarView);
                        TextView title = seekbarView.findViewById(R.id.txtOpacityLayerName);
                        title.setText(baseLayers.getName());
                        SeekBar opacitySeekBar = seekbarView.findViewById(R.id.seekbarOpacityLayer);
                        opacitySeekBar.setProgress(((int) (baseLayers.getOpacity() * 100)));
                        opacitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                baseLayers.setOpacity((float) seekBar.getProgress() / 100);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });
                    }
                }
            }
        }
    }

}