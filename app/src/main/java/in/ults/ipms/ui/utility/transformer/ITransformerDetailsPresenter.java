package in.ults.ipms.ui.utility.transformer;

import in.ults.ipms.ui.base.IBasePresenter;

public interface ITransformerDetailsPresenter<V extends ITransformerDetailsView, I extends ITransformerDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String poleNumber, String location, String consumerNo, String applicationNo, String category, String singleTube,String doubleTube,String led,String cfl, String bulb,String sodiumVapour, String connectedLoad, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
