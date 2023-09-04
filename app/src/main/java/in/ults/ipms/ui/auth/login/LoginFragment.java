package in.ults.ipms.ui.auth.login;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.ipms.databinding.FragmentLoginBinding;
import in.ults.ipms.ui.base.BaseFragment;
import in.ults.ipms.ui.dashboard.DashboardActivity;

/**
 * Created by Mohammed Shafeeq on 3/20/2018.
 */

public class LoginFragment extends BaseFragment<FragmentLoginBinding> implements ILoginView {
    public static final String TAG = LoginFragment.class.getSimpleName();

    @Inject
    ILoginPresenter<ILoginView, ILoginInteractor> presenter;


    public static final int ERROR_TYPE_USERNAME = 1;
    public static final int ERROR_TYPE_PASSWORD = 2;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    protected FragmentLoginBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentLoginBinding.inflate(inflater, container, attachToParent);
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
    protected void init(View view) {
        super.init(view);
        getViewBinding().btnLogin.setOnClickListener(v -> loginOnClick());
        getViewBinding().txtForgotPassword.setOnClickListener(v -> forgotPasswordOnClick());
    }

    @Override
    protected void setUp(View view) {
        super.setUp(view);
        presenter.checkStoredCredentials();
    }

    @Override
    public void showLoginFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_USERNAME:
                getViewBinding().layoutUserName.setError(error);
                getViewBinding().layoutUserName.requestFocus();
                break;
            case ERROR_TYPE_PASSWORD:
                getViewBinding().layoutPassword.setError(error);
                getViewBinding().layoutPassword.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutUserName.setErrorEnabled(false);
        getViewBinding().layoutPassword.setErrorEnabled(false);
    }

    @Override
    public void onLoginSuccess(String message) {
        showToast(message);
        startActivity(new Intent(getBaseActivity(), DashboardActivity.class));
    }


    private void loginOnClick() {
        hideKeyboard();
        presenter.loginUser(Objects.requireNonNull(getViewBinding().layoutUserName.getEditText()).getText().toString().trim(),
                Objects.requireNonNull(getViewBinding().layoutPassword.getEditText()).getText().toString().trim(),getViewBinding().checkboxRememberMe.isChecked());
    }

    private void forgotPasswordOnClick() {
        getBaseActivity().launchForgotPassword(true,false);
    }


    @Override
    public void setStoredCredentials(String username, String password) {
        Objects.requireNonNull(getViewBinding().layoutUserName.getEditText()).setText(username);
        Objects.requireNonNull(getViewBinding().layoutPassword.getEditText()).setText(password);
        getViewBinding().checkboxRememberMe.setChecked(true);
    }
}
