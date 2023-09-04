package in.ults.ipms.ui.utility.drainage.map;

import javax.inject.Inject;

import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.exceptions.NoInternetException;
import in.ults.ipms.utils.exceptions.UnAuthorizedException;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class DrainageMapPresenter<V extends IDrainageMapView, I extends IDrainageMapInteractor>
        extends BasePresenter<V, I> implements IDrainageMapPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public DrainageMapPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void fetchRoadDropDown() {
        getCompositeDisposable()
                .add(getInteractor()
                        .getUtilitySpinnerResponse()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .doOnSubscribe(disposable -> showProgressBar())
                        .doOnError(this::showRoadSpinnerApiCallErrors)
                        .doOnNext(response -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            getMvpView().hideProgressBar();
                            if (isResponseSuccess(response)) {
                                AppCacheData.getOurInstance().setUtilitySpinnerData(response.getData());
                                getMvpView().setBaseMapAndDefaultCentre();
                            }
                        }).subscribe());
    }

    protected void showRoadSpinnerApiCallErrors(Throwable throwable) {
        if (!isViewAttached()) {
            return;
        }
        if (throwable instanceof NoInternetException) {
            getMvpView().onRoadInternetError();
        } else if (throwable instanceof UnAuthorizedException) {
            logoutUser();
        } else {
            getMvpView().onRoadAPIError();
        }
    }
}
