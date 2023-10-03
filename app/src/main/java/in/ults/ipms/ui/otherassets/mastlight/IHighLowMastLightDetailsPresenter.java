package in.ults.ipms.ui.otherassets.mastlight;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IHighLowMastLightDetailsPresenter<V extends IHighLowMastLightDetailsView, I extends IHighLowMastLightDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String type,
                      String lightType,
                      String fundedBy,
                      String workingStatus,
                      String locationDetails,
                      String address,
                      String power,
                      String height,
                      String bulbNumber,
                      String wardNo,
                      String remarks,
                      String photo,
                      double locationLatitude,
                      double locationLongitude);
}
