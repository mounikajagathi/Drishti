package in.ults.ipms.ui.utility;

import in.ults.ipms.di.PerActivity;
import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

@PerActivity
public interface IUtilityPresenter<V extends IUtilityView, I extends IUtilityInteractor> extends IBasePresenter<V,I> {

    void fetchUtilityDropDown();
}
