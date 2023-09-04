package in.ults.ipms.data.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Mohammed Shafeeq on 16/08/18.
 */

@Entity(tableName = "cart")
public class ProductData  {

    @Expose
    @SerializedName("id")
    @ColumnInfo(name = "product_id")
    private String productId;

    @Expose
    @PrimaryKey
    public Long dbID;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
