package in.ults.ipms.ui.utility;

import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.GeomPolyLine;
import in.ults.ipms.databinding.ActivityUtilityBinding;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.utility.bridge.BridgeDetailsFragment;
import in.ults.ipms.ui.utility.culvert.CulvertDetailsFragment;
import in.ults.ipms.ui.utility.divider.DividerDetailsFragment;
import in.ults.ipms.ui.utility.drainage.details.DrainageDetailsFragment;
import in.ults.ipms.ui.utility.drainage.map.DrainageMapFragment;
import in.ults.ipms.ui.utility.home.UtilityHomeFragment;
import in.ults.ipms.ui.utility.hump.RoadHumpDetailsFragment;
import in.ults.ipms.ui.utility.junction.RoadJunctionDetailsFragment;
import in.ults.ipms.ui.utility.parking.ParkingDetailsFragment;
import in.ults.ipms.ui.utility.playground.PlaygroundDetailsFragment;
import in.ults.ipms.ui.utility.road.details.RoadDetailsFragment;
import in.ults.ipms.ui.utility.road.map.RoadMapFragment;
import in.ults.ipms.ui.utility.signboard.RoadSignboardDetailsFragment;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.views.adaptivestate.AdaptiveStateConstants;


public class UtilityActivity extends BaseActivity<ActivityUtilityBinding> implements IUtilityView {
    public static final String TAG = UtilityActivity.class.getSimpleName();

    @Inject
    IUtilityPresenter<IUtilityView, IUtilityInteractor> presenter;

    @Override
    protected ActivityUtilityBinding initViewBinding() {
        return ActivityUtilityBinding.inflate(getLayoutInflater());
    }

    @Override
    protected int getStatusBarColor() {
        return ContextCompat.getColor(this, R.color.colorRed);
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
        presenter.fetchUtilityDropDown();
    }

    @Override
    protected void configureToolbar() {
        setSupportActionBar(getViewBinding().roadPageToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        getViewBinding().roadPageToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public void setToolbarTitle(String toolbarTitle) {
        getViewBinding().roadPageToolbar.setTitle(toolbarTitle != null ? CommonUtils.capitalize(toolbarTitle) : "");
    }

    @Override
    public void showToolbar() {
        getViewBinding().roadPageToolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideToolbar() {
        getViewBinding().roadPageToolbar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void launchRoadMap(boolean isBackStack, boolean isAnimate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.road_page_fragment_container, RoadMapFragment.newInstance(), RoadMapFragment.TAG).commit();

    }

    @Override
    public void launchRoadDetails(boolean isBackStack, boolean isAnimate, String length, GeomPolyLine geom) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        RoadDetailsFragment fragment = RoadDetailsFragment.newInstance();
        fragment.setRoadLength(length);
        fragment.setGeom(geom);
        fragmentTransaction.replace(R.id.road_page_fragment_container, fragment, RoadDetailsFragment.TAG).commit();

    }

    @Override
    public void launchUtilityHome(boolean isBackStack, boolean isAnimate) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.road_page_fragment_container, UtilityHomeFragment.newInstance(), UtilityHomeFragment.TAG).commit();

    }

