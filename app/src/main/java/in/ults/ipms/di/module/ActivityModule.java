package in.ults.ipms.di.module;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import in.ults.ipms.adapters.DashboardMenuFeatureAdapter;
import in.ults.ipms.adapters.DetailInfoAdapter;
import in.ults.ipms.adapters.SearchAdapter;
import in.ults.ipms.adapters.SearchDetailsAdapter;
import in.ults.ipms.di.ActivityContext;
import in.ults.ipms.di.PerActivity;
import in.ults.ipms.ui.auth.AuthInteractor;
import in.ults.ipms.ui.auth.AuthPresenter;
import in.ults.ipms.ui.auth.IAuthInteractor;
import in.ults.ipms.ui.auth.IAuthPresenter;
import in.ults.ipms.ui.auth.IAuthView;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.buildingassets.BuildingAssetsInteractor;
import in.ults.ipms.ui.buildingassets.BuildingAssetsPresenter;
import in.ults.ipms.ui.buildingassets.IBuildingAssetsInteractor;
import in.ults.ipms.ui.buildingassets.IBuildingAssetsPresenter;
import in.ults.ipms.ui.buildingassets.IBuildingAssetsView;
import in.ults.ipms.ui.dashboard.DashboardInteractor;
import in.ults.ipms.ui.dashboard.DashboardPresenter;
import in.ults.ipms.ui.dashboard.IDashboardInteractor;
import in.ults.ipms.ui.dashboard.IDashboardPresenter;
import in.ults.ipms.ui.dashboard.IDashboardView;
import in.ults.ipms.ui.dashboard.search.ISearchInteractor;
import in.ults.ipms.ui.dashboard.search.ISearchPresenter;
import in.ults.ipms.ui.dashboard.search.ISearchView;
import in.ults.ipms.ui.dashboard.search.SearchInteractor;
import in.ults.ipms.ui.dashboard.search.SearchPresenter;
import in.ults.ipms.ui.detailedinfo.DetailedInfoInteractor;
import in.ults.ipms.ui.detailedinfo.DetailedInfoPresenter;
import in.ults.ipms.ui.detailedinfo.IDetailedInfoInteractor;
import in.ults.ipms.ui.detailedinfo.IDetailedInfoPresenter;
import in.ults.ipms.ui.detailedinfo.IDetailedInfoView;
import in.ults.ipms.ui.otherassets.IOtherAssetsInteractor;
import in.ults.ipms.ui.otherassets.IOtherAssetsPresenter;
import in.ults.ipms.ui.otherassets.IOtherAssetsView;
import in.ults.ipms.ui.otherassets.OtherAssetsInteractor;
import in.ults.ipms.ui.otherassets.OtherAssetsPresenter;
import in.ults.ipms.ui.splash.ISplashInteractor;
import in.ults.ipms.ui.splash.ISplashPresenter;
import in.ults.ipms.ui.splash.ISplashView;
import in.ults.ipms.ui.splash.SplashInteractor;
import in.ults.ipms.ui.splash.SplashPresenter;
import in.ults.ipms.ui.utility.IUtilityInteractor;
import in.ults.ipms.ui.utility.IUtilityPresenter;
import in.ults.ipms.ui.utility.IUtilityView;
import in.ults.ipms.ui.utility.UtilityInteractor;
import in.ults.ipms.ui.utility.UtilityPresenter;
import in.ults.ipms.ui.viewer.imageview.IImageViewerInteractor;
import in.ults.ipms.ui.viewer.imageview.IImageViewerPresenter;
import in.ults.ipms.ui.viewer.imageview.IImageViewerView;
import in.ults.ipms.ui.viewer.imageview.ImageViewerInteractor;
import in.ults.ipms.ui.viewer.imageview.ImageViewerPresenter;
import in.ults.ipms.ui.waterbody.IWaterBodyInteractor;
import in.ults.ipms.ui.waterbody.IWaterBodyPresenter;
import in.ults.ipms.ui.waterbody.IWaterBodyView;
import in.ults.ipms.ui.waterbody.WaterBodyInteractor;
import in.ults.ipms.ui.waterbody.WaterBodyPresenter;
import in.ults.ipms.utils.rx.AppSchedulerProvider;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 19/03/18.
 */

