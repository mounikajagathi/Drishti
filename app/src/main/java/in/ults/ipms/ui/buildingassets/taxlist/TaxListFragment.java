package in.ults.ipms.ui.buildingassets.taxlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.TaxListAdapter;
import in.ults.ipms.data.network.model.request.DeleteAssetDataRequest;
import in.ults.ipms.databinding.FragmentTaxListBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseFragment;
import in.ults.ipms.ui.buildingassets.tax.TaxDetailsFragment;

public class TaxListFragment extends BaseFragment<FragmentTaxListBinding> implements ITaxListView {

    public static final String TAG = TaxListFragment.class.getSimpleName();


    @Inject
    ITaxListPresenter<ITaxListView, ITaxListInteractor> presenter;

    @Inject
    TaxListAdapter taxListAdapter;

    @Inject
    LinearLayoutManager layoutManager;


    public static TaxListFragment newInstance() {
        return new TaxListFragment();
    }

    @Override
    protected FragmentTaxListBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentTaxListBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected void initInjector() {
        getFragmentComponent().inject(this);
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
    protected String getToolbarTitle() {
        return getString(R.string.tax_list_title);
    }

    @Override
    protected void init(View view) {
        getViewBinding().rvTaxList.setLayoutManager(layoutManager);
        if(AppCacheData.getOurInstance().getBuildingAssetData()!=null &&
        AppCacheData.getOurInstance().getBuildingAssetData().getTaxDetails()!=null) {
            taxListAdapter.setLocalDataSet(AppCacheData.getOurInstance().getBuildingAssetData().getTaxDetails());
        }
        getViewBinding().rvTaxList.setAdapter(taxListAdapter);
        taxListAdapter.setClickListener((position, v) -> {
            TaxDetailsFragment.selectedPosition = position;
            getBaseActivity().launchTaxDetails(true,false);
        });
        taxListAdapter.setDeleteListener((position, v) -> {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getTaxDetails() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getTaxDetails().size() > position) {
                DeleteAssetDataRequest request = new DeleteAssetDataRequest();
                request.setTaxDetails(new DeleteAssetDataRequest.AssetDeleteDetails(AppCacheData.getOurInstance().getBuildingAssetData()
                        .getTaxDetails().get(position).getPk()));
                presenter.deleteAssetDetails(-1, request, position);
            }
        });
        getViewBinding().btnAddTax.setOnClickListener(v -> {
            TaxDetailsFragment.selectedPosition = -1;
            getBaseActivity().launchTaxDetails(true,false);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(taxListAdapter!=null){
            taxListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshList() {
        if(taxListAdapter!=null){
            taxListAdapter.notifyDataSetChanged();
        }
    }
}
