package in.ults.ipms.ui.buildingassets.memberlist;

import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

@PerFragment
public interface IMemberListPresenter<V extends IMemberListView,I extends IMemberListInteractor> extends IBasePresenter<V,I> {
}
