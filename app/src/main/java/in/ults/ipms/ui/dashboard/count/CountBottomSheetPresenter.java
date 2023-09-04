package in.ults.ipms.ui.dashboard.count;

import javax.inject.Inject;

import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/17.
 */

public class CountBottomSheetPresenter<V extends ICountBottomSheetView,
        I extends ICountBottomSheetInteractor> extends BasePresenter<V, I>
        implements ICountBottomSheetPresenter<V, I> {

    @Inject
    CountBottomSheetPresenter(I mvpInteractor,
                              SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }



}
