package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.ipms.data.network.model.base.BaseResponse;
import in.ults.ipms.data.network.model.base.BaseSpinnerData;

public class OtherAssetsSpinnerResponse extends BaseResponse {
    @Expose
    @SerializedName("data")
    private OtherAssetsSpinnerResponse.Data data;

    public Data getData() {
        return data;
    }

    public static class Data {
        @Expose
        @SerializedName("drop_down_values")
        private OtherAssetsSpinnerResponse.DropDownValues dropDownValues;

        @Expose
        @SerializedName("layer_details")
        private LayerDetails layerDetails;

        public DropDownValues getDropDownValues() {
            return dropDownValues;
        }

        public LayerDetails getLayerDetails() {
            return layerDetails;
        }
    }


    public static class LayerDetails extends BaseSpinnerData {

        @Expose
        @SerializedName("High Mast & Low Mast Light")
        private DashboardResponse.LayerCategoryChild highMastLowMastLight;

        public DashboardResponse.LayerCategoryChild getHighMastLowMastLight() {
            return highMastLowMastLight;
        }
    }

    public static class DropDownValues extends BaseSpinnerData {

        @Expose
        @SerializedName("work_status")
        private ArrayList<WorkStatus> workStatuses;

        @Expose
        @SerializedName("type")
        private ArrayList<Type> types;

        @Expose
        @SerializedName("light_type")
        private ArrayList<LightType> lightTypes;

        @Expose
        @SerializedName("ward")
        private ArrayList<Ward> ward;

        public ArrayList<WorkStatus> getWorkStatuses() {
            return workStatuses;
        }

        public ArrayList<Type> getTypes() {
            return types;
        }

        public ArrayList<LightType> getLightTypes() {
            return lightTypes;
        }

        public ArrayList<Ward> getWard() {
            return ward;
        }
    }

    public static class LightType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Type extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class WorkStatus extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class Ward extends BaseSpinnerData {
        @Expose
        @SerializedName("ward_name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

}
