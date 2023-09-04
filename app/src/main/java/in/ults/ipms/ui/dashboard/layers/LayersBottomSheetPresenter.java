package in.ults.ipms.ui.dashboard.layers;

import javax.inject.Inject;

import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/17.
 */

public class LayersBottomSheetPresenter<V extends ILayersBottomSheetView,
        I extends ILayersBottomSheetInteractor> extends BasePresenter<V, I>
        implements ILayersBottomSheetPresenter<V, I> {

    @Inject
    LayersBottomSheetPresenter(I mvpInteractor,
                               SchedulerProvider schedulerProvider,
                               CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }



}
