package in.ults.ipms.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.ults.ipms.BuildConfig;
import in.ults.ipms.data.AppDataManager;
import in.ults.ipms.data.DataManager;
import in.ults.ipms.data.db.AppDatabase;
import in.ults.ipms.data.db.AppDbHelper;
import in.ults.ipms.data.db.DbHelper;
import in.ults.ipms.data.network.ApiHelper;
import in.ults.ipms.data.network.ApiInterface;
import in.ults.ipms.data.network.AppApiHelper;
import in.ults.ipms.data.prefs.AppPreferencesHelper;
import in.ults.ipms.data.prefs.PreferencesHelper;
import in.ults.ipms.di.ApplicationContext;
import in.ults.ipms.di.DatabaseInfo;
import in.ults.ipms.di.PreferenceInfo;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.network.NetworkInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohammed Shafeeq on 19/03/18.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;


    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }


    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@ApplicationContext Context context) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        okHttpClientBuilder.addInterceptor(new NetworkInterceptor(context));
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();
    }

    @Provides
    @Singleton
    ApiInterface provideApiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName,@ApplicationContext Context context) {
        return Room
                .databaseBuilder(context, AppDatabase.class, dbName)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper){
        return appDbHelper;
    }


    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
}
