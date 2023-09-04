package in.ults.ipms.ui.waterbody;

import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.databinding.ActivityWaterBodyBinding;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.waterbody.home.WaterBodyHomeFragment;
import in.ults.ipms.ui.waterbody.pond.PondDetailsFragment;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.views.adaptivestate.AdaptiveStateConstants;


public class WaterBodyActivity extends BaseActivity<ActivityWaterBodyBinding> implements IWaterBodyView {
    public static final String TAG = WaterBodyActivity.class.getSimpleName();

    @Inject
    IWaterBodyPresenter<IWaterBodyView, IWaterBodyInteractor> presenter;

    @Override
    protected ActivityWaterBodyBinding initViewBinding() {
        return ActivityWaterBodyBinding.inflate(getLayoutInflater());
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
        presenter.fetchWaterBodyDropDown();
    }

    @Override
    protected void configureToolbar() {
        setSupportActionBar(getViewBinding().waterBodyPageToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        getViewBinding().waterBodyPageToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public void setToolbarTitle(String toolbarTitle) {
        getViewBinding().waterBodyPageToolbar.setTitle(toolbarTitle != null ? CommonUtils.capitalize(toolbarTitle) : "");
    }

    @Override
    public void showToolbar() {
        getViewBinding().waterBodyPageToolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideToolbar() {
        getViewBinding().waterBodyPageToolbar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void launchWaterBodyHome(boolean isBackStack, boolean isAnimate) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.water_body_page_fragment_container, WaterBodyHomeFragment.newInstance(), WaterBodyHomeFragment.TAG).commit();

    }

    @Override
    public void launchPondDetails(boolean isBackStack, boolean isAnimate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.water_body_page_fragment_container, PondDetailsFragment.newInstance(), PondDetailsFragment.TAG).commit();

    }

    @Override
    public void onWaterBodyInternetError() {
        getAdaptiveStateLayout().showState(AdaptiveStateConstants.ADAPTIVE_STATE_NO_NETWORK);
    }

    @Override
    public void onWaterBodyAPIError() {
        getAdaptiveStateLayout().showState(AdaptiveStateConstants.ADAPTIVE_STATE_SERVER_ERROR);
    }

    @Override
    public void onWaterBodyDataSuccess() {
        if (getIntent().getExtras() != null) {
            String layer = getIntent().getExtras().getString(AppConstants.KEY_LAYER_TYPE);
            switch (layer) {
                case AppConstants.LAYER_TYPE_POND:
                    launchPondDetails(false, false);
                    break;
                default:
                    launchWaterBodyHome(false, false);
                    break;
            }
        } else {
            launchWaterBodyHome(false, false);
        }
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
                presenter.fetchWaterBodyDropDown();
            } else {
                super.onInternetConnectionFailure();
            }
        } else if (buttonId == R.id.btnTryAgainServerError) {
            presenter.fetchWaterBodyDropDown();
        }
    }
}
