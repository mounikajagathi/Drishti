package in.ults.ipms.ui.buildingassets.home;

import javax.inject.Inject;

import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class BuildingAssetHomePresenter<V extends IBuildingAssetHomeView, I extends IBuildingAssetHomeInteractor>
        extends BasePresenter<V, I> implements IBuildingAssetHomePresenter<V, I> {


    @Inject
    public BuildingAssetHomePresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getBuildingAssetSpinnerData() {
        if (AppCacheData.getOurInstance().getBuildingAssetSpinnerData() == null) {
            getCompositeDisposable()
                    .add(getInteractor()
                            .getBuildingAssetSpinnerData()
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
                                    if (response.getData() != null) {
                                        AppCacheData.getOurInstance().setBuildingAssetSpinnerData(response.getData());
                                        getMvpView().showBuildingAssetList();
                                    }

                                }
                            }).subscribe());
        } else {
            getMvpView().showBuildingAssetList();
        }
    }
}
