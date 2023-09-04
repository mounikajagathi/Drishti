package in.ults.ipms.data;

import in.ults.ipms.data.db.DbHelper;
import in.ults.ipms.data.network.ApiHelper;
import in.ults.ipms.data.prefs.PreferencesHelper;

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    void setUserAsLoggedOut();

    void setUserAsLoggedIn();

    boolean isUserLoggedIn();

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }


}
