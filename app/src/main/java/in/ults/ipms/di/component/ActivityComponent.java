package in.ults.ipms.di.component;

import dagger.Component;
import in.ults.ipms.di.PerActivity;
import in.ults.ipms.di.module.ActivityModule;
import in.ults.ipms.ui.auth.AuthActivity;
import in.ults.ipms.ui.buildingassets.BuildingAssetsActivity;
import in.ults.ipms.ui.dashboard.DashboardActivity;
import in.ults.ipms.ui.dashboard.search.SearchActivity;
import in.ults.ipms.ui.detailedinfo.DetailedInfoActivity;
import in.ults.ipms.ui.splash.SplashActivity;
import in.ults.ipms.ui.utility.UtilityActivity;
import in.ults.ipms.ui.viewer.imageview.ImageViewerActivity;
import in.ults.ipms.ui.waterbody.WaterBodyActivity;

/**
 * Created by Mohammed Shafeeq on 19/03/18.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);
    void inject(AuthActivity activity);
    void inject(BuildingAssetsActivity activity);
    void inject(DashboardActivity activity);
    void inject(SearchActivity activity);
    void inject(DetailedInfoActivity activity);
    void inject(ImageViewerActivity activity);
    void inject(UtilityActivity activity);
    void inject(WaterBodyActivity activity);
}
