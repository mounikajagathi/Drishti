package in.ults.ipms.ui.buildingassets;

import in.ults.ipms.di.PerActivity;
import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

@PerActivity
public interface IBuildingAssetsPresenter<V extends IBuildingAssetsView, I extends IBuildingAssetsInteractor> extends IBasePresenter<V,I> {
}
