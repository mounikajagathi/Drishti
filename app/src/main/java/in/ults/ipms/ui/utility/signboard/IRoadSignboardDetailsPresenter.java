package in.ults.ipms.ui.utility.signboard;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IRoadSignboardDetailsPresenter<V extends IRoadSignboardDetailsView, I extends IRoadSignboardDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String place, String category, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
