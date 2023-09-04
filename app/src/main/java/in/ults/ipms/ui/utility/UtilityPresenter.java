package in.ults.ipms.ui.utility;

import javax.inject.Inject;

import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.exceptions.NoInternetException;
import in.ults.ipms.utils.exceptions.UnAuthorizedException;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class UtilityPresenter<V extends IUtilityView, I extends IUtilityInteractor>
        extends BasePresenter<V, I> implements IUtilityPresenter<V, I> {

    @Inject
    public UtilityPresenter(I interactor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void fetchUtilityDropDown() {
        getCompositeDisposable()
                .add(getInteractor()
                        .getUtilitySpinnerResponse()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .doOnSubscribe(disposable -> showProgressBar())
                        .doOnError(this::showUtilityApiCallErrors)
                        .doOnNext(response -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            getMvpView().hideProgressBar();
                            if (isResponseSuccess(response)) {
                                AppCacheData.getOurInstance().setUtilitySpinnerData(response.getData());
                                getMvpView().onUtilityDataSuccess();
                            }
                        }).subscribe());
    }

    protected void showUtilityApiCallErrors(Throwable throwable) {
        if (!isViewAttached()) {
            return;
        }
        if (throwable instanceof NoInternetException) {
            getMvpView().onUtilityInternetError();
        } else if (throwable instanceof UnAuthorizedException) {
            logoutUser();
        } else {
            getMvpView().onUtilityAPIError();
        }
    }
}
