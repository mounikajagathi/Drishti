package in.ults.ipms.ui.utility.hump;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IRoadHumpDetailsPresenter<V extends IRoadHumpDetailsView, I extends IRoadHumpDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String place, String name, String length, String width,String status, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
