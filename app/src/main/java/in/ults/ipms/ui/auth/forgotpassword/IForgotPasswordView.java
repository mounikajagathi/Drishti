package in.ults.ipms.ui.auth.forgotpassword;

import in.ults.ipms.ui.base.IBaseView;

/**
 * Created by AmalB on 17/06/21.
 */

public interface IForgotPasswordView extends IBaseView {
    void showForgotPasswordFieldError(String error);
    void clearErrors();
    void onResetPasswordSuccess(String message);
}
