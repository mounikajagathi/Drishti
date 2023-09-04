package in.ults.ipms.ui.dashboard.measure;
import javax.inject.Inject;

import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/17.
 */

public class MeasureDialogPresenter<V extends IMeasureDialogView,
        I extends IMeasureDialogInteractor> extends BasePresenter<V, I>
        implements IMeasureDialogPresenter<V, I> {

    private final BaseActivity baseActivity;


    @Inject
    MeasureDialogPresenter(I mvpInteractor,
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
