package in.ults.ipms.ui.utility.bridge;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IBridgeDetailsPresenter<V extends IBridgeDetailsView, I extends IBridgeDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String bridgeName, String place, String bridgeMaterial, String bridgeSubType, String carriageWidth, String bridgeWidth,String bridgeLength,String footpathConstructionMaterial, String wardNo, String maintainedBy, String remarks, String photo, double locationLatitude, double locationLongitude);
}
