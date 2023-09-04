package in.ults.ipms.ui.dashboard.basemap;

import javax.inject.Inject;

import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/17.
 */

public class BaseMapBottomSheetPresenter<V extends IBaseMapBottomSheetView,
        I extends IBaseMapBottomSheetInteractor> extends BasePresenter<V, I>
        implements IBaseMapBottomSheetPresenter<V, I> {

    @Inject
    BaseMapBottomSheetPresenter(I mvpInteractor,
                                SchedulerProvider schedulerProvider,
                                CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }



}
