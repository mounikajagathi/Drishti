package in.ults.ipms.ui.waterbody.pond;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IPondDetailsPresenter<V extends IPondDetailsView, I extends IPondDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String name, String place, String obId, String area, String capacity, String maintainedBy, String usage, String odour, String pondStatus, String pondType, String presentCondition, String nature, String sideWall, String sideWallType, String pondCondition, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
