package in.ults.ipms.ui.base;


import in.ults.ipms.data.network.model.response.GeomPolyLine;

/**
 * Created by Mohammed Shafeeq on 20/04/18.
 */
interface IFragmentCallback {
    void launchLoginPage(boolean isBackStack,boolean isAnimate);
    void launchForgotPassword(boolean isBackStack, boolean isAnimate);
    void launchBuildAssetHome(boolean isBackStack,boolean isAnimate);
    void launchBuildingDetails(boolean isBackStack,boolean isAnimate);
    void launchPropertyDetails(boolean isBackStack,boolean isAnimate);
    void launchLocationDetails(boolean isBackStack,boolean isAnimate);
    void launchRoadDetails(boolean isBackStack,boolean isAnimate);
    void launchFloorAndRoofDetails(boolean isBackStack,boolean isAnimate);
    void launchEstablishmentDetails(boolean isBackStack,boolean isAnimate);
    void launchSurveyDetails(boolean isBackStack,boolean isAnimate);
    void launchPropertyOtherDetails(boolean isBackStack,boolean isAnimate);
    void launchTaxDetails(boolean isBackStack,boolean isAnimate);
    void launchTenantDetails(boolean isBackStack,boolean isAnimate);
    void launchOwnerDetails(boolean isBackStack,boolean isAnimate);
    void launchMemberDetails(boolean isBackStack,boolean isAnimate);
    void launchMemberList(boolean isBackStack,boolean isAnimate);
    void launchTaxList(boolean isBackStack,boolean isAnimate);
    void launchOwnerList(boolean isBackStack,boolean isAnimate);
    void launchRoadMap(boolean isBackStack,boolean isAnimate);
    void launchRoadDetails(boolean isBackStack, boolean isAnimate, String length, GeomPolyLine geom);
    void launchUtilityHome(boolean isBackStack, boolean isAnimate);
    void launchBridgeDetails(boolean isBackStack, boolean isAnimate);
    void launchCulvertDetails(boolean isBackStack, boolean isAnimate);
    void launchDividerDetails(boolean isBackStack, boolean isAnimate);
    void launchParkingDetails(boolean isBackStack, boolean isAnimate);
    void launchPlaygroundDetails(boolean isBackStack, boolean isAnimate);
    void launchRoadHumpMap(boolean isBackStack,boolean isAnimate);
    void launchRoadJunctionMap(boolean isBackStack,boolean isAnimate);
    void launchRoadSignboardMap(boolean isBackStack,boolean isAnimate);
    void launchDrainageMap(boolean isBackStack,boolean isAnimate);
    void launchDrainageDetails(boolean isBackStack, boolean isAnimate, GeomPolyLine geom);
    void launchWaterBodyHome(boolean isBackStack, boolean isAnimate);
    void launchPondDetails(boolean isBackStack, boolean isAnimate);

    void launchBusBayMap(boolean isBackStack, boolean isAnimate);
    void launchBusStandMap(boolean isBackStack, boolean isAnimate);
    void launchBusStopMap(boolean isBackStack, boolean isAnimate);
    void launchCanalDetails(boolean isBackStack, boolean isAnimate);
    void launchCanalLineDetails(boolean isBackStack, boolean isAnimate);
    void launchGarbageDetails(boolean isBackStack, boolean isAnimate);
    void launchMobileTowerDetails(boolean isBackStack, boolean isAnimate);
    void launchParkDetails(boolean isBackStack, boolean isAnimate);
    void launchStadiumDetails(boolean isBackStack, boolean isAnimate);
    void launchStatueDetails(boolean isBackStack, boolean isAnimate);
    void launchStreetTapDetails(boolean isBackStack, boolean isAnimate);
    void launchTankDetails(boolean isBackStack, boolean isAnimate);
    void launchTaxiStandDetails(boolean isBackStack, boolean isAnimate);
    void launchTransformerDetails(boolean isBackStack, boolean isAnimate);
    void launchWellDetails(boolean isBackStack, boolean isAnimate);

    void launchOtherAssetsHome(boolean isBackStack, boolean isAnimate);
    void launchHighLowMastLight(boolean isBackStack, boolean isAnimate);
}
