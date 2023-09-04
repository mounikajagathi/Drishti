package in.ults.ipms.ui.auth;

import in.ults.ipms.di.PerActivity;
import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

@PerActivity
public interface IAuthPresenter<V extends IAuthView, I extends IAuthInteractor> extends IBasePresenter<V,I> {
}
