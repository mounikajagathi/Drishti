package in.ults.ipms.ui.dashboard.search.filter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.BasicSearchFilterAdapter;
import in.ults.ipms.databinding.BottomSheetBasicFilterBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseBottomSheet;

/**
 * Created by Mohammed Shafeeq on 21/03/17.
 */

public class BasicFilterBottomSheet extends BaseBottomSheet<BottomSheetBasicFilterBinding> implements IBasicFilterBottomSheetView {

    public static final String TAG = BasicFilterBottomSheet.class.getSimpleName();

    @Inject
    IBasicFilterBottomSheetPresenter<IBasicFilterBottomSheetView, IBasicFilterBottomSheetInteractor> presenter;

    @Inject
    BasicSearchFilterAdapter basicSearchFilterAdapter;

    @Inject
    GridLayoutManager lmFilter;

    private String selectedFilter = "";

    private BasicSearchFilterAdapter.SearchFilterSelectListener searchFilterListener;



    @Override
    protected BottomSheetBasicFilterBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return BottomSheetBasicFilterBinding.inflate(inflater, container, attachToParent);
    }

    public void setSelectedFilter(String selectedFilter) {
        this.selectedFilter = selectedFilter;
    }

    public void setSearchFilterListener(BasicSearchFilterAdapter.SearchFilterSelectListener searchFilterListener) {
        this.searchFilterListener = searchFilterListener;
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

    public static BasicFilterBottomSheet newInstance() {
        BasicFilterBottomSheet bottomSheet = new BasicFilterBottomSheet();
        bottomSheet.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomSheetDialogStyle);
        return bottomSheet;
    }



    @Override
    protected void init(View view) {
        getViewBinding().includeDial0gHeader.txtDialogTitle.setText(R.string.search_filter_title);
        getViewBinding().includeDial0gHeader.btnCloseDialog.setOnClickListener(view1 -> dismiss());
        getViewBinding().rvSearchFilter.setLayoutManager(lmFilter);
        getViewBinding().rvSearchFilter.setAdapter(basicSearchFilterAdapter);
        basicSearchFilterAdapter.setFilterListener(selectedFilter -> {
            if(searchFilterListener!=null){
                searchFilterListener.onFilterClick(selectedFilter);
            }
        });
        basicSearchFilterAdapter.setLocalDataSet(AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getSearchAttributes(), selectedFilter);
        basicSearchFilterAdapter.notifyDataSetChanged();
    }
}