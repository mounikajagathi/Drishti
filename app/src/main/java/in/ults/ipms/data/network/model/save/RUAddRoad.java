package in.ults.ipms.data.network.model.save;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.ults.ipms.data.network.model.base.BaseResponse;

public class RUAddRoad extends BaseResponse {

    @Expose
    @SerializedName("road_name")
    private String roadName;

    @Expose
    @SerializedName("start_point")
    private String startPoint;
    @Expose
    @SerializedName("end_point")
    private String endPoint;
    @Expose
    @SerializedName("road_material")
    private String roadMaterial;
    @Expose
    @SerializedName("road_category")
    private String roadCategory;
    @Expose
    @SerializedName("maintained_by")
    private String maintainedBy;
    @Expose
    @SerializedName("lenth_mtr")
    private String lengthMtr;

    @Expose
    @SerializedName("carriage_width")
    private String carriageWidth;

    @Expose
    @SerializedName("roadway_width")
    private String roadwayWidth;

    @Expose
    @SerializedName("footpath_width")
    private String footpathWidth;

    @Expose
    @SerializedName("right_way_width")
    private String rightwaywidth;

    @Expose
    @SerializedName("remarks")
    private String remarks;

    @Expose
    @SerializedName("photo")
    private String photo;

    public RUAddRoad(){

    }

//    public RUAddRoad(String roadName,String startPoint,String endPoint,String roadMaterial,String roadCategory,String maintainedBy,String lengthMtr,String carriageWidth,String roadwayWidth);
}
