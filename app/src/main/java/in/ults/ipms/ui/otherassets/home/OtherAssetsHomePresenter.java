package in.ults.ipms.ui.otherassets.home;

import javax.inject.Inject;

import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class OtherAssetsHomePresenter<V extends IOtherAssetsHomeView, I extends IOtherAssetsHomeInteractor>
        extends BasePresenter<V, I> implements IOtherAssetsHomePresenter<V, I> {


    @Inject
    public OtherAssetsHomePresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
    }

}