@Module
public class ActivityModule {

    private final BaseActivity baseActivity;


    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return baseActivity;
    }

    @Provides
    BaseActivity provideActivity() {
        return baseActivity;
    }

    @Provides
    CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider providesSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    ISplashPresenter<ISplashView, ISplashInteractor> provideSplashAP(SplashPresenter<ISplashView, ISplashInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ISplashInteractor provideSplashAAI(SplashInteractor interactor) {
        return interactor;
    }



    @Provides
    @PerActivity
    IAuthPresenter<IAuthView, IAuthInteractor> provideAuthAP(AuthPresenter<IAuthView, IAuthInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    IAuthInteractor provideAuthAAI(AuthInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    IDashboardPresenter<IDashboardView, IDashboardInteractor> provideDashboardAP(DashboardPresenter<IDashboardView, IDashboardInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    IDashboardInteractor provideDashboardAAI(DashboardInteractor interactor) {
        return interactor;
    }



    @Provides
    @PerActivity
    IBuildingAssetsPresenter<IBuildingAssetsView, IBuildingAssetsInteractor> provideMainAP(BuildingAssetsPresenter<IBuildingAssetsView, IBuildingAssetsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    IBuildingAssetsInteractor provideMainAAI(BuildingAssetsInteractor interactor) {
        return interactor;
    }


    @Provides
    @PerActivity
    ISearchPresenter<ISearchView, ISearchInteractor> provideSearchAP(SearchPresenter<ISearchView, ISearchInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ISearchInteractor provideSearchAAI(SearchInteractor interactor) {
        return interactor;
    }



    @Provides
    @PerActivity
    IDetailedInfoPresenter<IDetailedInfoView, IDetailedInfoInteractor> provideDetailedInfoAP(DetailedInfoPresenter<IDetailedInfoView, IDetailedInfoInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    IDetailedInfoInteractor provideDetailedInfoAAI(DetailedInfoInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    IImageViewerPresenter<IImageViewerView, IImageViewerInteractor> provideImageViewerAP(ImageViewerPresenter<IImageViewerView, IImageViewerInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    IImageViewerInteractor provideImageViewerAAI(ImageViewerInteractor interactor) {
        return interactor;
    }


   @Provides
    @PerActivity
   IUtilityPresenter<IUtilityView, IUtilityInteractor> provideRoadUtilityAP(UtilityPresenter<IUtilityView, IUtilityInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    IUtilityInteractor provideRoadUtilityAAI(UtilityInteractor interactor) {
        return interactor;
    }


    @Provides
    @PerActivity
    LinearLayoutManager provideLinearLayoutManager(BaseActivity baseActivity){
        return new LinearLayoutManager(baseActivity);
    }


    @Provides
    @PerActivity
    DashboardMenuFeatureAdapter providesDashboardMenuAdapter(){
        return new DashboardMenuFeatureAdapter();
    }


    @Provides
    @PerActivity
    SearchAdapter providesSearchAdapter(){
        return new SearchAdapter();
    }


    @Provides
    @PerActivity
    SearchDetailsAdapter providesSearchDetailsAdapter(BaseActivity baseActivity){
        return new SearchDetailsAdapter(baseActivity);
    }


    @Provides
    @PerActivity
    DetailInfoAdapter providesDetailInfoAdapter(BaseActivity baseActivity) {
        return new DetailInfoAdapter(baseActivity);
    }


    @Provides
    @PerActivity
    IWaterBodyPresenter<IWaterBodyView, IWaterBodyInteractor> provideWaterBodyAP(WaterBodyPresenter<IWaterBodyView, IWaterBodyInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    IWaterBodyInteractor provideWaterBodyAAI(WaterBodyInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    IOtherAssetsPresenter<IOtherAssetsView, IOtherAssetsInteractor> provideOtherAssetsAP(OtherAssetsPresenter<IOtherAssetsView, IOtherAssetsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    IOtherAssetsInteractor provideOtherAssetsAAI(OtherAssetsInteractor interactor) {
        return interactor;
    }


}
