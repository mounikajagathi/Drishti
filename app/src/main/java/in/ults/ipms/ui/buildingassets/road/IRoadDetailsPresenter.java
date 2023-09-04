package in.ults.ipms.ui.buildingassets.road;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IRoadDetailsPresenter <V extends IRoadDetailsView,I extends IRoadDetailsInteractor> extends IBasePresenter<V,I> {

    void validateData(String nearRoad, String roadType, String roadWidth);
}
