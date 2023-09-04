package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.ipms.data.network.model.base.BaseResponse;
import in.ults.ipms.data.network.model.base.BaseSpinnerData;

public class BuildingNameSpinnerResponse extends BaseResponse {
    @Expose
    @SerializedName("data")
    private BuildingNameSpinnerResponse.Data data;

    public Data getData() {
        return data;
    }

    public static class Data {

        @Expose
        @SerializedName("buildings")
        private ArrayList<BuildingName> buildingName;

        public ArrayList<BuildingName> getBuildingName() {
            return buildingName;
        }
    }

    public static class BuildingName extends BaseSpinnerData {
        @Expose
        @SerializedName("building_name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

}
