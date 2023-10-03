package in.ults.ipms.ui.otherassets;

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

public class OtherAssetsPresenter<V extends IOtherAssetsView, I extends IOtherAssetsInteractor>
        extends BasePresenter<V, I> implements IOtherAssetsPresenter<V, I> {

    @Inject
    public OtherAssetsPresenter(I interactor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void fetchOtherAssetsDropDown() {
        getCompositeDisposable()
                .add(getInteractor()
                        .getOtherAssetsSpinnerResponse()
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
                                AppCacheData.getOurInstance().setOtherAssetsSpinnerData(response.getData());
                                getMvpView().onOtherAssetsDataSuccess();
                            }
                        }).subscribe());
    }

    protected void showUtilityApiCallErrors(Throwable throwable) {
        if (!isViewAttached()) {
            return;
        }
        if (throwable instanceof NoInternetException) {
            getMvpView().onOtherAssetsInternetError();
        } else if (throwable instanceof UnAuthorizedException) {
            logoutUser();
        } else {
            getMvpView().onOtherAssetsAPIError();
        }
    }
}
