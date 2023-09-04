package in.ults.ipms.di.module;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import in.ults.ipms.adapters.BasicSearchFilterAdapter;
import in.ults.ipms.adapters.CountMainAdapter;
import in.ults.ipms.adapters.DashboardLayersExpandableListAdapter;
import in.ults.ipms.adapters.MapOverlayAdapter;
import in.ults.ipms.di.PerBottomSheet;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.dashboard.basemap.BaseMapBottomSheetInteractor;
import in.ults.ipms.ui.dashboard.basemap.BaseMapBottomSheetPresenter;
import in.ults.ipms.ui.dashboard.basemap.IBaseMapBottomSheetInteractor;
import in.ults.ipms.ui.dashboard.basemap.IBaseMapBottomSheetPresenter;
import in.ults.ipms.ui.dashboard.basemap.IBaseMapBottomSheetView;
import in.ults.ipms.ui.dashboard.count.CountBottomSheetInteractor;
import in.ults.ipms.ui.dashboard.count.CountBottomSheetPresenter;
import in.ults.ipms.ui.dashboard.count.ICountBottomSheetInteractor;
import in.ults.ipms.ui.dashboard.count.ICountBottomSheetPresenter;
import in.ults.ipms.ui.dashboard.count.ICountBottomSheetView;
import in.ults.ipms.ui.dashboard.layers.ILayersBottomSheetInteractor;
import in.ults.ipms.ui.dashboard.layers.ILayersBottomSheetPresenter;
import in.ults.ipms.ui.dashboard.layers.ILayersBottomSheetView;
import in.ults.ipms.ui.dashboard.layers.LayersBottomSheetInteractor;
import in.ults.ipms.ui.dashboard.layers.LayersBottomSheetPresenter;
import in.ults.ipms.ui.dashboard.legends.ILegendsBottomSheetInteractor;
import in.ults.ipms.ui.dashboard.legends.ILegendsBottomSheetPresenter;
import in.ults.ipms.ui.dashboard.legends.ILegendsBottomSheetView;
import in.ults.ipms.ui.dashboard.legends.LegendsBottomSheetInteractor;
import in.ults.ipms.ui.dashboard.legends.LegendsBottomSheetPresenter;
import in.ults.ipms.ui.dashboard.opacity.IOpacityBottomSheetInteractor;
import in.ults.ipms.ui.dashboard.opacity.IOpacityBottomSheetPresenter;
import in.ults.ipms.ui.dashboard.opacity.IOpacityBottomSheetView;
import in.ults.ipms.ui.dashboard.opacity.OpacityBottomSheetInteractor;
import in.ults.ipms.ui.dashboard.opacity.OpacityBottomSheetPresenter;
import in.ults.ipms.ui.dashboard.search.filter.BasicFilterBottomSheetInteractor;
import in.ults.ipms.ui.dashboard.search.filter.BasicFilterBottomSheetPresenter;
import in.ults.ipms.ui.dashboard.search.filter.IBasicFilterBottomSheetInteractor;
import in.ults.ipms.ui.dashboard.search.filter.IBasicFilterBottomSheetPresenter;
import in.ults.ipms.ui.dashboard.search.filter.IBasicFilterBottomSheetView;
import in.ults.ipms.ui.dashboard.settings.ISettingsBottomSheetInteractor;
import in.ults.ipms.ui.dashboard.settings.ISettingsBottomSheetPresenter;
import in.ults.ipms.ui.dashboard.settings.ISettingsBottomSheetView;
import in.ults.ipms.ui.dashboard.settings.SettingsBottomSheetInteractor;
import in.ults.ipms.ui.dashboard.settings.SettingsBottomSheetPresenter;
import in.ults.ipms.ui.mapoverlays.IMapOverlayBottomSheetInteractor;
import in.ults.ipms.ui.mapoverlays.IMapOverlayBottomSheetPresenter;
import in.ults.ipms.ui.mapoverlays.IMapOverlayBottomSheetView;
import in.ults.ipms.ui.mapoverlays.MapOverlayBottomSheetInteractor;
import in.ults.ipms.ui.mapoverlays.MapOverlayBottomSheetPresenter;
import in.ults.ipms.utils.rx.AppSchedulerProvider;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 3/20/2018.
 */

@Module
public class BottomSheetModule {

    private final BaseActivity baseActivity;

    public BottomSheetModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
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
    BaseActivity provideBaseActivity() {
        return baseActivity;
    }


