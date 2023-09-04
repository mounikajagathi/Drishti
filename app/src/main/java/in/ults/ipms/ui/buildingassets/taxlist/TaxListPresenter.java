package in.ults.ipms.ui.buildingassets.taxlist;

import javax.inject.Inject;

import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class TaxListPresenter<V extends ITaxListView, I extends ITaxListInteractor>
        extends BasePresenter<V, I> implements ITaxListPresenter<V, I> {


    @Inject
    public TaxListPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onDeleteAssetSuccess(int type, int position) {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getTaxDetails() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getTaxDetails().size() > position) {
            AppCacheData.getOurInstance().getBuildingAssetData().getTaxDetails().remove(position);
            if(!isViewAttached()){
                return;
            }
            getMvpView().refreshList();
        }
    }

}
