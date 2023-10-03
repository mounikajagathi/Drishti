package in.ults.ipms.ui.utility.garbage;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IGarbageDetailsPresenter<V extends IGarbageDetailsView, I extends IGarbageDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String place, String capacity, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
