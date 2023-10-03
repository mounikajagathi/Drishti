package in.ults.ipms.ui.utility.mobiletower;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IMobileTowerDetailsPresenter<V extends IMobileTowerDetailsView, I extends IMobileTowerDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String buildingName, String place, String ownerName, String ownerAddress, String ownerMobile,
                      String buildingStatus, String buildingUsage, String newPropertyId, String oldPropertyId,
                      String yearOfConstruction, boolean isEleCon, String uniqueId, String serviceProvider,
                      String consumerNo, String roadWidth, String roadType, String wardNo, String remarks,
                      String photo, String photo2, double locationLatitude, double locationLongitude);
}
