package in.ults.ipms.ui.buildingassets.taxlist;

import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

@PerFragment
public interface ITaxListPresenter<V extends ITaxListView,I extends ITaxListInteractor> extends IBasePresenter<V,I> {
}
