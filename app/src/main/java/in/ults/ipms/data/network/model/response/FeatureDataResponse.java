package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.ipms.data.network.model.base.BaseResponse;

/**
 * Created by Mohammed Shafeeq on 31/07/18.
 */
public class FeatureDataResponse extends BaseResponse {

    @Expose
    @SerializedName("data")
    private ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }


    //Inner Data Class
    public static class Data {

        @Expose
        @SerializedName("minimalInfo")
        private ArrayList<MinimalInfo> minimalInfo;

        @Expose
        @SerializedName("detailedInfo")
        private ArrayList<DetailedInfo> detailedInfo;

        @Expose
        @SerializedName("images")
        private ArrayList<String> images;

        @Expose
        @SerializedName("app")
        private String app;
        @Expose
        @SerializedName("table")
        private String table;
        @Expose
        @SerializedName("id")
        private String id;
        @Expose
        @SerializedName("layer")
        private String layer;

        @Expose
        @SerializedName("can_edit")
        private boolean canEdit;

        @Expose
        @SerializedName("geom")
        private Geom geom;


        public String getApp() {
            return app;
        }

        public String getTable() {
            return table;
        }

        public String getId() {
            return id;
        }

        public String getLayer() {
            return layer;
        }

        public boolean isCanEdit() {
            return canEdit;
        }

        public ArrayList<MinimalInfo> getMinimalInfo() {
            return minimalInfo;
        }

        public ArrayList<DetailedInfo> getDetailedInfo() {
            return detailedInfo;
        }

        public ArrayList<String> getImages() {
            return images;
        }

        public Geom getGeom() {
            return geom;
        }
    }


    public static class MinimalInfo {

        @Expose
        @SerializedName("field_name")
        private String infoTitle;

        @Expose
        @SerializedName("field_value")
        private String infoValue;

        public String getInfoTitle() {
            return infoTitle;
        }

        public String getInfoValue() {
            return infoValue;
        }
    }


    public static class DetailedInfo {

        @Expose
        @SerializedName("heading")
        private String heading;


        @Expose
        @SerializedName("fields")
        private ArrayList<Object> detailedInfoData;

        public String getHeading() {
            return heading;
        }

        public ArrayList<Object> getDetailedInfoData() {
            return detailedInfoData;
        }
    }

    public static class DetailedInfoData {

        @Expose
        @SerializedName("field_name")
        private String infoTitle;


        @Expose
        @SerializedName("field_value")
        private Object infoValue;

        public DetailedInfoData(String infoTitle, Object infoValue) {
            this.infoTitle = infoTitle;
            this.infoValue = infoValue;
        }

        public String getInfoTitle() {
            return infoTitle;
        }

        public Object getInfoValue() {
            return infoValue;
        }
    }

    public static class Geom {

        @Expose
        @SerializedName("type")
        private String type;


        @Expose
        @SerializedName("coordinates")
        private ArrayList<Object> coordinates;

        public String getType() {
            return type;
        }

        public ArrayList<Object> getCoordinates() {
            return coordinates;
        }
    }



}
