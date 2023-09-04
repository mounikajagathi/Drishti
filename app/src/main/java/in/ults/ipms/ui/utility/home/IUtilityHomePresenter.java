package in.ults.ipms.ui.utility.home;

import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

@PerFragment
public interface IUtilityHomePresenter<V extends IUtilityHomeView,I extends IUtilityHomeInteractor> extends IBasePresenter<V,I> {
}
