package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.SerializedName;

public class WFSFeature {
    @SerializedName("type") // Replace with actual attribute names from your WFS data
    private String type;

    @SerializedName("geometry") // Replace with actual attribute names from your WFS data
    private GeomPolygon geometry;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeomPolygon getGeometry() {
        return geometry;
    }

    public void setGeometry(GeomPolygon geometry) {
        this.geometry = geometry;
    }
}