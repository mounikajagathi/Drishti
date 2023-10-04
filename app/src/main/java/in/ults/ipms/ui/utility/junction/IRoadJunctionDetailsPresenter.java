package in.ults.ipms.ui.utility.junction;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IRoadJunctionDetailsPresenter<V extends IRoadJunctionDetailsView, I extends IRoadJunctionDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String name,String location,String noOfRoad,String pedestrian, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
