package in.ults.ipms.ui.base;

import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import in.ults.ipms.data.network.model.base.BaseResponse;
import in.ults.ipms.data.network.model.request.DeleteAssetDataRequest;
import in.ults.ipms.data.network.model.request.FetchBuildingAssetsRequest;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.exceptions.NoInternetException;
import in.ults.ipms.utils.exceptions.UnAuthorizedException;
import in.ults.ipms.utils.file.FileUtils;
import in.ults.ipms.utils.network.ProgressRequestBody;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Mohammed Shafeeq on 3/20/2018.
 */

public class BasePresenter<V extends IBaseView, I extends IBaseInteractor> implements IBasePresenter<V, I> {


    private V mMvpView;
    private I mMvpInteractor;

    private final SchedulerProvider schedulerProvider;
    private final CompositeDisposable compositeDisposable;

    public BasePresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        this.mMvpInteractor = mMvpInteractor;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
        mMvpInteractor = null;
        compositeDisposable.dispose();
    }


    protected SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }


    protected void removeDisposable(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public V getMvpView() {
        return mMvpView;
    }


    protected void showApiCallErrors(Throwable throwable) {
        if (!isViewAttached()) {
            return;
        }
        if (throwable instanceof NoInternetException) {
            getMvpView().onInternetConnectionFailure();
        } else if (throwable instanceof UnAuthorizedException) {
            logoutUser();
        } else {
            System.out.println("@@@@--->"+throwable.getMessage());
            getMvpView().onApiFailure();
        }
        Log.d("API ERROR", new Gson().toJson(throwable));

        Log.d("API ERROR", throwable.getLocalizedMessage());
    }

    protected void hideProgressDialog() {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().hideProgressDialog();
    }

    protected void showProgressDialog() {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showProgressDialog();
    }


    protected void hideProgressBar() {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().hideProgressBar();
    }

    protected void showProgressBar() {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showProgressBar();
    }

    @Override
    public I getInteractor() {
        return mMvpInteractor;
    }

    @Override
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    @Override
    public void checkViewAttached() throws MvpViewNotAttachedException {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(IBaseView) before" +
                    " requesting data to the Presenter");
        }
    }


    protected boolean isResponseSuccess(BaseResponse response) {
        if (response.isSuccess()) {
            return true;
        } else {
            getMvpView().showToast(response.getMessage());
            return false;
        }
    }


    private Observable<Boolean> isObservableResponseSuccess(BaseResponse response) {
        if (response == null || !isViewAttached()) {
            return Observable.just(false);
        } else if (response.isSuccess()) {
            return Observable.just(true);
        } else {
            getMvpView().showToast(response.getMessage());
            return Observable.just(false);
        }
    }

    @Override
    public boolean isUserLoggedIn() {
        return getInteractor().getDataManager().isUserLoggedIn();
    }

    @Override
    public void logoutUser() {
        getInteractor().getDataManager().setUserAsLoggedOut();
        getMvpView().logoutUser();
    }

    private final ProgressRequestBody.UploadCallbacks progressBarListener = new ProgressRequestBody.UploadCallbacks() {
        @Override
        public void onProgressUpdate(int percentage) {
            if (!isViewAttached()) {
                return;
            }
            getMvpView().updateProgressPercent(percentage);
        }

        @Override
        public void onError() {
            if (!isViewAttached()) {
                return;
            }
            getMvpView().hideProgressDialog();
        }

        @Override
        public void onFinish() {
            if (!isViewAttached()) {
                return;
            }
        }
    };

    @Override
    public void uploadImage(String imagePath, String mainPath, String subPath, String localBodyId) {
        File imageFile = new File(imagePath);
        String imageName = subPath + "_" + new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new Date()) + ".jpg";
        getCompositeDisposable().add(getInteractor().updateImage(mainPath, subPath, localBodyId, imageName, new ProgressRequestBody(FileUtils.MIME_TYPE_IMAGE, imageFile, progressBarListener))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnSubscribe(disposable -> showProgressDialog())
                .doFinally(this::hideProgressDialog)
                .doOnError(this::showApiCallErrors)
                .doOnNext(response -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    if (response != null && response.getData() != null &&
                            response.getData().getUrl() != null && response.getData().getUrl().length() > 0) {
                        getMvpView().onImageUploadSuccess(imagePath, subPath, response.getData().getUrl());
                    }
                }).subscribe());
    }


    @Override
    public void fetchBuildAssetData(String id, String app, String layer) {
        getCompositeDisposable()
                .add(getInteractor()
                        .fetchBuildAssetData(new FetchBuildingAssetsRequest(id, app, layer))
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
                                System.out.println("@@@--->"+new Gson().toJson(response));
                                if (app.equalsIgnoreCase(AppConstants.APP_TYPE_BUILDING_ASSET)) {
                                    if (layer.equals(AppConstants.LAYER_TYPE_BUILDING)) {
                                        AppCacheData.getOurInstance().setBuildingDetailsData(response.getData());
                                    } else if (layer.equals(AppConstants.LAYER_TYPE_PROPERTY)) {
                                        AppCacheData.getOurInstance().setBuildingAssetData(response.getData());
                                    }
                                } else if (app.equalsIgnoreCase(AppConstants.APP_TYPE_UTILITY)) {
                                    AppCacheData.getOurInstance().setBuildingAssetData(response.getData());
                                } else if (app.equalsIgnoreCase(AppConstants.APP_TYPE_WATER_BODY)) {
                                    AppCacheData.getOurInstance().setBuildingAssetData(response.getData());
                                } else if (app.equalsIgnoreCase(AppConstants.APP_TYPE_OTHER_ASSET)) {
                                    AppCacheData.getOurInstance().setBuildingAssetData(response.getData());
                                }
                                getMvpView().onFetchAssetSuccess(id, app, layer);
                            }
                        }).subscribe());
    }

    @Override
    public void saveAssetDetails(FetchAssetDataResponse.Data data) {
        Log.d("save", new Gson().toJson(data));
        getCompositeDisposable()
                .add(getInteractor()
                        .saveAssetDetails(data)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .doOnSubscribe(disposable -> showProgressDialog())
                        .doFinally(this::hideProgressDialog)
                        .doOnError(this::showApiCallErrors)
                        .doOnNext(response -> {
                            Log.d("response", new Gson().toJson(response));

                            if (!isViewAttached()) {
                                return;
                            }
                            if (isResponseSuccess(response)) {
                                onSaveAssetSuccess(response);
                             // Log.d("response", new Gson().toJson(response));

                            }
                        }).subscribe());
    }

    @Override
    public void deleteAssetDetails(int type, DeleteAssetDataRequest request, int position) {
        getCompositeDisposable()
                .add(getInteractor()
                        .deleteAssetDetails(request)
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
                                onDeleteAssetSuccess(type, position);
                            }

                        }).subscribe());
    }

    public void onSaveAssetSuccess(FetchAssetDataResponse response) {

    }


    public void onDeleteAssetSuccess(int type, int position) {

    }
}


