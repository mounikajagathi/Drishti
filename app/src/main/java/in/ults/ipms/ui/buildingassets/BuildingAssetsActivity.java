package in.ults.ipms.ui.buildingassets;

import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.databinding.ActivityBuildingAssetsBinding;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.buildingassets.building.BuildingDetailsFragment;
import in.ults.ipms.ui.buildingassets.establishment.EstablishmentDetailsFragment;
import in.ults.ipms.ui.buildingassets.floorandroof.FloorAndRoofDetailsFragment;
import in.ults.ipms.ui.buildingassets.home.BuildingAssetHomeFragment;
import in.ults.ipms.ui.buildingassets.location.LocationDetailsFragment;
import in.ults.ipms.ui.buildingassets.member.MemberDetailsFragment;
import in.ults.ipms.ui.buildingassets.memberlist.MemberListFragment;
import in.ults.ipms.ui.buildingassets.otherproperty.PropertyOtherDetailsFragment;
import in.ults.ipms.ui.buildingassets.owner.OwnerDetailsFragment;
import in.ults.ipms.ui.buildingassets.ownerlist.OwnerListFragment;
import in.ults.ipms.ui.buildingassets.property.PropertyDetailsFragment;
import in.ults.ipms.ui.buildingassets.road.RoadDetailsFragment;
import in.ults.ipms.ui.buildingassets.survey.SurveyDetailsFragment;
import in.ults.ipms.ui.buildingassets.tax.TaxDetailsFragment;
import in.ults.ipms.ui.buildingassets.taxlist.TaxListFragment;
import in.ults.ipms.ui.buildingassets.tenant.TenantDetailsFragment;
import in.ults.ipms.utils.common.CommonUtils;


public class BuildingAssetsActivity extends BaseActivity<ActivityBuildingAssetsBinding> implements IBuildingAssetsView {
    public static final String TAG = BuildingAssetsActivity.class.getSimpleName();

    @Inject
    IBuildingAssetsPresenter<IBuildingAssetsView, IBuildingAssetsInteractor> presenter;

    @Override
    protected ActivityBuildingAssetsBinding initViewBinding() {
        return ActivityBuildingAssetsBinding.inflate(getLayoutInflater());
    }

    @Override
    protected int getStatusBarColor() {
        return ContextCompat.getColor(this,R.color.colorRed);
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
        super.init();
    }

    @Override
    protected void setUp() {
        super.setUp();
        launchBuildAssetHome(false, false);
    }

    @Override
    protected void configureToolbar() {
        setSupportActionBar(getViewBinding().mainPageToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        getViewBinding().mainPageToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public void setToolbarTitle(String toolbarTitle) {
        getViewBinding().mainPageToolbar.setTitle(toolbarTitle != null ? CommonUtils.capitalize(toolbarTitle) : "");
    }

    @Override
    public void showToolbar() {
        getViewBinding().mainPageToolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideToolbar() {
        getViewBinding().mainPageToolbar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void launchBuildAssetHome(boolean isBackStack, boolean isAnimate) {
        super.launchBuildAssetHome(isBackStack, isAnimate);
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, BuildingAssetHomeFragment.newInstance(), BuildingAssetHomeFragment.TAG).commit();
    }

    @Override
    public void launchBuildingDetails(boolean isBackStack, boolean isAnimate) {
        super.launchBuildingDetails(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, BuildingDetailsFragment.newInstance(), BuildingDetailsFragment.TAG).commit();
    }

    @Override
    public void launchPropertyDetails(boolean isBackStack, boolean isAnimate) {
        super.launchPropertyDetails(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, PropertyDetailsFragment.newInstance(), PropertyDetailsFragment.TAG).commit();
    }

    @Override
    public void launchLocationDetails(boolean isBackStack, boolean isAnimate) {
        super.launchLocationDetails(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, LocationDetailsFragment.newInstance(), LocationDetailsFragment.TAG).commit();
    }

    @Override
    public void launchEstablishmentDetails(boolean isBackStack, boolean isAnimate) {
        super.launchEstablishmentDetails(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, EstablishmentDetailsFragment.newInstance(), EstablishmentDetailsFragment.TAG).commit();

    }

    @Override
    public void launchOwnerDetails(boolean isBackStack, boolean isAnimate) {
        super.launchOwnerDetails(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, OwnerDetailsFragment.newInstance(), OwnerDetailsFragment.TAG).commit();

    }

    @Override
    public void launchPropertyOtherDetails(boolean isBackStack, boolean isAnimate) {
        super.launchPropertyOtherDetails(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, PropertyOtherDetailsFragment.newInstance(), PropertyOtherDetailsFragment.TAG).commit();

    }

    @Override
    public void launchRoadDetails(boolean isBackStack, boolean isAnimate) {
        super.launchRoadDetails(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, RoadDetailsFragment.newInstance(), RoadDetailsFragment.TAG).commit();

    }

    @Override
    public void launchTenantDetails(boolean isBackStack, boolean isAnimate) {
        super.launchTenantDetails(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, TenantDetailsFragment.newInstance(), TenantDetailsFragment.TAG).commit();

    }

    @Override
    public void launchTaxDetails(boolean isBackStack, boolean isAnimate) {
        super.launchTaxDetails(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, TaxDetailsFragment.newInstance(), TaxDetailsFragment.TAG).commit();

    }

    @Override
    public void launchMemberDetails(boolean isBackStack, boolean isAnimate) {
        super.launchMemberDetails(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, MemberDetailsFragment.newInstance(), MemberDetailsFragment.TAG).commit();

    }

    @Override
    public void launchMemberList(boolean isBackStack, boolean isAnimate) {
        super.launchMemberList(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, MemberListFragment.newInstance(), MemberListFragment.TAG).commit();

    }

    @Override
    public void launchSurveyDetails(boolean isBackStack, boolean isAnimate) {
        super.launchSurveyDetails(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, SurveyDetailsFragment.newInstance(), SurveyDetailsFragment.TAG).commit();

    }

    @Override
    public void launchTaxList(boolean isBackStack, boolean isAnimate) {
        super.launchTaxList(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, TaxListFragment.newInstance(), TaxListFragment.TAG).commit();

    }

    @Override
    public void launchOwnerList(boolean isBackStack, boolean isAnimate) {
        super.launchOwnerList(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, OwnerListFragment.newInstance(), OwnerListFragment.TAG).commit();

    }

    @Override
    public void launchFloorAndRoofDetails(boolean isBackStack, boolean isAnimate) {
        super.launchFloorAndRoofDetails(isBackStack, isAnimate);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.main_page_fragment_container, FloorAndRoofDetailsFragment.newInstance(), FloorAndRoofDetailsFragment.TAG).commit();

    }
}