    @Override
    public void launchBridgeDetails(boolean isBackStack, boolean isAnimate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.road_page_fragment_container, BridgeDetailsFragment.newInstance(), BridgeDetailsFragment.TAG).commit();

    }

    @Override
    public void launchCulvertDetails(boolean isBackStack, boolean isAnimate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.road_page_fragment_container, CulvertDetailsFragment.newInstance(), CulvertDetailsFragment.TAG).commit();
    }

    @Override
    public void launchDividerDetails(boolean isBackStack, boolean isAnimate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.road_page_fragment_container, DividerDetailsFragment.newInstance(), DividerDetailsFragment.TAG).commit();

    }

    @Override
    public void launchParkingDetails(boolean isBackStack, boolean isAnimate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.road_page_fragment_container, ParkingDetailsFragment.newInstance(), ParkingDetailsFragment.TAG).commit();
    }

    @Override
    public void launchPlaygroundDetails(boolean isBackStack, boolean isAnimate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.road_page_fragment_container, PlaygroundDetailsFragment.newInstance(), PlaygroundDetailsFragment.TAG).commit();
    }

    @Override
    public void launchRoadHumpMap(boolean isBackStack, boolean isAnimate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.road_page_fragment_container, RoadHumpDetailsFragment.newInstance(), RoadHumpDetailsFragment.TAG).commit();
    }

    @Override
    public void launchRoadJunctionMap(boolean isBackStack, boolean isAnimate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.road_page_fragment_container, RoadJunctionDetailsFragment.newInstance(), RoadJunctionDetailsFragment.TAG).commit();
    }

    @Override
    public void launchRoadSignboardMap(boolean isBackStack, boolean isAnimate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.road_page_fragment_container, RoadSignboardDetailsFragment.newInstance(), RoadSignboardDetailsFragment.TAG).commit();
    }

    @Override
    public void launchDrainageMap(boolean isBackStack, boolean isAnimate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.road_page_fragment_container, DrainageMapFragment.newInstance(), DrainageMapFragment.TAG).commit();

    }

    @Override
    public void launchDrainageDetails(boolean isBackStack, boolean isAnimate, GeomPolyLine geom) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        DrainageDetailsFragment fragment = DrainageDetailsFragment.newInstance();
        fragment.setGeom(geom);
        fragmentTransaction.replace(R.id.road_page_fragment_container, fragment, DrainageDetailsFragment.TAG).commit();

    }

    @Override
    public void onUtilityDataSuccess() {
        if (getIntent().getExtras() != null) {
            String layer = getIntent().getExtras().getString(AppConstants.KEY_LAYER_TYPE);
            switch (layer) {
                case AppConstants.LAYER_TYPE_ROAD:
                    launchRoadMap(false, false);
                    break;
                case AppConstants.LAYER_TYPE_BRIDGE:
                    launchBridgeDetails(false, false);
                    break;
                case AppConstants.LAYER_TYPE_CULVERT:
                    launchCulvertDetails(false, false);
                    break;
                case AppConstants.LAYER_TYPE_DIVIDER:
                    launchDividerDetails(false, false);
                    break;
                case AppConstants.LAYER_TYPE_DRAINAGE:
                    launchDrainageMap(false, false);
                    break;
                case AppConstants.LAYER_TYPE_PLAYGROUND:
                    launchPlaygroundDetails(false, false);
                    break;
                case AppConstants.LAYER_TYPE_PARKING_AREA:
                    launchParkingDetails(false, false);
                    break;
                case AppConstants.LAYER_TYPE_ROAD_HUMP:
                    launchRoadHumpMap(false, false);
                    break;
                case AppConstants.LAYER_TYPE_ROAD_JUNCTION:
                    launchRoadJunctionMap(false, false);
                    break;
                case AppConstants.LAYER_TYPE_ROAD_SIGNBOARD:
                    launchRoadSignboardMap(false, false);
                    break;
                default:
                    launchUtilityHome(false, false);
                    break;
            }
        } else {
            launchUtilityHome(false, false);
        }
    }


    @Override
    public void onUtilityInternetError() {
        getAdaptiveStateLayout().showState(AdaptiveStateConstants.ADAPTIVE_STATE_NO_NETWORK);
    }

    @Override
    public void onUtilityAPIError() {
        getAdaptiveStateLayout().showState(AdaptiveStateConstants.ADAPTIVE_STATE_SERVER_ERROR);
    }

    @Override
    public void showProgressBar() {
        getAdaptiveStateLayout().showState(AdaptiveStateConstants.ADAPTIVE_STATE_PROGRESS_BAR);
    }

    @Override
    public void hideProgressBar() {
        getAdaptiveStateLayout().showActualState();
    }

    @Override
    public void onAdaptiveStateClick(int stateType, int buttonId) {
        if (buttonId == R.id.btnTryAgainInternet) {
            if (isNetworkConnected()) {
                presenter.fetchUtilityDropDown();
            } else {
                super.onInternetConnectionFailure();
            }
        } else if (buttonId == R.id.btnTryAgainServerError) {
            presenter.fetchUtilityDropDown();
        }
    }
}
