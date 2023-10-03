package in.ults.ipms.ui.utility.streettap;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IStreetTapDetailsPresenter<V extends IStreetTapDetailsView, I extends IStreetTapDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String location,boolean workingStatus, String fundedBy, String address, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
