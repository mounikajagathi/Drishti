package in.ults.ipms.ui.auth.login;


import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

@PerFragment
public interface ILoginPresenter<V extends ILoginView, I extends ILoginInteractor> extends IBasePresenter<V,I> {

    void loginUser(String userName,String password,boolean rememberMe);

    void checkStoredCredentials();

}
