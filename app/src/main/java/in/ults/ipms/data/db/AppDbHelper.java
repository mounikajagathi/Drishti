

package in.ults.ipms.data.db;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Mohammed Shafeeq VJ on 07/07/17.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }


}
