package in.ults.ipms.ui.utility.playground;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IPlaygroundDetailsPresenter<V extends IPlaygroundDetailsView, I extends IPlaygroundDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String location, String name, String type, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
