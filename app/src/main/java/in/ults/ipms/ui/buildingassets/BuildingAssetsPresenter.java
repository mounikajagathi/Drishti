package in.ults.ipms.ui.buildingassets;

import javax.inject.Inject;

import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class BuildingAssetsPresenter<V extends IBuildingAssetsView, I extends IBuildingAssetsInteractor>
        extends BasePresenter<V, I> implements IBuildingAssetsPresenter<V, I> {

    @Inject
    public BuildingAssetsPresenter(I interactor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

}
