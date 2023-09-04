package in.ults.ipms.ui.buildingassets.ownerlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.OwnerListAdapter;
import in.ults.ipms.data.network.model.request.DeleteAssetDataRequest;
import in.ults.ipms.databinding.FragmentOwnerListBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseFragment;
import in.ults.ipms.ui.buildingassets.owner.OwnerDetailsFragment;

public class OwnerListFragment extends BaseFragment<FragmentOwnerListBinding> implements IOwnerListView {

    public static final String TAG = OwnerListFragment.class.getSimpleName();


    @Inject
    IOwnerListPresenter<IOwnerListView, IOwnerListInteractor> presenter;

    @Inject
    OwnerListAdapter ownerListAdapter;

    @Inject
    LinearLayoutManager layoutManager;


    public static OwnerListFragment newInstance() {
        return new OwnerListFragment();
    }

    @Override
    protected FragmentOwnerListBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentOwnerListBinding.inflate(inflater, container, attachToParent);
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
        return getString(R.string.owner_list_title);
    }

    @Override
    protected void init(View view) {
        getViewBinding().rvOwnerList.setLayoutManager(layoutManager);
        if(AppCacheData.getOurInstance().getBuildingAssetData()!=null &&
        AppCacheData.getOurInstance().getBuildingAssetData().getOwnerDetails()!=null) {
            ownerListAdapter.setLocalDataSet(AppCacheData.getOurInstance().getBuildingAssetData().getOwnerDetails());
        }
        getViewBinding().rvOwnerList.setAdapter(ownerListAdapter);
        ownerListAdapter.setClickListener((position, v) -> {
            OwnerDetailsFragment.selectedPosition = position;
            getBaseActivity().launchOwnerDetails(true,false);
        });

        ownerListAdapter.setDeleteListener((position, v) -> {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getOwnerDetails() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getOwnerDetails().size() > position) {
                DeleteAssetDataRequest request = new DeleteAssetDataRequest();
                request.setOwnerDetails(new DeleteAssetDataRequest.AssetDeleteDetails(AppCacheData.getOurInstance().getBuildingAssetData()
                        .getOwnerDetails().get(position).getPk()));
                presenter.deleteAssetDetails(-1, request, position);
            }
        });
        getViewBinding().btnAddOwner.setOnClickListener(v -> {
            OwnerDetailsFragment.selectedPosition = -1;
            getBaseActivity().launchOwnerDetails(true,false);
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        if(ownerListAdapter!=null){
            ownerListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshList() {
        if (ownerListAdapter != null) {
            ownerListAdapter.notifyDataSetChanged();
        }
    }
}
