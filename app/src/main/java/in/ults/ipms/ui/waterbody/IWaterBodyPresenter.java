package in.ults.ipms.ui.waterbody;

import in.ults.ipms.di.PerActivity;
import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

@PerActivity
public interface IWaterBodyPresenter<V extends IWaterBodyView, I extends IWaterBodyInteractor> extends IBasePresenter<V,I> {
    
    void fetchWaterBodyDropDown();

}
