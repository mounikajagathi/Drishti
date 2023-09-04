package in.ults.ipms.ui.dashboard.opacity;

import javax.inject.Inject;

import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/17.
 */

public class OpacityBottomSheetPresenter<V extends IOpacityBottomSheetView,
        I extends IOpacityBottomSheetInteractor> extends BasePresenter<V, I>
        implements IOpacityBottomSheetPresenter<V, I> {

    @Inject
    OpacityBottomSheetPresenter(I mvpInteractor,
                                SchedulerProvider schedulerProvider,
                                CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }



}
