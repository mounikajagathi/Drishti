package in.ults.ipms.ui.auth.forgotpassword;

import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

@PerFragment
public interface IForgotPasswordPresenter <V extends IForgotPasswordView,I extends IForgotPasswordInteractor> extends IBasePresenter<V,I> {
    void resetPassword(String email);
}
