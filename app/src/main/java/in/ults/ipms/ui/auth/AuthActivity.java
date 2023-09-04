package in.ults.ipms.ui.auth;

import androidx.fragment.app.FragmentTransaction;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.databinding.ActivityAuthBinding;
import in.ults.ipms.ui.auth.forgotpassword.ForgotPasswordFragment;
import in.ults.ipms.ui.auth.login.LoginFragment;
import in.ults.ipms.ui.base.BaseActivity;


public class AuthActivity extends BaseActivity<ActivityAuthBinding> implements IAuthView {
    public static final String TAG = AuthActivity.class.getSimpleName();

    @Inject
    IAuthPresenter<IAuthView, IAuthInteractor> presenter;

    @Override
    protected boolean setTransparentStatusBar() {
        return true;
    }

    @Override
    protected ActivityAuthBinding initViewBinding() {
        return ActivityAuthBinding.inflate(getLayoutInflater());
    }


    @Override
    protected boolean adjustLayoutWithKeyboard() {
        return true;
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
       launchLoginPage(false,false);
    }



    @Override
    public void launchLoginPage(boolean isBackStack, boolean isAnimate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.auth_page_fragment_container, LoginFragment.newInstance(), LoginFragment.TAG).commit();

    }

    @Override
    public void launchForgotPassword(boolean isBackStack, boolean isAnimate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.auth_page_fragment_container, ForgotPasswordFragment.newInstance(), ForgotPasswordFragment.TAG).commit();

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finishAffinity();
        } else {
            super.onBackPressed();
        }

    }
}
