package in.ults.ipms.ui.auth.forgotpassword;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.databinding.FragmentForgotPasswordBinding;
import in.ults.ipms.ui.base.BaseFragment;

public class ForgotPasswordFragment extends BaseFragment<FragmentForgotPasswordBinding> implements IForgotPasswordView {

    public static final String TAG = ForgotPasswordFragment.class.getSimpleName();


    @Inject
    IForgotPasswordPresenter<IForgotPasswordView, IForgotPasswordInteractor> presenter;


    public static ForgotPasswordFragment newInstance() {
        return new ForgotPasswordFragment();
    }

    @Override
    protected FragmentForgotPasswordBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentForgotPasswordBinding.inflate(inflater, container, attachToParent);
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
        getViewBinding().txtBackLogin.setPaintFlags(getViewBinding().txtBackLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        getViewBinding().btnResetPassword.setOnClickListener(v -> resetPasswordOnClick());
        getViewBinding().txtBackLogin.setOnClickListener(v -> txtBackLogin());

    }



    void resetPasswordOnClick(){
        hideKeyboard();
        presenter.resetPassword(Objects.requireNonNull(getViewBinding().layoutForgotPasswordMail.getEditText()).getText().toString().trim());
    }


    void txtBackLogin(){
        onFragmentBackPressed();
    }

    @Override
    public void showForgotPasswordFieldError(String error) {
        getViewBinding().layoutForgotPasswordMail.setError(error);
        getViewBinding().layoutForgotPasswordMail.requestFocus();
    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutForgotPasswordMail.setErrorEnabled(false);
    }

    @Override
    public void onResetPasswordSuccess(String message) {
       showDialog(getString(R.string.forgot_password_title), message, getString(R.string.cmn_ok), view -> {
           hideDialog();
           onFragmentBackPressed();
       });
    }
}
