package in.ults.ipms.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AmalB on 18/06/21.
 */

public class FetchBuildingAssetsRequest {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("app")
    private String app;

    @Expose
    @SerializedName("layer")
    private String layer;

    public FetchBuildingAssetsRequest(String id, String app, String layer) {
        this.id = id;
        this.app = app;
        this.layer = layer;
    }
}
