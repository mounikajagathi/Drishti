package in.ults.ipms.ui.auth.login;


import in.ults.ipms.ui.base.IBaseView;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public interface ILoginView extends IBaseView {
    void showLoginFieldError(int errorType, String error);
    void clearErrors();
    void onLoginSuccess(String message);
    void setStoredCredentials(String username, String password);
}
