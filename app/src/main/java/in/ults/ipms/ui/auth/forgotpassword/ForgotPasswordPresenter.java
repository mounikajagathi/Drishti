package in.ults.ipms.ui.auth.forgotpassword;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.request.ForgotPasswordRequest;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class ForgotPasswordPresenter<V extends IForgotPasswordView, I extends IForgotPasswordInteractor>
        extends BasePresenter<V, I> implements IForgotPasswordPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public ForgotPasswordPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    public void resetPassword(String email) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(email)) {
            getMvpView().showForgotPasswordFieldError(baseActivity.getResources().getString(R.string.err_forgot_email));
            return;
        }
        if (!CommonUtils.isValidEmail(email)) {
            getMvpView().showForgotPasswordFieldError(baseActivity.getResources().getString(R.string.invalid_forgot_email));
            return;
        }
        resettingPassword(email);
    }

    public void resettingPassword(String email) {
        getCompositeDisposable().
                add(getInteractor()
                        .resetPassword(new ForgotPasswordRequest(email))
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
                                getMvpView().onResetPasswordSuccess(response.getMessage());
                            }
                        }).subscribe());
    }
}
