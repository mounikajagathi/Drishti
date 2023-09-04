package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.ipms.data.network.model.base.BaseResponse;
import in.ults.ipms.data.network.model.base.BaseSpinnerData;

public class WaterBodySpinnerResponse extends BaseResponse {
    @Expose
    @SerializedName("data")
    private WaterBodySpinnerResponse.Data data;

    public Data getData() {
        return data;
    }

    public static class Data {
        @Expose
        @SerializedName("drop_down_values")
        private WaterBodySpinnerResponse.DropDownValues dropDownValues;

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
        @SerializedName("Pond")
        private DashboardResponse.LayerCategoryChild pond;

        public DashboardResponse.LayerCategoryChild getPond() {
            return pond;
        }
    }

    public static class DropDownValues extends BaseSpinnerData {

        @Expose
        @SerializedName("maintained_by")
        private ArrayList<MaintainedBy> maintainedBy;

        @Expose
        @SerializedName("usage")
        private ArrayList<Usage> usages;

        @Expose
        @SerializedName("odour")
        private ArrayList<Odour> odours;

        @Expose
        @SerializedName("pond_status")
        private ArrayList<PondStatus> pondStatuses;

        @Expose
        @SerializedName("pond_type")
        private ArrayList<PondType> pondTypes;

        @Expose
        @SerializedName("present_condition")
        private ArrayList<PresentCondition> presentConditions;

        @Expose
        @SerializedName("Nature")
        private ArrayList<Nature> natures;

        @Expose
        @SerializedName("sidewall")
        private ArrayList<Sidewall> sidewalls;

        @Expose
        @SerializedName("sidewall_Type")
        private ArrayList<SidewallType> sidewallTypes;

        @Expose
        @SerializedName("pond_condition")
        private ArrayList<PondCondition> pondConditions;

        @Expose
        @SerializedName("ward")
        private ArrayList<Ward> ward;

        public ArrayList<MaintainedBy> getMaintainedBy() {
            return maintainedBy;
        }

        public ArrayList<Usage> getUsages() {
            return usages;
        }

        public ArrayList<Odour> getOdours() {
            return odours;
        }

        public ArrayList<PondStatus> getPondStatuses() {
            return pondStatuses;
        }

        public ArrayList<PondType> getPondTypes() {
            return pondTypes;
        }

        public ArrayList<PresentCondition> getPresentConditions() {
            return presentConditions;
        }

        public ArrayList<Nature> getNatures() {
            return natures;
        }

        public ArrayList<Sidewall> getSidewalls() {
            return sidewalls;
        }

        public ArrayList<SidewallType> getSidewallTypes() {
            return sidewallTypes;
        }

        public ArrayList<PondCondition> getPondConditions() {
            return pondConditions;
        }

        public ArrayList<Ward> getWard() {
            return ward;
        }
    }
    public static class MaintainedBy extends BaseSpinnerData {
        @Expose
        @SerializedName("maintained_by")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }
    public static class Usage extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }
    public static class Odour extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class PondStatus extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }
    public static class PondType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }
    public static class PresentCondition extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }
    public static class Nature extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }
    public static class Sidewall extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }
    public static class SidewallType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }
    public static class PondCondition extends BaseSpinnerData {
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
