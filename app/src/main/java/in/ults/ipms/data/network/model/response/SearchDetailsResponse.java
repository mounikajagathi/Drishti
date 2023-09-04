package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.ipms.data.network.model.base.BaseResponse;

/**
 * Created by Mohammed Shafeeq on 31/07/18.
 */
public class SearchDetailsResponse extends BaseResponse {

    @Expose
    @SerializedName("data")
    private Data data;


    public Data getData() {
        return data;
    }


    //Inner Data Class
    public static class Data {

        @Expose
        @SerializedName("search_result")
        private ArrayList<Features> features;

        public ArrayList<Features> getFeatures() {
            return features;
        }

        @Expose
        @SerializedName("table")
        private String featureIdType;

        public String getFeatureIdType() {
            return featureIdType;
        }

    }


    //Inner Data Class
    public static class Features {
        @Expose
        @SerializedName("properties")
        private Property property;

        public Property getProperty() {
            return property;
        }
    }


    //Inner Data Class
    public static class Property {

        @Expose
        @SerializedName("new_pro_id")
        private String newPropertyId;

        @Expose
        @SerializedName("old_pro_id")
        private String oldPropertyId;

        @Expose
        @SerializedName("pk")
        private String pk;

        public String getNewPropertyId() {
            return newPropertyId;
        }

        public String getOldPropertyId() {
            return oldPropertyId;
        }

        public String getPk() {
            return pk;
        }
    }


}
