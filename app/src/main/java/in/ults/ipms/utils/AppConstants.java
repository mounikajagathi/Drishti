package in.ults.ipms.utils;

/**
 * Created by Mohammed Shafeeq on 08/01/17.
 */

public final class AppConstants {
    public static final String DB_NAME = "drishti.db";
    public static final String PREF_NAME = "drishti_pref";

    public static final String MENU_TYPE_LAYERS = "Layers";
    public static final String MENU_TYPE_LEGENDS = "Legend";
    public static final String MENU_TYPE_OPACITY = "Opacity";
    public static final String MENU_TYPE_BASEMAP = "Base Maps";
    public static final String MENU_TYPE_SETTINGS = "Settings";
    public static final String MENU_TYPE_COUNT = "Count";
    public static final String MENU_TYPE_LOGOUT = "Logout";
    public static final String BASE_MAP_NAME = "OSM";
    public static final String BASE_MAP_SUB_LAYERS = "SUB_LAYERS";
    public static final String KEY_LAYER_CATEGORY_NAME = "LAYER_CATEGORY_NAME";
    public static final String KEY_LAYER_NAME = "LAYER_NAME";
    public static final String KEY_GEO_LAYER_NAME = "LAYER_NAME_GEO";
    public static final String KEY_URL = "URL";
    public static final String KEY_LAYER_STYLE = "STYLE";
    public static final String KEY_LAYER_TYPE = "KEY_LAYER_TYPE";
    public static final String FULLSCREEN_IMAGE = "FULLSCREEN_IMAGE";
    public static final String INFO_TYPE_HEADING = "INFO_TYPE_HEADING";
    public static final String INFO_TYPE_IMAGES = "INFO_TYPE_IMAGES";
    public static final String IMAGE_PATH_BUILDING_ASSET = "property";
    public static final String IMAGE_PATH_ROAD = "road";
    public static final String IMAGE_PATH_BRIDGE = "bridge";
    public static final String IMAGE_PATH_CULVERT = "culvert";
    public static final String IMAGE_PATH_DIVIDER = "divider";
    public static final String IMAGE_PATH_DRAINAGE = "drainage";
    public static final String IMAGE_PATH_PLAYGROUND = "playground";
    public static final String IMAGE_PATH_PARKING_AREA = "parking_area";
    public static final String IMAGE_PATH_ROAD_HUMP = "road_hump";
    public static final String IMAGE_PATH_ROAD_JUNCTION = "road_junction";
    public static final String IMAGE_PATH_ROAD_SIGNBOARD = "road_signboard";
    public static final String IMAGE_PATH_BUS_BAY = "bus_bay";
    public static final String IMAGE_PATH_BUS_STAND = "bus_stand";
    public static final String IMAGE_PATH_BUS_STOP = "bus_stop";
    public static final String IMAGE_PATH_CANAL = "canal";
    public static final String IMAGE_PATH_CANAL_LINE = "canal_line";
    public static final String IMAGE_PATH_GARBAGE = "garbage_point";
    public static final String IMAGE_PATH_MOBILE_TOWER = "mobile_tower";
    public static final String IMAGE_PATH_PARK = "park";
    public static final String IMAGE_PATH_STATUE = "statue";
    public static final String IMAGE_PATH_STREET_TAP = "street_tap";
    public static final String IMAGE_PATH_TANK = "tank";
    public static final String IMAGE_PATH_TAXI_STAND = "taxi_stand";
    public static final String IMAGE_PATH_TRANSFORMER = "transformer";
    public static final String IMAGE_PATH_WELL = "well";
    public static final String IMAGE_PATH_POND = "pond";
    public static final String IMAGE_PATH_PHOTO1 = "photo1";
    public static final String IMAGE_PATH_PHOTO2 = "photo2";
    public static final String IMAGE_PATH_PLAN = "plan";
    public static final String IMAGE_PATH_TAX = "plan";
    public static final String IMAGE_PATH_HIGH_LOW_MAST_LIGHT= "high_mast_light";
    public static final String GEOM_TYPE_POINT = "Point";
    public static final String GEOM_TYPE_MULTI_LINE_STRING = "MultiLineString";
    public static final String APP_TYPE_BUILDING_ASSET = "buildingasset";
    public static final String LAYER_TYPE_PROPERTY = "Property";
    public static final String LAYER_TYPE_BUILDING = "Building";
    public static final String APP_TYPE_UTILITY = "utility";
    public static final String LAYER_TYPE_ROAD = "Road";
    public static final String LAYER_TYPE_BRIDGE = "Bridge";
    public static final String LAYER_TYPE_CULVERT = "Culvert";
    public static final String LAYER_TYPE_DIVIDER = "Divider";
    public static final String LAYER_TYPE_ROAD_HUMP = "Road_Hump";
    public static final String LAYER_TYPE_ROAD_JUNCTION = "Road_Junction";
    public static final String LAYER_TYPE_ROAD_SIGNBOARD = "Road_Signboard";
    public static final String LAYER_TYPE_BUS_BAY = "Bus_Bay";
    public static final String LAYER_TYPE_BUS_STAND = "Bus_Stand";
    public static final String LAYER_TYPE_BUS_STOP = "Bus_Stop";
    public static final String LAYER_TYPE_CANAL = "Canal";
    public static final String LAYER_TYPE_CANAL_LINE = "Canal_Line";
    public static final String LAYER_TYPE_GARBAGE = "Garbage";
    public static final String LAYER_TYPE_MOBILE_TOWER = "Mobile_Tower";
    public static final String LAYER_TYPE_PARK = "Park";
    public static final String LAYER_TYPE_STADIUM = "Stadium";
    public static final String LAYER_TYPE_STATUE = "Statue";
    public static final String LAYER_TYPE_STREET_TAP = "Street_Tap";
    public static final String LAYER_TYPE_TANK = "Tank";
    public static final String LAYER_TYPE_TRANSFORMER = "Transformer";
    public static final String LAYER_TYPE_WELL = "Well";
    public static final String LAYER_TYPE_PARKING_AREA = "Parking_Area";
    public static final String LAYER_TYPE_DRAINAGE = "Drainage";
    public static final String LAYER_TYPE_PLAYGROUND = "Playground";
    public static final String APP_TYPE_WATER_BODY = "waterbody";
    public static final String APP_TYPE_OTHER_ASSET = "otherasset";
    public static final String LAYER_TYPE_POND = "Pond";
    public static final String UPDATION_STATUS_ADD = "0";
    public static final String UPDATION_STATUS_UPDATE = "4";
    public static final String PARENT_TYPE_PROPERTY = "property";
    public static final String LAYER_TYPE_HIGH_MAST_LIGHT = "High Mast Light";



    public static final int SPINNER_ID = 555;
    public static final int REQUEST_CODE_OPEN_CAMERA = 303;
    public static final int REQUEST_CODE_OPEN_GALLERY = 505;
    public static final int REQUEST_CODE_STORAGE_CAMERA_PERMISSION = 134;

    private AppConstants() {
        // This utility class is not publicly instantiable
    }

    public enum LoggedInMode {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_LOGGED_IN(3);
        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
