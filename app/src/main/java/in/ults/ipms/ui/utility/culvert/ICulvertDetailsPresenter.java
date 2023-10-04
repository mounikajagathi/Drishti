package in.ults.ipms.ui.utility.culvert;

import in.ults.ipms.ui.base.IBasePresenter;

public interface ICulvertDetailsPresenter<V extends ICulvertDetailsView, I extends ICulvertDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String name, String place,String uniqueID,String spanLength,String constructionMaterial,String roadName, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
