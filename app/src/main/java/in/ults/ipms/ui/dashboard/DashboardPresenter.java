package in.ults.ipms.ui.dashboard;

import java.util.ArrayList;
import java.util.Set;

import javax.inject.Inject;

import in.ults.ipms.data.network.model.request.FeatureDataRequest;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.exceptions.NoInternetException;
import in.ults.ipms.utils.exceptions.UnAuthorizedException;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class DashboardPresenter<V extends IDashboardView, I extends IDashboardInteractor>
        extends BasePresenter<V, I> implements IDashboardPresenter<V, I> {

    @Inject
    public DashboardPresenter(I interactor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void getDashboardDetails() {
        getCompositeDisposable()
                .add(getInteractor()
                        .getDashboardDetails()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .doOnSubscribe(disposable -> showProgressBar())
                        .doOnError(this::showDashboardApiCallErrors)
                        .doOnNext(response -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            getMvpView().hideProgressBar();
                            if (isResponseSuccess(response)) {
                                AppCacheData.getOurInstance().setDashboardDetails(response.getData().getBasicDetails());

                                if (response.getData() != null &&
                                        response.getData().getBasicDetails() != null &&
                                        response.getData().getBasicDetails().getLocalBody() != null) {
                                    AppCacheData.getOurInstance().setImageBaseURL(response.getData().getBasicDetails().getLocalBody().getImageUrl());
                                }
                                getMvpView().setBaseMapAndDefaultCentre();
                            }
                        }).subscribe());
    }

    protected void showDashboardApiCallErrors(Throwable throwable) {
        if (!isViewAttached()) {
            return;
        }
        if (throwable instanceof NoInternetException) {
            getMvpView().onDashboardInternetError();
        } else if (throwable instanceof UnAuthorizedException) {
            logoutUser();
        } else {
            getMvpView().onDashboardAPIError();
        }
    }


    @Override
    public void getFeatureInfoData(Set<String> featureIdSet) {
        ArrayList<FeatureDataRequest> dataRequests = new ArrayList<>();
        for (String data : featureIdSet) {
            dataRequests.add(new FeatureDataRequest(data));
        }
        getCompositeDisposable()
                .add(getInteractor()
                        .getFeatureInfoData(dataRequests)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .doOnSubscribe(disposable -> showProgressDialog())
                        .doFinally(this::hideProgressDialog)
                        .doOnError(throwable -> {
                            showApiCallErrors(throwable);
                            hideProgressDialog();
                        })
                        .doOnNext(response -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            if (isResponseSuccess(response)) {
                                if (response.getData() != null && response.getData().size() > 0) {
                                    getMvpView().onFeatureDetailsFetched(response.getData());
                                }
                            }
                        }).subscribe());
    }
}
