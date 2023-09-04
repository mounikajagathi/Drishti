package in.ults.ipms.ui.utility.drainage.map;

import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

@PerFragment
public interface IDrainageMapPresenter<V extends IDrainageMapView,I extends IDrainageMapInteractor> extends IBasePresenter<V,I> {

    void fetchRoadDropDown();

}
