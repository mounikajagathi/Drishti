package in.ults.ipms.ui.buildingassets.memberlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.MembersListAdapter;
import in.ults.ipms.data.network.model.request.DeleteAssetDataRequest;
import in.ults.ipms.databinding.FragmentMemberListBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseFragment;
import in.ults.ipms.ui.buildingassets.member.MemberDetailsFragment;

public class MemberListFragment extends BaseFragment<FragmentMemberListBinding> implements IMemberListView {

    public static final String TAG = MemberListFragment.class.getSimpleName();


    @Inject
    IMemberListPresenter<IMemberListView, IMemberListInteractor> presenter;

    @Inject
    MembersListAdapter membersListAdapter;


    public static MemberListFragment newInstance() {
        return new MemberListFragment();
    }

    @Override
    protected FragmentMemberListBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentMemberListBinding.inflate(inflater, container, attachToParent);
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
        return getString(R.string.member_list_title);
    }

    @Override
    protected void init(View view) {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getMemberDetails() != null) {
            membersListAdapter.setLocalDataSet(AppCacheData.getOurInstance().getBuildingAssetData().getMemberDetails());
        }
        getViewBinding().rvMemberList.setAdapter(membersListAdapter);
        membersListAdapter.setClickListener((position, v) -> {
            MemberDetailsFragment.selectedPosition = position;
            getBaseActivity().launchMemberDetails(true, false);
        });
        membersListAdapter.setDeleteListener((position, v) -> {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getMemberDetails() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getMemberDetails().size() > position) {
                DeleteAssetDataRequest request = new DeleteAssetDataRequest();
                request.setMemberDetails(new DeleteAssetDataRequest.AssetDeleteDetails(AppCacheData.getOurInstance().getBuildingAssetData()
                        .getMemberDetails().get(position).getPk()));
                presenter.deleteAssetDetails(-1, request, position);
            }
        });
        getViewBinding().btnAddMembers.setOnClickListener(v -> {
            MemberDetailsFragment.selectedPosition = -1;
            getBaseActivity().launchMemberDetails(true, false);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (membersListAdapter != null) {
            membersListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshList() {
        if (membersListAdapter != null) {
            membersListAdapter.notifyDataSetChanged();
        }
    }
}
