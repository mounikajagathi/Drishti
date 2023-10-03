package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WFSModel {
    @SerializedName("type") // Replace with actual attribute names from your WFS data
    private String type;

    @SerializedName("features") // Replace with actual attribute names from your WFS data
    private ArrayList<WFSFeature> features;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<WFSFeature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<WFSFeature> features) {
        this.features = features;
    }
}