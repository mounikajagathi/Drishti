package in.ults.ipms.ui.detailedinfo;

import in.ults.ipms.di.PerActivity;
import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

@PerActivity
public interface IDetailedInfoPresenter<V extends IDetailedInfoView, I extends IDetailedInfoInteractor> extends IBasePresenter<V,I> {



}
