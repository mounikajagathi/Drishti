package in.ults.ipms.ui.waterbody.home;

import javax.inject.Inject;

import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class WaterBodyHomePresenter<V extends IWaterBodyHomeView, I extends IWaterBodyHomeInteractor>
        extends BasePresenter<V, I> implements IWaterBodyHomePresenter<V, I> {


    @Inject
    public WaterBodyHomePresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
    }

}
