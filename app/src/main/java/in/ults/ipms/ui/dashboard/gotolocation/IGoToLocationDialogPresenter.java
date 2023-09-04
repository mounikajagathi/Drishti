package in.ults.ipms.ui.dashboard.gotolocation;

import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by Mohammed Shafeeq on 22/03/17.
 */

public interface IGoToLocationDialogPresenter<V extends IGoToLocationDialogView,I extends IGoToLocationDialogInteractor> extends IBasePresenter<V, I> {

    boolean validateLatLong(String latitude,String longitude);
}
