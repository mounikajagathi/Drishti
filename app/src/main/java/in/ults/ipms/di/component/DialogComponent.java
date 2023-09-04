package in.ults.ipms.di.component;

import dagger.Component;
import in.ults.ipms.di.PerDialog;
import in.ults.ipms.di.module.DialogModule;
import in.ults.ipms.ui.dashboard.gotolocation.GoToLocationDialog;
import in.ults.ipms.ui.dashboard.location.LocationDialog;
import in.ults.ipms.ui.dashboard.measure.MeasureDialog;

/**
 * Created by Mohammed Shafeeq on 3/20/2018.
 */
@PerDialog
@Component(dependencies = ApplicationComponent.class, modules = DialogModule.class)
public interface DialogComponent {
    void inject(LocationDialog fragment);
    void inject(GoToLocationDialog fragment);
    void inject(MeasureDialog fragment);
}
