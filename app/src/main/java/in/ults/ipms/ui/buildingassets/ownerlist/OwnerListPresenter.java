package in.ults.ipms.ui.buildingassets.ownerlist;

import javax.inject.Inject;

import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class OwnerListPresenter<V extends IOwnerListView, I extends IOwnerListInteractor>
        extends BasePresenter<V, I> implements IOwnerListPresenter<V, I> {


    @Inject
    public OwnerListPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onDeleteAssetSuccess(int type, int position) {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getOwnerDetails() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getOwnerDetails().size() > position) {
            AppCacheData.getOurInstance().getBuildingAssetData().getOwnerDetails().remove(position);
            if(!isViewAttached()){
                return;
            }
            getMvpView().refreshList();
        }
    }
}
