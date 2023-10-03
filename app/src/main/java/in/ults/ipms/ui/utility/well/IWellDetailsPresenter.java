package in.ults.ipms.ui.utility.well;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IWellDetailsPresenter<V extends IWellDetailsView, I extends IWellDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String location,String wellOwner,String wellPurpose, String wellCover, String surveyNo,String nearRoad,String reWaterAvailMarks,String status, boolean seasonal,String wellType, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
