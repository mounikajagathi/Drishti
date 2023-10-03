package in.ults.ipms.ui.utility.busstop;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IBusStopDetailsPresenter<V extends IBusStopDetailsView, I extends IBusStopDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String place, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
