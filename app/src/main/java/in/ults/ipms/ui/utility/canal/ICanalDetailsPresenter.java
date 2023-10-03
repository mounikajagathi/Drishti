package in.ults.ipms.ui.utility.canal;

import in.ults.ipms.ui.base.IBasePresenter;

public interface ICanalDetailsPresenter<V extends ICanalDetailsView, I extends ICanalDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String name, String location, String streetName, String area, String type, String subType,String classField,String sideWall,String condition, String startPoint,String endPoint, String status, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
