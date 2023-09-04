package in.ults.ipms.ui.dashboard.search.filter;

import javax.inject.Inject;

import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/17.
 */

public class BasicFilterBottomSheetPresenter<V extends IBasicFilterBottomSheetView,
        I extends IBasicFilterBottomSheetInteractor> extends BasePresenter<V, I>
        implements IBasicFilterBottomSheetPresenter<V, I> {

    @Inject
    BasicFilterBottomSheetPresenter(I mvpInteractor,
                                    SchedulerProvider schedulerProvider,
                                    CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }



}
