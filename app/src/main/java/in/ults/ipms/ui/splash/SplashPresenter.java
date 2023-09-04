package in.ults.ipms.ui.splash;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class SplashPresenter<V extends ISplashView, I extends ISplashInteractor>
        extends BasePresenter<V, I> implements ISplashPresenter<V, I> {

    @Inject
    public SplashPresenter(I interactor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void runSplashTimer() {
        getCompositeDisposable()
                .add(Completable.timer(2, TimeUnit.SECONDS)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .doFinally(() -> {
                            if (!isViewAttached()) {
                                return;
                             }
                            if(isUserLoggedIn()){
                                getMvpView().goToDashboard();
                            }else {
                                getMvpView().goToAuth();
                            }
                        }).subscribe());
    }
}
