package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.ipms.data.network.model.base.BaseResponse;

/**
 * Created by Mohammed Shafeeq on 31/07/18.
 */
public class SearchResponse extends BaseResponse {

    @Expose
    @SerializedName("data")
     private ArrayList<String>  data;

    public ArrayList<String>  getData() {
        return data;
    }

}
