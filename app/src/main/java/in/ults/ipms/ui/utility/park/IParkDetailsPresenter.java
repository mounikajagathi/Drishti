package in.ults.ipms.ui.utility.park;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IParkDetailsPresenter<V extends IParkDetailsView, I extends IParkDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String location, String name,String area, String surveyNo, String type, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
