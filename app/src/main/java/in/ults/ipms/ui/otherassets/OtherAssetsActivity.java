package in.ults.ipms.ui.otherassets;

import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.databinding.ActivityOtherAssetsBinding;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.otherassets.home.OtherAssetsHomeFragment;
import in.ults.ipms.ui.otherassets.mastlight.HighLowMastLightDetailsFragment;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.views.adaptivestate.AdaptiveStateConstants;


public class OtherAssetsActivity extends BaseActivity<ActivityOtherAssetsBinding> implements IOtherAssetsView {
    public static final String TAG = OtherAssetsActivity.class.getSimpleName();

    @Inject
    IOtherAssetsPresenter<IOtherAssetsView, IOtherAssetsInteractor> presenter;

    @Override
    protected ActivityOtherAssetsBinding initViewBinding() {
        return ActivityOtherAssetsBinding.inflate(getLayoutInflater());
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
        presenter.fetchOtherAssetsDropDown();
    }

    @Override
    protected void configureToolbar() {
        setSupportActionBar(getViewBinding().otherAssetsPageToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        getViewBinding().otherAssetsPageToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public void setToolbarTitle(String toolbarTitle) {
        getViewBinding().otherAssetsPageToolbar.setTitle(toolbarTitle != null ? CommonUtils.capitalize(toolbarTitle) : "");
    }

    @Override
    public void showToolbar() {
        getViewBinding().otherAssetsPageToolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideToolbar() {
        getViewBinding().otherAssetsPageToolbar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void launchOtherAssetsHome(boolean isBackStack, boolean isAnimate) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.other_assets_page_fragment_container, OtherAssetsHomeFragment.newInstance(), OtherAssetsHomeFragment.TAG).commit();

    }

    @Override
    public void launchHighLowMastLight(boolean isBackStack, boolean isAnimate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.other_assets_page_fragment_container, HighLowMastLightDetailsFragment.newInstance(), HighLowMastLightDetailsFragment.TAG).commit();
    }


    @Override
    public void onOtherAssetsInternetError() {
        getAdaptiveStateLayout().showState(AdaptiveStateConstants.ADAPTIVE_STATE_NO_NETWORK);
    }

    @Override
    public void onOtherAssetsAPIError() {
        getAdaptiveStateLayout().showState(AdaptiveStateConstants.ADAPTIVE_STATE_SERVER_ERROR);
    }

    @Override
    public void onOtherAssetsDataSuccess() {
        if (getIntent().getExtras() != null) {
            String layer = getIntent().getExtras().getString(AppConstants.KEY_LAYER_TYPE);
            switch (layer) {
                case AppConstants.LAYER_TYPE_HIGH_MAST_LIGHT:
                    launchHighLowMastLight(false, false);
                    break;
                default:
                    launchOtherAssetsHome(false, false);
                    break;
            }
        } else {
            launchOtherAssetsHome(false, false);
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
                presenter.fetchOtherAssetsDropDown();
            } else {
                super.onInternetConnectionFailure();
            }
        } else if (buttonId == R.id.btnTryAgainServerError) {
            presenter.fetchOtherAssetsDropDown();
        }
    }
}
