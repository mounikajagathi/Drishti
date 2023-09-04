package in.ults.ipms.ui.utility.road.map;

import javax.inject.Inject;

import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class RoadMapPresenter<V extends IRoadMapView, I extends IRoadMapInteractor>
        extends BasePresenter<V, I> implements IRoadMapPresenter<V, I> {


    @Inject
    public RoadMapPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
    }


}
