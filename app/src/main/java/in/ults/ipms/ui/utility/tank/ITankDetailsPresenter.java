package in.ults.ipms.ui.utility.tank;

import in.ults.ipms.ui.base.IBasePresenter;

public interface ITankDetailsPresenter<V extends ITankDetailsView, I extends ITankDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String location, String tankOwner,String capacity, String type, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
