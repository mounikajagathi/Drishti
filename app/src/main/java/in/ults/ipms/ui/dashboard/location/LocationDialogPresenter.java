package in.ults.ipms.ui.dashboard.location;
import javax.inject.Inject;

import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/17.
 */

public class LocationDialogPresenter<V extends ILocationDialogView,
        I extends ILocationDialogInteractor> extends BasePresenter<V, I>
        implements ILocationDialogPresenter<V, I> {

    private final BaseActivity baseActivity;


    @Inject
    LocationDialogPresenter(I mvpInteractor,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }


}
