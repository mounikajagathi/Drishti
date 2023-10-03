package in.ults.ipms.ui.utility.bus;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IBusStandDetailsPresenter<V extends IBusStandDetailsView, I extends IBusStandDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String place, String name, String length, String width, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
