package in.ults.ipms.ui.utility.parking;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IParkingDetailsPresenter<V extends IParkingDetailsView, I extends IParkingDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String place, String capacity, String type, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
