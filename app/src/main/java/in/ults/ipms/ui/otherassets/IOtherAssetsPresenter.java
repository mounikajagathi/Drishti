package in.ults.ipms.ui.otherassets;

import in.ults.ipms.di.PerActivity;
import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

@PerActivity
public interface IOtherAssetsPresenter<V extends IOtherAssetsView, I extends IOtherAssetsInteractor> extends IBasePresenter<V,I> {
    
    void fetchOtherAssetsDropDown();

}
