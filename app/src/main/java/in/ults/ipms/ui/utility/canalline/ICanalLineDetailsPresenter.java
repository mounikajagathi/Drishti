package in.ults.ipms.ui.utility.canalline;

import in.ults.ipms.ui.base.IBasePresenter;

public interface ICanalLineDetailsPresenter<V extends ICanalLineDetailsView, I extends ICanalLineDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String streetName, String area, String type, String subType,String classField,String width,String length, String startPoint,String endPoint, String status, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