    @Provides
    @PerBottomSheet
    IBaseMapBottomSheetPresenter<IBaseMapBottomSheetView, IBaseMapBottomSheetInteractor> provideBaseMapBSP(BaseMapBottomSheetPresenter<IBaseMapBottomSheetView, IBaseMapBottomSheetInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerBottomSheet
    IBaseMapBottomSheetInteractor provideBaseMapBSI(BaseMapBottomSheetInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerBottomSheet
    ILayersBottomSheetPresenter<ILayersBottomSheetView, ILayersBottomSheetInteractor> provideLayersBSP(LayersBottomSheetPresenter<ILayersBottomSheetView, ILayersBottomSheetInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerBottomSheet
    ILayersBottomSheetInteractor provideLayersBSI(LayersBottomSheetInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerBottomSheet
    ICountBottomSheetPresenter<ICountBottomSheetView, ICountBottomSheetInteractor> provideCountBSP(CountBottomSheetPresenter<ICountBottomSheetView, ICountBottomSheetInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerBottomSheet
    ICountBottomSheetInteractor provideCountBSI(CountBottomSheetInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerBottomSheet
    ILegendsBottomSheetPresenter<ILegendsBottomSheetView, ILegendsBottomSheetInteractor> provideLegendsBSP(LegendsBottomSheetPresenter<ILegendsBottomSheetView, ILegendsBottomSheetInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerBottomSheet
    ILegendsBottomSheetInteractor provideLegendsBSI(LegendsBottomSheetInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerBottomSheet
    IOpacityBottomSheetPresenter<IOpacityBottomSheetView, IOpacityBottomSheetInteractor> provideOpacityBSP(OpacityBottomSheetPresenter<IOpacityBottomSheetView, IOpacityBottomSheetInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerBottomSheet
    IOpacityBottomSheetInteractor provideOpacityBSI(OpacityBottomSheetInteractor interactor) {
        return interactor;
    }


    @Provides
    @PerBottomSheet
    DashboardLayersExpandableListAdapter providesDashboardLayersExpandableListAdapter(BaseActivity baseActivity) {
        return new DashboardLayersExpandableListAdapter(baseActivity);
    }

    @Provides
    @PerBottomSheet
    MapOverlayAdapter providesMapOverlayAdapter() {
        return new MapOverlayAdapter();
    }


    @Provides
    @PerBottomSheet
    IBasicFilterBottomSheetInteractor provideBasicFilterBSI(BasicFilterBottomSheetInteractor interactor) {
        return interactor;
    }


    @Provides
    @PerBottomSheet
    IBasicFilterBottomSheetPresenter<IBasicFilterBottomSheetView, IBasicFilterBottomSheetInteractor> provideBasicFilterBSP(BasicFilterBottomSheetPresenter<IBasicFilterBottomSheetView, IBasicFilterBottomSheetInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerBottomSheet
    ISettingsBottomSheetInteractor provideSettingsBSI(SettingsBottomSheetInteractor interactor) {
        return interactor;
    }


    @Provides
    @PerBottomSheet
    ISettingsBottomSheetPresenter<ISettingsBottomSheetView, ISettingsBottomSheetInteractor> provideSettingsBSP(SettingsBottomSheetPresenter<ISettingsBottomSheetView, ISettingsBottomSheetInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerBottomSheet
    IMapOverlayBottomSheetInteractor provideMapOverlayBSI(MapOverlayBottomSheetInteractor interactor) {
        return interactor;
    }


    @Provides
    @PerBottomSheet
    IMapOverlayBottomSheetPresenter<IMapOverlayBottomSheetView, IMapOverlayBottomSheetInteractor> provideMapOverlayBSP(MapOverlayBottomSheetPresenter<IMapOverlayBottomSheetView, IMapOverlayBottomSheetInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerBottomSheet
    LinearLayoutManager provideLinearLayoutManager(BaseActivity baseActivity) {
        return new LinearLayoutManager(baseActivity);
    }

    @Provides
    @PerBottomSheet
    GridLayoutManager provideGridLayoutManager(BaseActivity baseActivity) {
        return new GridLayoutManager(baseActivity, 2);
    }

    @Provides
    @PerBottomSheet
    BasicSearchFilterAdapter providesBasicSearchAdapter() {
        return new BasicSearchFilterAdapter();
    }

    @Provides
    @PerBottomSheet
    CountMainAdapter providesCountMainAdapter(BaseActivity baseActivity) {
        return new CountMainAdapter(baseActivity);
    }

}
