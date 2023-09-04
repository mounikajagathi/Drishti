package in.ults.ipms.ui.splash;

import in.ults.ipms.di.PerActivity;
import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

@PerActivity
public interface ISplashPresenter<V extends ISplashView, I extends ISplashInteractor> extends IBasePresenter<V,I> {

    void runSplashTimer();

}
