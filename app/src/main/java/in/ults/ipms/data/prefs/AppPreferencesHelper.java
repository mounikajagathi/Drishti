package in.ults.ipms.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.ults.ipms.di.ApplicationContext;
import in.ults.ipms.di.PreferenceInfo;
import in.ults.ipms.utils.AppConstants;

/**
 * Created by Mohammed Shafeeq on 27/01/17.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_USER_ACCESS_ID = "PREF_KEY_USER_ACCESS_ID";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_ACTIVE_STATUS = "PREF_KEY_ACTIVE_STATUS";
    private static final String PREF_KEY_LOG_USER_NAME = "PREF_KEY_LOG_USER_NAME";
    private static final String PREF_KEY_LOG_PASSWORD = "PREF_KEY_LOG_PASSWORD";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE, AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(AppConstants.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public long getUserAccessId() {
        return mPrefs.getLong(PREF_KEY_USER_ACCESS_ID, -1);
    }

    @Override
    public void setUserAccessId(long id) {
        mPrefs.edit().putLong(PREF_KEY_USER_ACCESS_ID, id).apply();
    }

    @Override
    public boolean getUserActiveStatus() {
        return mPrefs.getBoolean(PREF_KEY_ACTIVE_STATUS, false);
    }

    @Override
    public void setUserActiveStatus(boolean activeStatus) {
        mPrefs.edit().putBoolean(PREF_KEY_ACTIVE_STATUS, activeStatus).apply();
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, "Bearer "+accessToken).apply();
    }

    @Override
    public String getLogUserName() {
        return mPrefs.getString(PREF_KEY_LOG_USER_NAME, null);
    }

    @Override
    public void setLogUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_LOG_USER_NAME, userName).apply();
    }

    @Override
    public String getLogPassword() {
        return mPrefs.getString(PREF_KEY_LOG_PASSWORD, null);
    }

    @Override
    public void setLogPassword(String password) {
        mPrefs.edit().putString(PREF_KEY_LOG_PASSWORD, password).apply();
    }
}
