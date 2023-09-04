package in.ults.ipms.ui.splash;

import android.content.Intent;

import javax.inject.Inject;

import in.ults.ipms.databinding.ActivitySplashBinding;
import in.ults.ipms.ui.auth.AuthActivity;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.dashboard.DashboardActivity;


public class SplashActivity extends BaseActivity<ActivitySplashBinding> implements ISplashView {
    public static final String TAG = SplashActivity.class.getSimpleName();


    @Inject
    ISplashPresenter<ISplashView, ISplashInteractor> presenter;

    @Override
    protected boolean setFullScreenActivity() {
        return true;
    }

    @Override
    protected boolean setTransparentStatusBar() {
        return true;
    }

    @Override
    protected boolean hideNavigationIcons() {
        return true;
    }

    @Override
    protected ActivitySplashBinding initViewBinding() {
        return ActivitySplashBinding.inflate(getLayoutInflater());
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
        presenter.runSplashTimer();
    }

    @Override
    public void goToDashboard() {
        startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
    }

    @Override
    public void goToAuth() {
        startActivity(new Intent(SplashActivity.this, AuthActivity.class));
    }
}
