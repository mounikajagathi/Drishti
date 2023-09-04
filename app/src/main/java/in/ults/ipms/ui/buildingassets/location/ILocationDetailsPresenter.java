package in.ults.ipms.ui.buildingassets.location;

import in.ults.ipms.ui.base.IBasePresenter;

public interface ILocationDetailsPresenter<V extends ILocationDetailsView,I extends ILocationDetailsInteractor> extends IBasePresenter<V,I> {
    void validateData(String placeName, String zone, String wardNumber, String postoffice, double locationLatitude, double locationLongitude);
}
