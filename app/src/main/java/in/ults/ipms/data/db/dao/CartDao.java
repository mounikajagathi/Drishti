package in.ults.ipms.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import in.ults.ipms.data.db.model.ProductData;

/**
 * Created by Mohammed Shafeeq on 25/08/18.
 */
@Dao
public interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProductData productData);
}
