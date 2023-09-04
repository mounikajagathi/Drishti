package in.ults.ipms.data.prefs;


import javax.inject.Singleton;

import in.ults.ipms.utils.AppConstants;

/**
 * Created by Mohammed Shafeeq on 27/01/17.
 */

@Singleton
public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(AppConstants.LoggedInMode mode);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    long getUserAccessId();

    void setUserAccessId(long id);

    boolean getUserActiveStatus();

    void setUserActiveStatus(boolean activeStatus);

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getLogUserName();

    void setLogUserName(String userName);

    String getLogPassword();

    void setLogPassword(String password);
}
