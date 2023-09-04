package in.ults.ipms.ui.dashboard;

import java.util.Set;

import in.ults.ipms.di.PerActivity;
import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

@PerActivity
public interface IDashboardPresenter<V extends IDashboardView, I extends IDashboardInteractor> extends IBasePresenter<V,I> {

    void getDashboardDetails();
    void getFeatureInfoData(Set<String> featureIdSet);
}
