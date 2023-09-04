package in.ults.ipms.ui.utility.road.map;

import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

@PerFragment
public interface IRoadMapPresenter<V extends IRoadMapView,I extends IRoadMapInteractor> extends IBasePresenter<V,I> {


}
