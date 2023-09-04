package in.ults.ipms.ui.mapoverlays;

import javax.inject.Inject;

import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/17.
 */

public class MapOverlayBottomSheetPresenter<V extends IMapOverlayBottomSheetView,
        I extends IMapOverlayBottomSheetInteractor> extends BasePresenter<V, I>
        implements IMapOverlayBottomSheetPresenter<V, I> {

    @Inject
    MapOverlayBottomSheetPresenter(I mvpInteractor,
                                   SchedulerProvider schedulerProvider,
                                   CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }



}
