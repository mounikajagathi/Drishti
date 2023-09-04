package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.ults.ipms.data.network.model.base.BaseResponse;

public class DashboardResponse extends BaseResponse {

    @Expose
    @SerializedName("data")
    private DashboardResponse.Data data;

    public Data getData() {
        return data;
    }

    //Inner Data Class
    public static class Data {

        @Expose
        @SerializedName("basicDetails")
        private DashboardResponse.BasicDetails basicDetails;

        public BasicDetails getBasicDetails() {
            return basicDetails;
        }
    }


    public static class BasicDetails {

        @Expose
        @SerializedName("localBody")
        private DashboardResponse.LocalBody localBody;

        @Expose
        @SerializedName("wardBoundary")
        private DashboardResponse.LayerCategoryChild wardBoundary;

        @Expose
        @SerializedName("userDetails")
        private DashboardResponse.UserDetails userDetails;

        @Expose
        @SerializedName("role")
        private DashboardResponse.Role role;

        public LocalBody getLocalBody() {
            return localBody;
        }

        public UserDetails getUserDetails() {
            return userDetails;
        }

        public Role getRole() {
            return role;
        }

        public LayerCategoryChild getWardBoundary() {
            return wardBoundary;
        }
    }

    public static class LocalBody {

        @Expose
        @SerializedName("status")
        private int status;
        @Expose
        @SerializedName("local_body_name")
        private String localBodyName;
        @Expose
        @SerializedName("local_body_id")
        private int localBodyId;
        @Expose
        @SerializedName("layer_to_search")
        private String layerToSearch;
        @Expose
        @SerializedName("initial_zoom")
        private int initialZoom;
        @Expose
        @SerializedName("initial_centre")
        private String initialCentre;
        @Expose
        @SerializedName("image_url")
        private String imageUrl;
        @Expose
        @SerializedName("default_zoom")
        private int defaultZoom;
        @Expose
        @SerializedName("default_centre")
        private String defaultCentre;
        @Expose
        @SerializedName("searchAttributes")
        private ArrayList<SearchAttributes> searchAttributes;
        @Expose
        @SerializedName("baseLayers")
        private ArrayList<BaseLayers> baseLayers;

        @Expose
        @SerializedName("layerCategory")
        private ArrayList<LayerCategory> layerCategory;


        public int getStatus() {
            return status;
        }

        public String getLocalBodyName() {
            return localBodyName;
        }

        public int getLocalBodyId() {
            return localBodyId;
        }

        public String getLayerToSearch() {
            return layerToSearch;
        }

        public int getInitialZoom() {
            return initialZoom;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getDefaultZoom() {
            return defaultZoom;
        }

        public String getInitialCentre() {
            return initialCentre == null ? "" : initialCentre;
        }

        public String getDefaultCentre() {
            return defaultCentre == null ? "" : defaultCentre;
        }

        public double getDefaultLat() {
            String[] tempArray = defaultCentre.split(",");
            return tempArray.length == 2 ? Double.parseDouble(tempArray[1]) : 0;
        }

        public double getDefaultLon() {
            String[] tempArray = defaultCentre.split(",");
            return tempArray.length == 2 ? Double.parseDouble(tempArray[0]) : 0;
        }

        public double getInitialLat() {
            String[] tempArray = initialCentre.split(",");
            return tempArray.length == 2 ? Double.parseDouble(tempArray[0]) : 0;
        }

        public double getInitialLon() {
            String[] tempArray = initialCentre.split(",");
            return tempArray.length == 2 ? Double.parseDouble(tempArray[1]) : 0;
        }

        public ArrayList<SearchAttributes> getSearchAttributes() {
            return searchAttributes;
        }

        public ArrayList<BaseLayers> getBaseLayers() {
            return baseLayers;
        }

        public ArrayList<LayerCategory> getLayerCategory() {
            return layerCategory;
        }
    }

    public static class UserDetails {

        @Expose
        @SerializedName("email")
        private String email;
        @Expose
        @SerializedName("firstname")
        private String firstName;
        @Expose
        @SerializedName("lastname")
        private String lastName;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("status")
        private int status;
        @Expose
        @SerializedName("user_details_id")
        private int userDetailsId;


        public String getEmail() {
            return email;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getName() {
            return name;
        }

        public int getStatus() {
            return status;
        }

        public int getUserDetailsId() {
            return userDetailsId;
        }
    }


    public static class SearchAttributes {
        @Expose
        @SerializedName("column_name")
        private String columnName;
        @Expose
        @SerializedName("display_name")
        private String displayName;

