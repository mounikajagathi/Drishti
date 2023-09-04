package in.ults.ipms.ui.dashboard.search;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import javax.inject.Inject;

import in.ults.ipms.adapters.SearchAdapter;
import in.ults.ipms.adapters.SearchDetailsAdapter;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.data.network.model.response.SearchDetailsResponse;
import in.ults.ipms.databinding.ActivitySearchBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.dashboard.search.filter.BasicFilterBottomSheet;
import in.ults.ipms.utils.common.CommonUtils;


public class SearchActivity extends BaseActivity<ActivitySearchBinding> implements ISearchView, SearchAdapter.SearchResultClickListener, SearchDetailsAdapter.SearchResultClickListener {
    public static final String TAG = SearchActivity.class.getSimpleName();


    @Inject
    ISearchPresenter<ISearchView, ISearchInteractor> presenter;

    String selectedFilterId;

    @Inject
    SearchAdapter searchAdapter;

    @Inject
    SearchDetailsAdapter searchDetailsAdapter;

    @Inject
    LinearLayoutManager searchLayoutManager;

    public static final int SEARCH_ACTIVITY_FOR_RESULT = 786;


    @Override
    protected boolean setTransparentStatusBar() {
        return true;
    }

    @Override
    protected boolean hideNavigationIcons() {
        return true;
    }

    @Override
    protected boolean setDarkStatusBarIcon() {
        return true;
    }

    @Override
    protected ActivitySearchBinding initViewBinding() {
        return ActivitySearchBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initInjector() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void attachPresenter() {
        presenter.onAttach(this);
    }

    @Override
    protected void detachPresenter() {
        presenter.onDetach();
    }


    @Override
    protected void init() {
        getViewBinding().rootSearchActivity.setPadding(0, CommonUtils.getStatusBarHeight(this), 0, 0);
        getViewBinding().includeSearchView.searchView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int radius = getViewBinding().includeSearchView.searchView.getHeight();
                getViewBinding().includeSearchView.searchView.setRadius((float) radius);
                getViewBinding().includeSearchView.searchView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        getViewBinding().includeSearchView.searchTextView.setVisibility(View.INVISIBLE);
        getViewBinding().includeSearchView.searchEditText.setVisibility(View.VISIBLE);
        getViewBinding().includeSearchView.searchIcon.setVisibility(View.INVISIBLE);
        getViewBinding().includeSearchView.backIcon.setVisibility(View.VISIBLE);
        getViewBinding().includeSearchView.btnSearchFilter.setVisibility(View.VISIBLE);
        getViewBinding().includeSearchView.searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (getViewBinding().includeSearchView.searchEditText.length() > 0) {
                    getViewBinding().includeSearchView.btnSearchFilter.setVisibility(View.INVISIBLE);
                    getViewBinding().includeSearchView.btnSearchClear.setVisibility(View.VISIBLE);
                } else {
                    getViewBinding().includeSearchView.btnSearchFilter.setVisibility(View.VISIBLE);
                    getViewBinding().includeSearchView.btnSearchClear.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getViewBinding().rvSearch.setLayoutManager(searchLayoutManager);
        getViewBinding().rvSearch.setAdapter(searchAdapter);
        searchAdapter.setSearchResultClickListener(this);
        getViewBinding().rvSearchDetails.setLayoutManager(new LinearLayoutManager(this));
        getViewBinding().rvSearchDetails.setAdapter(searchDetailsAdapter);
        searchDetailsAdapter.setSearchResultClickListener(this);
    }

    @Override
    protected void setUp() {
        super.setUp();
        DashboardResponse.BasicDetails data = AppCacheData.getOurInstance().getDashboardDetails();
        if (data != null && data.getLocalBody() != null
                && data.getLocalBody().getSearchAttributes() != null
                && data.getLocalBody().getSearchAttributes().size() > 0) {
            selectedFilterId = data.getLocalBody().getSearchAttributes().get(0).getColumnName();
            getViewBinding().includeSearchView.backIcon.setOnClickListener(v -> {
                        if (getViewBinding().rvSearchDetails.getVisibility() == View.VISIBLE) {
                            getViewBinding().rvSearch.setVisibility(View.VISIBLE);
                            getViewBinding().rvSearchDetails.setVisibility(View.GONE);
                        } else {
                            onBackPressed();
                        }
                    }
            );
            getViewBinding().includeSearchView.btnSearchFilter.setOnClickListener(view -> {
                BasicFilterBottomSheet bfSheet = BasicFilterBottomSheet.newInstance();
                bfSheet.setSelectedFilter(selectedFilterId);
                bfSheet.setSearchFilterListener(selectedFilter -> selectedFilterId = selectedFilter);
                bfSheet.show(getSupportFragmentManager(), BasicFilterBottomSheet.TAG);
            });

            getViewBinding().includeSearchView.btnSearch.setOnClickListener(view -> {
                String searchValue = getViewBinding().includeSearchView.searchEditText.getText().toString();
                if (searchValue.length() > 0) {
                    hideKeyboard();
                    presenter.searchData(selectedFilterId, searchValue);
                }
            });

            getViewBinding().includeSearchView.btnSearchClear.setOnClickListener(view -> {
                getViewBinding().includeSearchView.searchEditText.setText("");
                if(searchAdapter!=null){
                    searchAdapter.clearData();
                }
                if(searchDetailsAdapter!=null){
                    searchDetailsAdapter.clearData();
                }
                getViewBinding().rvSearch.setVisibility(View.VISIBLE);
                getViewBinding().rvSearchDetails.setVisibility(View.INVISIBLE);
            });

            getViewBinding().includeSearchView.searchEditText.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // Your piece of code on keyboard search click
                    getViewBinding().includeSearchView.btnSearch.performClick();
                    return true;
                }
                return false;
            });
        }
    }

    @Override
    public void onSearchSuccess(ArrayList<String> data) {
            if (data != null && data.size() > 0) {
                getViewBinding().includeSearchError.searchErrorRootView.setVisibility(View.GONE);
                getViewBinding().rvSearch.setVisibility(View.VISIBLE);
            } else {
                getViewBinding().includeSearchError.searchErrorRootView.setVisibility(View.VISIBLE);
                getViewBinding().rvSearch.setVisibility(View.INVISIBLE);
            }
            searchAdapter.setLocalDataSet(data);
            searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResultClick(String itemName) {
            presenter.searchDetails(selectedFilterId, itemName);
    }

    @Override
    public void onSearchDetailsSuccess(String featureType, ArrayList<SearchDetailsResponse.Features> properties) {
        searchDetailsAdapter.setLocalDataSet(properties, featureType);
        searchDetailsAdapter.notifyDataSetChanged();
        getViewBinding().rvSearch.setVisibility(View.INVISIBLE);
        getViewBinding().rvSearchDetails.setVisibility(View.VISIBLE);

    }

    @Override
    public void onResultDetailsClick(String resultId) {
        Intent intent = new Intent();
        intent.putExtra("search_result_id", resultId);
        setResult(SEARCH_ACTIVITY_FOR_RESULT, intent);
        finish();
    }
}
