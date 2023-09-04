package in.ults.ipms.ui.dashboard.count;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.esri.arcgisruntime.layers.Layer;
import com.esri.arcgisruntime.layers.WmsLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.CountMainAdapter;
import in.ults.ipms.adapters.DashboardLayersExpandableListAdapter;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.databinding.BottomSheetCountBinding;
import in.ults.ipms.databinding.BottomSheetLayersBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseBottomSheet;
import in.ults.ipms.utils.AppConstants;

/**
 * Created by Mohammed Shafeeq on 21/03/17.
 */

@SuppressWarnings("SuspiciousListRemoveInLoop")
public class CountBottomSheet extends BaseBottomSheet<BottomSheetCountBinding> implements ICountBottomSheetView {

    public static final String TAG = CountBottomSheet.class.getSimpleName();

    @Inject
    ICountBottomSheetPresenter<ICountBottomSheetView, ICountBottomSheetInteractor> presenter;

    @Inject
    CountMainAdapter countMainAdapter;

    @Override
    protected BottomSheetCountBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return BottomSheetCountBinding.inflate(inflater, container, attachToParent);
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

    public static CountBottomSheet newInstance() {
        CountBottomSheet bottomSheet = new CountBottomSheet();
        bottomSheet.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomSheetDialogStyle);
        return bottomSheet;
    }

    @Override
    protected void init(View view) {
        getViewBinding().includeDial0gHeader.txtDialogTitle.setText(R.string.dashboard_count);
        getViewBinding().includeDial0gHeader.btnCloseDialog.setOnClickListener(view1 -> dismiss());
        if (AppCacheData.getOurInstance().getDashboardDetails().getLocalBody() != null &&
                AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getLayerCategory() != null) {
            if (AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getLayerCategory().size() > 0) {
                getViewBinding().txtNoData.setVisibility(View.GONE);
            }
            getViewBinding().rvCountMain.setLayoutManager(new LinearLayoutManager(getBaseActivity(),LinearLayoutManager.VERTICAL,false));
            getViewBinding().rvCountMain.setAdapter(countMainAdapter);
            countMainAdapter.setLocalDataSet(AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getLayerCategory());
        }
    }

}