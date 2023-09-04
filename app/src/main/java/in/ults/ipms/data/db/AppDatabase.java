

package in.ults.ipms.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import in.ults.ipms.data.db.dao.CartDao;
import in.ults.ipms.data.db.model.ProductData;


/**
 * Created by Mohammed Shafeeq VJ on 07/07/17.
 */
@Database(entities = {ProductData.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CartDao cartDao();
}
