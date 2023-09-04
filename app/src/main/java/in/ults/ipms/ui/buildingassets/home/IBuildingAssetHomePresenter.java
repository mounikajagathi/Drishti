package in.ults.ipms.ui.buildingassets.home;

import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

@PerFragment
public interface IBuildingAssetHomePresenter<V extends IBuildingAssetHomeView,I extends IBuildingAssetHomeInteractor> extends IBasePresenter<V,I> {
    void getBuildingAssetSpinnerData();
}
