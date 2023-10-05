package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.ipms.data.network.model.base.BaseResponse;
import in.ults.ipms.data.network.model.base.BaseSpinnerData;

public class UtilitySpinnerResponse extends BaseResponse {
    @Expose
    @SerializedName("data")
    private UtilitySpinnerResponse.Data data;

    public Data getData() {
        return data;
    }

    public static class Data {
        @Expose
        @SerializedName("drop_down_values")
        private UtilitySpinnerResponse.DropDownValues dropDownValues;

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
        @SerializedName("Bridge")
        private DashboardResponse.LayerCategoryChild bridge;


        @Expose
        @SerializedName("Culvert")
        private DashboardResponse.LayerCategoryChild culvert;


        @Expose
        @SerializedName("Divider")
        private DashboardResponse.LayerCategoryChild divider;


        @Expose
        @SerializedName("Drainage")
        private DashboardResponse.LayerCategoryChild drainage;


        @Expose
        @SerializedName("Parking Area")
        private DashboardResponse.LayerCategoryChild parkingArea;


        @Expose
        @SerializedName("Playground")
        private DashboardResponse.LayerCategoryChild playground;


        @Expose
        @SerializedName("Road")
        private DashboardResponse.LayerCategoryChild road;


        @Expose
        @SerializedName("Road Hump")
        private DashboardResponse.LayerCategoryChild roadHump;


        @Expose
        @SerializedName("Road Junction")
        private DashboardResponse.LayerCategoryChild roadJunction;


        @Expose
        @SerializedName("Road Sign Board")
        private DashboardResponse.LayerCategoryChild roadSignBoard;

        @Expose
        @SerializedName("Bus Bay")
        private DashboardResponse.LayerCategoryChild busBay;

        @Expose
        @SerializedName("Bus Stand")
        private DashboardResponse.LayerCategoryChild busStand;

        @Expose
        @SerializedName("Bus Stop")
        private DashboardResponse.LayerCategoryChild busStop;

        @Expose
        @SerializedName("Canal")
        private DashboardResponse.LayerCategoryChild canal;

        @Expose
        @SerializedName("Canal (Line)")
        private DashboardResponse.LayerCategoryChild canalLine;

        @Expose
        @SerializedName("Garbage Collection Points")
        private DashboardResponse.LayerCategoryChild garbage;

        @Expose
        @SerializedName("Mobile Tower")
        private DashboardResponse.LayerCategoryChild mobileTower;

        @Expose
        @SerializedName("Park")
        private DashboardResponse.LayerCategoryChild park;

        @Expose
        @SerializedName("Stadium")
        private DashboardResponse.LayerCategoryChild stadium;

        @Expose
        @SerializedName("Statue")
        private DashboardResponse.LayerCategoryChild statue;

        @Expose
        @SerializedName("Street Tap")
        private DashboardResponse.LayerCategoryChild streetTap;

        @Expose
        @SerializedName("Taxi Stand")
        private DashboardResponse.LayerCategoryChild taxiStand;

        @Expose
        @SerializedName("Transformer")
        private DashboardResponse.LayerCategoryChild transformer;

        @Expose
        @SerializedName("Water Tank")
        private DashboardResponse.LayerCategoryChild tank;

        @Expose
        @SerializedName("Well")
        private DashboardResponse.LayerCategoryChild well;

        public DashboardResponse.LayerCategoryChild getBridge() {
            return bridge;
        }

        public DashboardResponse.LayerCategoryChild getCulvert() {
            return culvert;
        }

        public DashboardResponse.LayerCategoryChild getDivider() {
            return divider;
        }

        public DashboardResponse.LayerCategoryChild getDrainage() {
            return drainage;
        }

        public DashboardResponse.LayerCategoryChild getParkingArea() {
            return parkingArea;
        }

        public DashboardResponse.LayerCategoryChild getPlayground() {
            return playground;
        }

        public DashboardResponse.LayerCategoryChild getRoad() {
            return road;
        }

        public DashboardResponse.LayerCategoryChild getRoadHump() {
            return roadHump;
        }

        public DashboardResponse.LayerCategoryChild getRoadJunction() {
            return roadJunction;
        }

        public DashboardResponse.LayerCategoryChild getRoadSignBoard() {
            return roadSignBoard;
        }

        public DashboardResponse.LayerCategoryChild getBusBay() {
            return busBay;
        }

        public DashboardResponse.LayerCategoryChild getBusStand() {
            return busStand;
        }

        public DashboardResponse.LayerCategoryChild getBusStop() {
            return busStop;
        }

        public DashboardResponse.LayerCategoryChild getCanal() {
            return canal;
        }

        public DashboardResponse.LayerCategoryChild getCanalLine() {
            return canalLine;
        }

        public DashboardResponse.LayerCategoryChild getGarbage() {
            return garbage;
        }

        public DashboardResponse.LayerCategoryChild getMobileTower() {
            return mobileTower;
        }

        public DashboardResponse.LayerCategoryChild getPark() {
            return park;
        }

        public DashboardResponse.LayerCategoryChild getTank() {
            return tank;
        }

        public DashboardResponse.LayerCategoryChild getStadium() {
            return stadium;
        }

        public DashboardResponse.LayerCategoryChild getStatue() {
            return statue;
        }

        public DashboardResponse.LayerCategoryChild getStreetTap() {
            return streetTap;
        }

