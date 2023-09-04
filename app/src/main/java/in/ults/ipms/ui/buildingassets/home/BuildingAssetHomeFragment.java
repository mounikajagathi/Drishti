package in.ults.ipms.ui.buildingassets.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.BuildingAssetHomeAdapter;
import in.ults.ipms.databinding.FragmentBuildingAssetHomeBinding;
import in.ults.ipms.ui.base.BaseFragment;

public class BuildingAssetHomeFragment extends BaseFragment<FragmentBuildingAssetHomeBinding> implements IBuildingAssetHomeView {

    public static final String TAG = BuildingAssetHomeFragment.class.getSimpleName();


    @Inject
    IBuildingAssetHomePresenter<IBuildingAssetHomeView, IBuildingAssetHomeInteractor> presenter;

    @Inject
    BuildingAssetHomeAdapter buildingAssetHomeAdapter;

    @Inject
    GridLayoutManager layoutManager;


    public static BuildingAssetHomeFragment newInstance() {
        return new BuildingAssetHomeFragment();
    }

    @Override
    protected FragmentBuildingAssetHomeBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentBuildingAssetHomeBinding.inflate(inflater, container, attachToParent);
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
        return getString(R.string.building_assets_title);
    }

    @Override
    protected void init(View view) {
        getViewBinding().rvBuildingAssetHome.setLayoutManager(layoutManager);
        getViewBinding().rvBuildingAssetHome.setAdapter(buildingAssetHomeAdapter);
        String[] buildingAssetsArray = getResources().getStringArray(R.array.building_assets);
        buildingAssetHomeAdapter.setLocalDataSet(buildingAssetsArray);
        buildingAssetHomeAdapter.setClickListener((position, v) -> {
            switch (position) {
                case 0:
                    getBaseActivity().launchBuildingDetails(true, false);
                    return;
                case 1:
                    getBaseActivity().launchPropertyDetails(true, false);
                    return;
                case 2:
                    getBaseActivity().launchLocationDetails(true, false);
                    return;
                case 3:
                    getBaseActivity().launchRoadDetails(true, false);
                    return;
                case 4:
                    getBaseActivity().launchEstablishmentDetails(true, false);
                    return;
                case 5:
                    getBaseActivity().launchSurveyDetails(true, false);
                    return;
                case 6:
                    getBaseActivity().launchPropertyOtherDetails(true, false);
                    return;
                case 7:
                    getBaseActivity().launchFloorAndRoofDetails(true, false);
                    return;
                case 8:
                    getBaseActivity().launchTaxList(true, false);
                    return;
                case 9:
                    getBaseActivity().launchTenantDetails(true, false);
                    return;
                case 10:
                    getBaseActivity().launchOwnerList(true, false);
                    return;
                case 11:
                    getBaseActivity().launchMemberList(true, false);
                    return;
            }
        });
    }

    @Override
    protected void setUp(View view) {
        super.setUp(view);
        presenter.getBuildingAssetSpinnerData();
    }

    @Override
    public void showBuildingAssetList() {
        getViewBinding().rvBuildingAssetHome.setVisibility(View.VISIBLE);
    }
}
