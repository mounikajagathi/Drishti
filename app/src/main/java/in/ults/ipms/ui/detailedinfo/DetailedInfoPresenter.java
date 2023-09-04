package in.ults.ipms.ui.detailedinfo;

import javax.inject.Inject;

import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class DetailedInfoPresenter<V extends IDetailedInfoView, I extends IDetailedInfoInteractor>
        extends BasePresenter<V, I> implements IDetailedInfoPresenter<V, I> {

    @Inject
    public DetailedInfoPresenter(I interactor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

}
