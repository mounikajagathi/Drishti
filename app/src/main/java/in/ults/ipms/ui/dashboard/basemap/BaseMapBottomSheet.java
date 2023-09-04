package in.ults.ipms.ui.dashboard.basemap;

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
import in.ults.ipms.databinding.BottomSheetBaseMapBinding;
import in.ults.ipms.ui.base.BaseBottomSheet;

/**
 * Created by Mohammed Shafeeq on 21/03/17.
 */

public class BaseMapBottomSheet extends BaseBottomSheet<BottomSheetBaseMapBinding> implements IBaseMapBottomSheetView {

    public static final String TAG = BaseMapBottomSheet.class.getSimpleName();

    private ArcGISMap arcGISMap;

    @Inject
    IBaseMapBottomSheetPresenter<IBaseMapBottomSheetView, IBaseMapBottomSheetInteractor> presenter;


    @Override
    protected BottomSheetBaseMapBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return BottomSheetBaseMapBinding.inflate(inflater, container, attachToParent);
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

    public static BaseMapBottomSheet newInstance() {
        BaseMapBottomSheet bottomSheet = new BaseMapBottomSheet();
        bottomSheet.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomSheetDialogStyle);
        return bottomSheet;
    }

    public void setArcGISMap(ArcGISMap arcGISMap) {
        this.arcGISMap = arcGISMap;
    }

    @Override
    protected void init(View view) {
        getViewBinding().includeDial0gHeader.txtDialogTitle.setText(R.string.dashboard_basemap);
        getViewBinding().includeDial0gHeader.btnCloseDialog.setOnClickListener(view1 -> dismiss());
    }

    @Override
    protected void setUp(View view) {
        if (arcGISMap != null) {
            if (arcGISMap.getBasemap().getBaseLayers().size() > 0) {
                getViewBinding().txtNoData.setVisibility(View.GONE);
                for (Layer baseLayers : arcGISMap.getBasemap().getBaseLayers()) {
                    View seekbarView = LayoutInflater.from(getBaseActivity()).inflate(R.layout.view_opacity_seekbar, null);
                    getViewBinding().baseMapContainers.addView(seekbarView);
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