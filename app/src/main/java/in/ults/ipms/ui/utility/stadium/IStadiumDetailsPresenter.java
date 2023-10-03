package in.ults.ipms.ui.utility.stadium;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IStadiumDetailsPresenter<V extends IStadiumDetailsView, I extends IStadiumDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String name,String location,String address, String area, String surveyNo,String noGallery,String electricity,String bathroom, String galleryCoverage,String gallery, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
