package in.ults.ipms.ui.utility.busbay;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IBusBayDetailsPresenter<V extends IBusBayDetailsView, I extends IBusBayDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String name, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
