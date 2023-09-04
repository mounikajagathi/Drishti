package in.ults.ipms.ui.utility.home;

import javax.inject.Inject;

import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class UtilityHomePresenter<V extends IUtilityHomeView, I extends IUtilityHomeInteractor>
        extends BasePresenter<V, I> implements IUtilityHomePresenter<V, I> {


    @Inject
    public UtilityHomePresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
    }

}