        public SearchAttributes(String columnName, String displayName) {
            this.columnName = columnName;
            this.displayName = displayName;
        }

        public String getColumnName() {
            return columnName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }


    public static class BaseLayers {
        @Expose
        @SerializedName("index")
        private int index;
        @Expose
        @SerializedName("label")
        private String label;

        @Expose
        @SerializedName("layer_name")
        private String layerName;
        @Expose
        @SerializedName("layer_type")
        private String layerType;

        @Expose
        @SerializedName("opacity")
        private int opacity;
        @Expose
        @SerializedName("type")
        private String type;

        @Expose
        @SerializedName("url")
        private String url;
        @Expose
        @SerializedName("visibility")
        private boolean visibility;

        public int getIndex() {
            return index;
        }

        public String getLabel() {
            return label;
        }

        public String getLayerName() {
            return layerName;
        }

        public String getLayerType() {
            return layerType;
        }

        public float getOpacity() {
            return (float) opacity / 100;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public boolean isVisibility() {
            return visibility;
        }
    }


    public static class LayerCategory {
        @Expose
        @SerializedName("label")
        private String label;

        @Expose
        @SerializedName("value")
        private String value;

        @Expose
        @SerializedName("layer_category_id")
        private int layerCategoryId;

        @Expose
        @SerializedName("layer_category_order")
        private int layerCategoryOrder;

        @Expose
        @SerializedName("local_body_id")
        private int localBodyId;

        @Expose
        @SerializedName("status")
        private int status;

        @Expose
        @SerializedName("children")
        private ArrayList<LayerCategoryChild> children;


        private boolean isSelected = false;


        public String getLabel() {
            return label;
        }

        public String getValue() {
            return value;
        }

        public int getLayerCategoryId() {
            return layerCategoryId;
        }

        public int getLayerCategoryOrder() {
            return layerCategoryOrder;
        }

        public int getLocalBodyId() {
            return localBodyId;
        }

        public int getStatus() {
            return status;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public ArrayList<LayerCategoryChild> getChildren() {
            return children;
        }
    }


    public static class LayerCategoryChild {

        @Expose
        @SerializedName("label")
        private String label;

        @Expose
        @SerializedName("value")
        private String value;

        @Expose
        @SerializedName("layer_category_id")
        private int layerCategoryId;

        @Expose
        @SerializedName("layer_id")
        private int layerId;

        @Expose
        @SerializedName("layer_order")
        private int layerOrder;

        @Expose
        @SerializedName("local_body_id")
        private int localBodyId;

        @Expose
        @SerializedName("layer_name")
        private String layerName;

        @Expose
        @SerializedName("layer_type")
        private String layerType;

        @Expose
        @SerializedName("status")
        private int status;

        @Expose
        @SerializedName("style")
        private String style;

        @Expose
        @SerializedName("type")
        private String type;

        @Expose
        @SerializedName("url")
        private String url;

        @Expose
        @SerializedName("cql_filter")
        private String cqlFilter;

        @Expose
        @SerializedName("visibility")
        private boolean visibility;

        @Expose
        @SerializedName("count")
        private String count;

        private boolean isSelected = false;


        public String getLabel() {
            return label;
        }

        public String getValue() {
            return value;
        }

        public int getLayerCategoryId() {
            return layerCategoryId;
        }

        public int getLayerId() {
            return layerId;
        }

        public int getLayerOrder() {
            return layerOrder;
        }

        public int getLocalBodyId() {
            return localBodyId;
        }

        public String getLayerName() {
            return layerName;
        }

        public String getLayerType() {
            return layerType;
        }

        public int getStatus() {
            return status;
        }

        public String getStyle() {
            return style;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public boolean isVisibility() {
            return visibility;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setLayerName(String layerName) {
            this.layerName = layerName;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setLayerType(String layerType) {
            this.layerType = layerType;
        }

        public String getCqlFilter() {
            return cqlFilter;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }


    public static class Role {

        @Expose
        @SerializedName("menu")
        private ArrayList<Menu> menu;

        public ArrayList<Menu> getMenu() {
            ArrayList<Menu> newMenu = new ArrayList<>();
            if (menu != null) {
                Menu settingsMenu = null;
                for (DashboardResponse.Menu item : menu) {
                    if (item.getMenuName() != null && item.getMenuName().equalsIgnoreCase("settings")) {
                        settingsMenu = item;

                    } else {
                        newMenu.add(item);
                    }
                }
                if (settingsMenu != null) {
                    newMenu.add(settingsMenu);
                }
            }
            return newMenu;
        }
    }

    public static class Menu {

        @Expose
        @SerializedName("menu_name")
        private String menuName;

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }
    }


}