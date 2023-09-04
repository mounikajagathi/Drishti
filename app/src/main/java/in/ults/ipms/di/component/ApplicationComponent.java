package in.ults.ipms.di.component;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import in.ults.ipms.application.AppClass;
import in.ults.ipms.data.DataManager;
import in.ults.ipms.data.db.DbHelper;
import in.ults.ipms.data.network.ApiHelper;
import in.ults.ipms.data.prefs.PreferencesHelper;
import in.ults.ipms.di.ApplicationContext;
import in.ults.ipms.di.module.ApplicationModule;

/**
 * Created by Mohammed Shafeeq on 19/03/18.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(AppClass application);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();

    PreferencesHelper preferencesHelper();

    ApiHelper apiHelper();

    DbHelper dbHelper();

    Gson getGson();





}
