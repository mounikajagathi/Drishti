package in.ults.ipms.application;

import android.app.Application;

import in.ults.ipms.di.component.ApplicationComponent;
import in.ults.ipms.di.component.DaggerApplicationComponent;
import in.ults.ipms.di.module.ApplicationModule;

/**
 * Created by Mohammed Shafeeq on 19/03/18.
 */

public class AppClass extends Application {

    private static ApplicationComponent applicationComponent;
    private  static AppClass context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        applicationComponent =
                DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static AppClass getContext() {
        return context;
    }
}
