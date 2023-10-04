package in.ults.ipms.ui.utility.divider;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IDividerDetailsPresenter<V extends IDividerDetailsView, I extends IDividerDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String place, String length,String width,String startEnd, String material, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
