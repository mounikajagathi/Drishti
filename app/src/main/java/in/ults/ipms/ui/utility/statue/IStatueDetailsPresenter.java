package in.ults.ipms.ui.utility.statue;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IStatueDetailsPresenter<V extends IStatueDetailsView, I extends IStatueDetailsInteractor> extends IBasePresenter<V, I> {
    void validateData(String location, String wardNo, String remarks, String photo, double locationLatitude, double locationLongitude);
}
