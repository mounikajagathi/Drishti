package in.ults.ipms.ui.buildingassets.ownerlist;

import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

@PerFragment
public interface IOwnerListPresenter<V extends IOwnerListView,I extends IOwnerListInteractor> extends IBasePresenter<V,I> {
}
