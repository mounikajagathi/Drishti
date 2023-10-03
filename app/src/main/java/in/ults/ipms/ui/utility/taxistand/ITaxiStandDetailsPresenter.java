package in.ults.ipms.ui.utility.taxistand;

import in.ults.ipms.ui.base.IBasePresenter;

public interface ITaxiStandDetailsPresenter<V extends ITaxiStandDetailsView, I extends ITaxiStandDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String location, boolean authorised, String capacity, String authDetails, String type, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}