        public DashboardResponse.LayerCategoryChild getTaxiStand() {
            return taxiStand;
        }

        public DashboardResponse.LayerCategoryChild getTransformer() {
            return transformer;
        }

        public DashboardResponse.LayerCategoryChild getWell() {
            return well;
        }
    }

    public static class DropDownValues extends BaseSpinnerData {


        @Expose
        @SerializedName("maintained_by")
        private ArrayList<MaintainedBy> maintainedBy;

        @Expose
        @SerializedName("road_categories")
        private ArrayList<RoadCategories> roadCategories;

        @Expose
        @SerializedName("road_types")
        private ArrayList<RoadTypes> roadTypes;

        @Expose
        @SerializedName("road_widths")
        private ArrayList<RoadWidths> roadWidths;

        @Expose
        @SerializedName("ward")
        private ArrayList<Ward> ward;

        @Expose
        @SerializedName("signboard_category")
        private ArrayList<SignboardCategory> signboardCategory;

        @Expose
        @SerializedName("drainage_type")
        private ArrayList<DrainageType> drainageType;

        @Expose
        @SerializedName("drainage_material")
        private ArrayList<DrainageMaterial> drainageMaterial;

        @Expose
        @SerializedName("divider_material")
        private ArrayList<DividerMaterial> dividerMaterial;

        @Expose
        @SerializedName("bridge_sub_type")
        private ArrayList<BridgeSubType> bridgeSubType;

        @Expose
        @SerializedName("bridge_material")
        private ArrayList<BridgeMaterial> bridgeMaterial;

        @Expose
        @SerializedName("ground_type")
        private ArrayList<GroundType> groundTypes;

        @Expose
        @SerializedName("parking_type")
        private ArrayList<ParkingType> parkingTypes;

        @Expose
        @SerializedName("ownership")
        private ArrayList<OwnerShip> ownership;

        @Expose
        @SerializedName("building_status")
        private ArrayList<BuildingStatus> buildingStatus;

        @Expose
        @SerializedName("park_type")
        private ArrayList<ParkType> parkType;

        @Expose
        @SerializedName("tank_type")
        private ArrayList<TankType> tankType;

        @Expose
        @SerializedName("building_usage")
        private ArrayList<BuildingUsage> buildingUsage;

        @Expose
        @SerializedName("well_type")
        private ArrayList<WellType> wellType;

        public ArrayList<MaintainedBy> getMaintainedBy() {
            return maintainedBy;
        }

        public ArrayList<RoadCategories> getRoadCategories() {
            return roadCategories;
        }

        public ArrayList<RoadTypes> getRoadTypes() {
            return roadTypes;
        }

        public ArrayList<RoadWidths> getRoadWidths() {
            return roadWidths;
        }

        public ArrayList<Ward> getWard() {
            return ward;
        }

        public ArrayList<BuildingStatus> getBuildingStatus() {
            return buildingStatus;
        }

        public ArrayList<BuildingUsage> getBuildingUsage() {
            return buildingUsage;
        }

        public ArrayList<SignboardCategory> getSignboardCategory() {
            return signboardCategory;
        }

        public ArrayList<DrainageType> getDrainageType() {
            return drainageType;
        }

        public ArrayList<DrainageMaterial> getDrainageMaterial() {
            return drainageMaterial;
        }

        public ArrayList<DividerMaterial> getDividerMaterial() {
            return dividerMaterial;
        }

        public ArrayList<BridgeSubType> getBridgeSubType() {
            return bridgeSubType;
        }

        public ArrayList<BridgeMaterial> getBridgeMaterial() {
            return bridgeMaterial;
        }

        public ArrayList<GroundType> getGroundTypes() {
            return groundTypes;
        }

        public ArrayList<ParkingType> getParkingTypes() {
            return parkingTypes;
        }

        public ArrayList<OwnerShip> getOwnership() {
            return ownership;
        }

        public ArrayList<ParkType> getParkType() {
            return parkType;
        }

        public ArrayList<TankType> getTankType() {
            return tankType;
        }

        public ArrayList<WellType> getWellType() {
            return wellType;
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

    public static class RoadCategories extends BaseSpinnerData {
        @Expose
        @SerializedName("road_category")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class RoadTypes extends BaseSpinnerData {
        @Expose
        @SerializedName("road_type")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class RoadWidths extends BaseSpinnerData {
        @Expose
        @SerializedName("road_width")
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

    public static class OwnerShip extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class BridgeMaterial extends BaseSpinnerData {
        @Expose
        @SerializedName("road_type")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class BridgeSubType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class DividerMaterial extends BaseSpinnerData {
        @Expose
        @SerializedName("road_type")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class DrainageMaterial extends BaseSpinnerData {
        @Expose
        @SerializedName("road_type")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class DrainageType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class SignboardCategory extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class GroundType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class ParkingType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class BuildingStatus extends BaseSpinnerData {
        @Expose
        @SerializedName("status")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class BuildingUsage extends BaseSpinnerData {
        @Expose
        @SerializedName("bldg_usage")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class ParkType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class TankType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

    public static class WellType extends BaseSpinnerData {
        @Expose
        @SerializedName("name")
        private String spinnerTitle;

        @Override
        public String getSpinnerTitle() {
            return spinnerTitle;
        }
    }

}
