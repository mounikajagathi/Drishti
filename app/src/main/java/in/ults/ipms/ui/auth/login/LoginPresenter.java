package in.ults.ipms.ui.auth.login;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.request.LoginRequest;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class LoginPresenter<V extends ILoginView, I extends ILoginInteractor>
        extends BasePresenter<V, I> implements ILoginPresenter<V, I> {

    private final BaseActivity baseActivity;


    @Inject
    LoginPresenter(I interactor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(interactor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }


    @Override
    public void loginUser(String userName, String password, boolean rememberMe) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(userName)) {
            getMvpView().showLoginFieldError(LoginFragment.ERROR_TYPE_USERNAME,
                    baseActivity.getResources().getString(R.string.err_username));
            return;
        }
        if (CommonUtils.isNullString(password)) {
            getMvpView().showLoginFieldError(LoginFragment.ERROR_TYPE_PASSWORD,
                    baseActivity.getResources().getString(R.string.err_password));
            return;
        }
        loggingUser(userName, password, rememberMe);
    }


    private void loggingUser(String userName, String password, boolean rememberMe) {
        getCompositeDisposable()
                .add(getInteractor()
                        .loginUser(new LoginRequest(userName, password))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .doOnSubscribe(disposable -> showProgressDialog())
                        .doFinally(this::hideProgressDialog)
                        .doOnError(this::showApiCallErrors)
                        .doOnNext(response -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            if (isResponseSuccess(response)) {
                                getInteractor().getDataManager().setCurrentUserName(response.getData().getBasicDetails().getUserName());
                                getInteractor().getDataManager().setUserAccessId(response.getData().getBasicDetails().getUserAccessId());
                                getInteractor().getDataManager().setUserActiveStatus(response.getData().getBasicDetails().getUserActiveStatus());
                                getInteractor().getDataManager().setAccessToken(response.getData().getToken());
                                getInteractor().getDataManager().setLogUserName(rememberMe ? userName : null);
                                getInteractor().getDataManager().setLogPassword(rememberMe ? password : null);
                                getInteractor().getDataManager().setUserAsLoggedIn();
                                getMvpView().onLoginSuccess(response.getMessage());
                            }
                        }).subscribe());
    }

    @Override
    public void checkStoredCredentials() {
        if (getInteractor().getDataManager().getLogUserName() != null &&
                getInteractor().getDataManager().getLogPassword() != null) {
            getMvpView().setStoredCredentials(
                    getInteractor().getDataManager().getLogUserName(),
                    getInteractor().getDataManager().getLogPassword());
        }
    }
}
