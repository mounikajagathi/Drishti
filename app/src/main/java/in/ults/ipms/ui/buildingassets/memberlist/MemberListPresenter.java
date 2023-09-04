package in.ults.ipms.ui.buildingassets.memberlist;

import javax.inject.Inject;

import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class MemberListPresenter<V extends IMemberListView, I extends IMemberListInteractor>
        extends BasePresenter<V, I> implements IMemberListPresenter<V, I> {


    @Inject
    public MemberListPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onDeleteAssetSuccess(int type, int position) {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getMemberDetails() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getMemberDetails().size() > position) {
            AppCacheData.getOurInstance().getBuildingAssetData().getMemberDetails().remove(position);
            if(!isViewAttached()){
                return;
            }
            getMvpView().refreshList();
        }
    }
}
