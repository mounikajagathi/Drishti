package in.ults.ipms.di.module;

import dagger.Module;
import dagger.Provides;
import in.ults.ipms.di.PerDialog;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.dashboard.gotolocation.GoToLocationDialogInteractor;
import in.ults.ipms.ui.dashboard.gotolocation.GoToLocationDialogPresenter;
import in.ults.ipms.ui.dashboard.gotolocation.IGoToLocationDialogInteractor;
import in.ults.ipms.ui.dashboard.gotolocation.IGoToLocationDialogPresenter;
import in.ults.ipms.ui.dashboard.gotolocation.IGoToLocationDialogView;
import in.ults.ipms.ui.dashboard.location.ILocationDialogInteractor;
import in.ults.ipms.ui.dashboard.location.ILocationDialogPresenter;
import in.ults.ipms.ui.dashboard.location.ILocationDialogView;
import in.ults.ipms.ui.dashboard.location.LocationDialogInteractor;
import in.ults.ipms.ui.dashboard.location.LocationDialogPresenter;
import in.ults.ipms.ui.dashboard.measure.IMeasureDialogInteractor;
import in.ults.ipms.ui.dashboard.measure.IMeasureDialogPresenter;
import in.ults.ipms.ui.dashboard.measure.IMeasureDialogView;
import in.ults.ipms.ui.dashboard.measure.MeasureDialogInteractor;
import in.ults.ipms.ui.dashboard.measure.MeasureDialogPresenter;
import in.ults.ipms.utils.rx.AppSchedulerProvider;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 3/20/2018.
 */

@Module
public class DialogModule {

    private final BaseActivity baseActivity;

    public DialogModule(BaseActivity baseActivity) {
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
    @PerDialog
    IGoToLocationDialogPresenter<IGoToLocationDialogView, IGoToLocationDialogInteractor> provideGoToXYDP(GoToLocationDialogPresenter<IGoToLocationDialogView, IGoToLocationDialogInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerDialog
    IGoToLocationDialogInteractor provideGoToXYDI(GoToLocationDialogInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerDialog
    IMeasureDialogPresenter<IMeasureDialogView, IMeasureDialogInteractor> provideMeasurementDP(MeasureDialogPresenter<IMeasureDialogView, IMeasureDialogInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerDialog
    IMeasureDialogInteractor provideMeasurementDI(MeasureDialogInteractor interactor) {
        return interactor;
    }


    @Provides
    @PerDialog
    ILocationDialogPresenter<ILocationDialogView, ILocationDialogInteractor> provideLocationDP(LocationDialogPresenter<ILocationDialogView, ILocationDialogInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerDialog
    ILocationDialogInteractor provideLocationDI(LocationDialogInteractor interactor) {
        return interactor;
    }

}
