package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GeomPolyLine {
    @Expose
    @SerializedName("type")
    private String type;

    @Expose
    @SerializedName("coordinates")
    private ArrayList<ArrayList<ArrayList<Double>>> coordinates;

    public String getType() {
        return type;
    }

    public ArrayList<ArrayList<ArrayList<Double>>> getCoordinates() {
        return coordinates;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCoordinates(ArrayList<ArrayList<ArrayList<Double>>> coordinates) {
        this.coordinates = coordinates;
    }
}
