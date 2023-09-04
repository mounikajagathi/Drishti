package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.ipms.utils.AppConstants;

public class GeomPoint {
    @Expose
    @SerializedName("type")
    private String type;


    @Expose
    @SerializedName("coordinates")
    private ArrayList<Double> coordinates;

    public String getType() {
        return type;
    }

    public ArrayList<Double> getCoordinates() {
        return coordinates;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCoordinates(ArrayList<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public void setGeom(double longitude, double latitude){
        setType(AppConstants.GEOM_TYPE_POINT);
        ArrayList<Double> coordinates = new ArrayList<>();
        coordinates.add(longitude);
        coordinates.add(latitude);
        setCoordinates(coordinates);
    }
}
