package in.ults.ipms.ui.dashboard.search;

import javax.inject.Inject;

import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class SearchPresenter<V extends ISearchView, I extends ISearchInteractor>
        extends BasePresenter<V, I> implements ISearchPresenter<V, I> {

    @Inject
    public SearchPresenter(I interactor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void searchData(String searchFilter, String searchValue) {
        getCompositeDisposable()
                .add(getInteractor()
                        .searchData(searchFilter, searchValue)
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
                                getMvpView().onSearchSuccess(response.getData());
                            }
                        }).subscribe());
    }

    @Override
    public void searchDetails(String searchFilter, String searchValue) {
        System.out.println("$$$--"+searchFilter+searchValue);
        getCompositeDisposable()
                .add(getInteractor()
                        .searchDetails(searchFilter, searchValue)
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
                            if(response.isSuccess()) {
                                if (response.getData().getFeatureIdType() != null &&
                                        response.getData() != null && response.getData().getFeatures() != null &&
                                        response.getData().getFeatures().size() > 0) {
                                    getMvpView().onSearchDetailsSuccess(response.getData().getFeatureIdType(), response.getData().getFeatures());
                                }
                            }
                        }).subscribe());
    }
}
