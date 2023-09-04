package in.ults.ipms.singletons;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import in.ults.ipms.data.network.model.response.BuildingAssetSpinnerResponse;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.data.network.model.response.BuildingNameSpinnerResponse;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.data.network.model.response.FeatureDataResponse;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.data.network.model.response.UtilitySpinnerResponse;
import in.ults.ipms.data.network.model.response.WaterBodySpinnerResponse;

/**
 * Created by Mohammed Shafeeq on 16/08/18.
 */
public class AppCacheData {
    private static final AppCacheData ourInstance = new AppCacheData();

    private DashboardResponse.BasicDetails dashboardDetails;
    private BuildingAssetSpinnerResponse.Data buildingAssetSpinnerData;
    private UtilitySpinnerResponse.Data utilitySpinnerData;
    private WaterBodySpinnerResponse.Data waterBodySpinnerData;
    private FetchAssetDataResponse.Data buildingAssetData;
    private FetchAssetDataResponse.Data buildingDetailsData;
    private ArrayList<BuildingNameSpinnerResponse.BuildingName> buildingNameSpinnerData;
    private FeatureDataResponse.Data selectedFeatureInfoDetails;
    private BuildingAssets.PropertyDetails newProperty;

    private Set<String> selectedDashboardLayerParent;
    private Set<String> selectedDashboardLayerChild;
    private boolean isAssetUpdate = false;
    private String imageBaseURL = "";
    private boolean isBuildingAssetPropertyAdded = false;

    private AppCacheData() {
        selectedDashboardLayerParent = new HashSet<>();
        selectedDashboardLayerChild = new HashSet<>();
    }

    public boolean isAssetUpdate() {
        return isAssetUpdate;
    }

    public void setAssetUpdate(boolean buildingAssetUpdate) {
        isAssetUpdate = buildingAssetUpdate;
    }

    public boolean isBuildingAssetPropertyAdded() {
        return isBuildingAssetPropertyAdded;
    }

    public void setBuildingAssetPropertyAdded(boolean buildingAssetPropertyAdded) {
        isBuildingAssetPropertyAdded = buildingAssetPropertyAdded;
    }

    public Set<String> getSelectedDashboardLayerParent() {
        return selectedDashboardLayerParent;
    }

    public Set<String> getSelectedDashboardLayerChild() {
        return selectedDashboardLayerChild;
    }

    public static AppCacheData getOurInstance() {
        return ourInstance;
    }

    public DashboardResponse.BasicDetails getDashboardDetails() {
        return dashboardDetails;
    }

    public void setDashboardDetails(DashboardResponse.BasicDetails dashboardDetails) {
        this.dashboardDetails = dashboardDetails;
    }

    public BuildingAssetSpinnerResponse.Data getBuildingAssetSpinnerData() {
        return buildingAssetSpinnerData;
    }

    public String getImageBaseURL() {
        return imageBaseURL;
    }

    public void setImageBaseURL(String imageBaseURL) {
        this.imageBaseURL = imageBaseURL;
    }

    public void setBuildingAssetSpinnerData(BuildingAssetSpinnerResponse.Data buildingAssetSpinnerData) {
        this.buildingAssetSpinnerData = buildingAssetSpinnerData;
    }

    public ArrayList<BuildingNameSpinnerResponse.BuildingName> getBuildingNameSpinnerData() {
        return buildingNameSpinnerData;
    }

    public void setBuildingNameSpinnerData(ArrayList<BuildingNameSpinnerResponse.BuildingName> buildingNameSpinnerData) {
        this.buildingNameSpinnerData = buildingNameSpinnerData;
    }

    public FeatureDataResponse.Data getSelectedFeatureInfoDetails() {
        return selectedFeatureInfoDetails;
    }

    public void setSelectedFeatureInfoDetails(FeatureDataResponse.Data selectedFeatureInfoDetails) {
        this.selectedFeatureInfoDetails = selectedFeatureInfoDetails;
    }

    public FetchAssetDataResponse.Data getBuildingAssetData() {
        return buildingAssetData;
    }

    public void setBuildingAssetData(FetchAssetDataResponse.Data buildingAssetData) {
        this.buildingAssetData = buildingAssetData;
    }

    public UtilitySpinnerResponse.Data getUtilitySpinnerData() {
        return utilitySpinnerData;
    }

    public void setUtilitySpinnerData(UtilitySpinnerResponse.Data utilitySpinnerData) {
        this.utilitySpinnerData = utilitySpinnerData;
    }

    public FetchAssetDataResponse.Data getBuildingDetailsData() {
        return buildingDetailsData;
    }

    public void setBuildingDetailsData(FetchAssetDataResponse.Data buildingDetailsData) {
        this.buildingDetailsData = buildingDetailsData;
    }

    public WaterBodySpinnerResponse.Data getWaterBodySpinnerData() {
        return waterBodySpinnerData;
    }

    public void setWaterBodySpinnerData(WaterBodySpinnerResponse.Data waterBodySpinnerData) {
        this.waterBodySpinnerData = waterBodySpinnerData;
    }

    public BuildingAssets.PropertyDetails getNewProperty() {
        return newProperty;
    }

    public void setNewProperty(BuildingAssets.PropertyDetails newProperty) {
        this.newProperty = newProperty;
    }
}
