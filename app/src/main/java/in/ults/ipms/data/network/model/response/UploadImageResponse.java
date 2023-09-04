package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.ults.ipms.data.network.model.base.BaseResponse;

/**
 * Created by Mohammed Shafeeq on 31/07/18.
 */
public class UploadImageResponse extends BaseResponse {

    @Expose
    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    //Inner Data Class
    public static class Data {

        @Expose
        @SerializedName("url")
        private String url;


        public String getUrl() {
            return url;
        }
    }


}
