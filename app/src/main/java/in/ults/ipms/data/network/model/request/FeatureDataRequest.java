package in.ults.ipms.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AmalB on 18/06/21.
 */

public class FeatureDataRequest {

    @Expose
    @SerializedName("id")
    private String id;

    public FeatureDataRequest(String id) {
        this.id = id;
    }
}
