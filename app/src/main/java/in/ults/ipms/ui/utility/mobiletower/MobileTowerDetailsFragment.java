package in.ults.ipms.ui.utility.mobiletower;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import in.ults.ipms.BuildConfig;
import in.ults.ipms.R;
import in.ults.ipms.adapters.CommonSpinnerAdapter;
import in.ults.ipms.data.StaticData;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.data.network.model.response.GeomPoint;
import in.ults.ipms.data.network.model.response.UtilityAssets;
import in.ults.ipms.data.network.model.response.UtilitySpinnerResponse;
import in.ults.ipms.databinding.FragmentMobileTowerDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BaseFragment;
import in.ults.ipms.ui.mapoverlays.MapOverlayBottomSheet;
import in.ults.ipms.utils.AppConstants;

public class MobileTowerDetailsFragment extends BaseFragment<FragmentMobileTowerDetailsBinding> implements IMobileTowerDetailsView, BaseActivity.ImagePickListener {

    public static final String TAG = MobileTowerDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_BUILDING_NAME = 1;
    public static final int ERROR_TYPE_PLACE = 2;
    public static final int ERROR_TYPE_OWNER_NAME = 3;
    public static final int ERROR_TYPE_OWNER_ADDRESS = 4;
    public static final int ERROR_TYPE_OWNER_MOBILE = 5;
    public static final int ERROR_TYPE_BUILDING_STATUS = 6;
    public static final int ERROR_TYPE_BUILDING_USAGE = 7;
    public static final int ERROR_TYPE_NEW_PROPERTY_ID = 8;
    public static final int ERROR_TYPE_OLD_PROPERTY_ID = 9;
    public static final int ERROR_TYPE_YEAR_OF_CONSTRUCTION = 10;
    public static final int ERROR_TYPE_ELECTRIC_CONNECTIVITY = 11;
    public static final int ERROR_TYPE_UNIQUE_ID = 12;
    public static final int ERROR_TYPE_SERVICE_PROVIDER = 13;
    public static final int ERROR_TYPE_CONSUMER_NO = 14;
    public static final int ERROR_TYPE_ROAD_WIDTH = 15;
    public static final int ERROR_TYPE_ROAD_TYPE = 16;
    public static final int ERROR_TYPE_WARD_NO = 17;
    public static final int ERROR_TYPE_REMARKS = 18;
    public static final int ERROR_TYPE_PHOTO_1 = 19;
    public static final int ERROR_TYPE_PHOTO_2 = 20;
    public static final int ERROR_TYPE_MOBILE_TOWER_LOCATION = 21;

    public static MobileTowerDetailsFragment newInstance() {
        return new MobileTowerDetailsFragment();
    }

    @Inject
    IMobileTowerDetailsPresenter<IMobileTowerDetailsView, IMobileTowerDetailsInteractor> presenter;

    CommonSpinnerAdapter<UtilitySpinnerResponse.Ward> wardAdapter;

    CommonSpinnerAdapter<UtilitySpinnerResponse.BuildingStatus> buildingStatusAdapter;

    CommonSpinnerAdapter<UtilitySpinnerResponse.BuildingUsage> buildingUsageAdapter;

    private ArcGISMap map;
    private Viewpoint initialViewPoint;
    private GraphicsOverlay pointOverlay;
    private double latitude = 0.0;
    private double longitude = 0.0;
    Set<String> selectedMapLayers;
    ArrayList<DashboardResponse.LayerCategoryChild> layers;
    private String photo;
    private String photo2;
    static final int REQUEST_CODE_IMAGE = 101;
    static final int REQUEST_CODE_IMAGE_2 = 102;

    @Override
    protected FragmentMobileTowerDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentMobileTowerDetailsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.mobile_tower_details_title);
    }

    @Override
    protected void initInjector() {
        getFragmentComponent().inject(this);

    }

    @Override
    protected void attachPresenter() {
        presenter.onAttach(this);

    }

    @Override
    protected void detachPresenter() {
        presenter.onDetach();

    }

    @Override
    protected void init(View view) {
        selectedMapLayers = new HashSet<>();
        layers = new ArrayList<>();
        setBaseMapAndDefaultCentre();
        getViewBinding().imgMobileTower1.setOnClickListener(v -> captureImage(REQUEST_CODE_IMAGE));
        getViewBinding().imgMobileTower2.setOnClickListener(v -> captureImage(REQUEST_CODE_IMAGE_2));
        getViewBinding().btnSubmit.setOnClickListener(v -> submitOnClick());
        getViewBinding().includeMiniMapProperty.fabLayers.setOnClickListener(view1 -> {
            MapOverlayBottomSheet layersBottomSheet = MapOverlayBottomSheet.newInstance();
            layersBottomSheet.setArcGISMap(map);
            layersBottomSheet.setLayerList(layers);
            layersBottomSheet.setSelectedLayersIDs(selectedMapLayers);
            layersBottomSheet.show(getParentFragmentManager(), MapOverlayBottomSheet.TAG);
        });
        if (AppCacheData.getOurInstance().getUtilitySpinnerData() != null && AppCacheData.getOurInstance().getUtilitySpinnerData().getDropDownValues() != null) {
            wardAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srWardNo, AppCacheData.getOurInstance().getUtilitySpinnerData().getDropDownValues().getWard());
            buildingStatusAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srBuildingStatus, AppCacheData.getOurInstance().getUtilitySpinnerData().getDropDownValues().getBuildingStatus());
            buildingUsageAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srBuildingUsage, AppCacheData.getOurInstance().getUtilitySpinnerData().getDropDownValues().getBuildingUsage());
        }
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            setEditData();
        }

    }

    @Override
    protected void setUp(View view) {
        super.setUp(view);
        getViewBinding().includeMiniMapProperty.miniMapDelete.setOnClickListener(v -> {
            getViewBinding().includeMiniMapProperty.etMiniMapSearch.setText("");
            longitude = 0.0;
            latitude = 0.0;
            if (pointOverlay != null) {
                pointOverlay.getGraphics().clear();
            }
        });
        getViewBinding().includeMiniMapProperty.miniMapExtend.setOnClickListener(v -> {
            if (initialViewPoint != null) {
                getViewBinding().includeMiniMapProperty.miniMap.setViewpoint(initialViewPoint);
            }
        });
        getViewBinding().includeMiniMapProperty.miniMapSearch.setOnClickListener(v -> {
            String latLon = getViewBinding().includeMiniMapProperty.etMiniMapSearch.getText().toString();
            String[] latLonArray = latLon.split(",");
            if (latLonArray.length == 2) {
                double longitude = Double.parseDouble(latLonArray[0]);
                double latitude = Double.parseDouble(latLonArray[1]);
                plotPoint(latitude, longitude);
            } else {
                showToast(R.string.err_lat_lon_search);
            }


        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setBaseMapAndDefaultCentre() {
        ArcGISRuntimeEnvironment.setApiKey(BuildConfig.ARC_GIS_API_KEY);
        ArcGISRuntimeEnvironment.setLicense(BuildConfig.ARC_GIS_LICENSE);
        getViewBinding().includeMiniMapProperty.miniMap.setAttributionTextVisible(false);
        map = new ArcGISMap(Basemap.createOpenStreetMap());
        if (map.getBasemap().getBaseLayers().size() > 0) {
            map.getBasemap().getBaseLayers().get(0).setId(AppConstants.BASE_MAP_NAME);
            map.getBasemap().getBaseLayers().get(0).setName(AppConstants.BASE_MAP_NAME);
        }
        if (AppCacheData.getOurInstance().getDashboardDetails() != null) {
            if (AppCacheData.getOurInstance().getDashboardDetails().getLocalBody() != null) {
                initialViewPoint = new Viewpoint(AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getDefaultLat(),
                        AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getDefaultLon(),
                        StaticData.getArcGisScale()[AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getDefaultZoom()]);
                getViewBinding().includeMiniMapProperty.miniMap.setOnTouchListener(new MapTouchListener(getBaseActivity(), getViewBinding().includeMiniMapProperty.miniMap));
                map.setInitialViewpoint(initialViewPoint);
                map.setMinScale(StaticData.getArcGisScale()[4]);
                map.setMaxScale(StaticData.getArcGisScale()[23]);
                getViewBinding().includeMiniMapProperty.miniMap.setMap(map);

                ArrayList<DashboardResponse.BaseLayers> baseLayerData = AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getBaseLayers();
                for (DashboardResponse.BaseLayers layer : baseLayerData) {
                    DashboardResponse.LayerCategoryChild childLayers = new DashboardResponse.LayerCategoryChild();
                    String[] buildingName = layer.getLayerName().split(":");
                    String layerName = buildingName.length > 0 ? buildingName[buildingName.length - 1] : "";
                    childLayers.setLabel(layer.getLabel());
                    childLayers.setLayerType(layer.getLayerType());
                    childLayers.setLayerName(layerName);
                    childLayers.setUrl(layer.getUrl());
                    childLayers.setValue(layer.getLabel());
                    layers.add(childLayers);
                }
                layers.add(AppCacheData.getOurInstance().getDashboardDetails().getWardBoundary());
                if (AppCacheData.getOurInstance().getUtilitySpinnerData() != null &&
                        AppCacheData.getOurInstance().getUtilitySpinnerData().getLayerDetails() != null) {
                    layers.add(AppCacheData.getOurInstance().getUtilitySpinnerData().getLayerDetails().getMobileTower());
                }
            }
        }

    }


    class MapTouchListener extends DefaultMapViewOnTouchListener {

        public MapTouchListener(Context context, MapView mapView) {
            super(context, mapView);
        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    getViewBinding().nsvMobileTowerDetails.requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    getViewBinding().nsvMobileTowerDetails.requestDisallowInterceptTouchEvent(false);
                    break;
            }
            return super.onTouch(view, event);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            android.graphics.Point screenPoint = new android.graphics.Point(Math.round(e.getX()), Math.round(e.getY()));
            Point mapPoint = getViewBinding().includeMiniMapProperty.miniMap.screenToLocation(screenPoint);
            if (mapPoint != null) {
                Point wgs84Point = (Point) GeometryEngine.project(mapPoint, SpatialReferences.getWgs84());
                latitude = wgs84Point.getY();
                longitude = wgs84Point.getX();
                plotPoint(latitude, longitude);
            }
            return true;
        }
    }

    public void plotPoint(double latitude, double longitude) {
        try {
            this.latitude = latitude;
            this.longitude = longitude;
            if (pointOverlay == null) {
                pointOverlay = new GraphicsOverlay();
                getViewBinding().includeMiniMapProperty.miniMap.getGraphicsOverlays().add(pointOverlay);
            }
            pointOverlay.getGraphics().clear();
            Point point = new Point(longitude, latitude, SpatialReferences.getWgs84());
            BitmapDrawable pinStarBlueDrawable = (BitmapDrawable) ContextCompat.getDrawable(getBaseActivity(), R.mipmap.marker_bridge);
            PictureMarkerSymbol pinSymbol = PictureMarkerSymbol.createAsync(Objects.requireNonNull(pinStarBlueDrawable)).get();
            pinSymbol.setHeight(getResources().getDimensionPixelOffset(R.dimen.standard_icon_map_plot));
            pinSymbol.setWidth(getResources().getDimensionPixelOffset(R.dimen.standard_icon_map_plot));
            pinSymbol.loadAsync();
            Graphic graphic = new Graphic(point, pinSymbol);
            pointOverlay.getGraphics().add(graphic);
            getViewBinding().includeMiniMapProperty.miniMap.setViewpoint(new Viewpoint(latitude, longitude, getViewBinding().includeMiniMapProperty.miniMap.getMapScale()));
            getViewBinding().includeMiniMapProperty.etMiniMapSearch.setText(String.format(Locale.ENGLISH, "%.4f,%.4f", longitude, latitude));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }


    void submitOnClick() {
        String buildingName = Objects.requireNonNull(getViewBinding().etBuildingName.getText()).toString().trim();
        String place = Objects.requireNonNull(getViewBinding().etMobileTowerPlace.getText()).toString().trim();
        String ownerAddress = Objects.requireNonNull(getViewBinding().etMobileTowerOwnerAddress.getText()).toString().trim();
        String ownerName = Objects.requireNonNull(getViewBinding().etMobileTowerOwnerName.getText()).toString().trim();
        String ownerMobile = Objects.requireNonNull(getViewBinding().etMobileTowerOwnerMobile.getText()).toString().trim();
        String buildingStatus = (String) getViewBinding().srBuildingStatus.getTag();
        String buildingUsage = (String) getViewBinding().srBuildingUsage.getTag();
        String newPropertyId = Objects.requireNonNull(getViewBinding().etNewPropertyId.getText()).toString().trim();
        String oldPropertyId = Objects.requireNonNull(getViewBinding().etOldPropertyId.getText()).toString().trim();
        String uniqueId = Objects.requireNonNull(getViewBinding().etMobileTowerUniqueId.getText()).toString().trim();
        String consumerNo = Objects.requireNonNull(getViewBinding().etConsumerNumber.getText()).toString().trim();
        String yearOfCon = Objects.requireNonNull(getViewBinding().etYearOfConstruction.getText()).toString().trim();
        String serviceProvider = Objects.requireNonNull(getViewBinding().etServiceProvider.getText()).toString().trim();
        String roadWidth = Objects.requireNonNull(getViewBinding().etRoadWidth.getText()).toString().trim();
        boolean isElecCon = getViewBinding().cbElectricConnectivity.isChecked();
        String roadType = Objects.requireNonNull(getViewBinding().etRoadType.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewBinding().etRemarks.getText()).toString().trim();
        String wardNo = (String) getViewBinding().srWardNo.getTag();
        presenter.validateData(buildingName, place, ownerAddress, ownerName, ownerMobile, buildingStatus,
                buildingUsage, newPropertyId, oldPropertyId, yearOfCon, isElecCon, uniqueId, serviceProvider,
                consumerNo, roadWidth, roadType, wardNo, remarks, photo, photo2, latitude, longitude);
    }

    @Override
    public void showErrors(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_BUILDING_NAME:
                getViewBinding().layoutMobileTowerBuildingName.setError(error);
                getViewBinding().layoutMobileTowerBuildingName.requestFocus();
                break;
            case ERROR_TYPE_PLACE:
                getViewBinding().layoutMobileTowerPlace.setError(error);
                getViewBinding().layoutMobileTowerPlace.requestFocus();
                break;
            case ERROR_TYPE_OWNER_NAME:
                getViewBinding().layoutMobileTowerOwnerName.setError(error);
                getViewBinding().layoutMobileTowerOwnerName.requestFocus();
                break;
            case ERROR_TYPE_OWNER_ADDRESS:
                getViewBinding().layoutMobileTowerOwnerAddress.setError(error);
                getViewBinding().layoutMobileTowerOwnerAddress.requestFocus();
                break;
            case ERROR_TYPE_OWNER_MOBILE:
                getViewBinding().layoutMobileTowerOwnerMobile.setError(error);
                getViewBinding().layoutMobileTowerOwnerMobile.requestFocus();
                break;
            case ERROR_TYPE_BUILDING_STATUS:
                getViewBinding().layoutMobileTowerBuildingStatus.setError(error);
                getViewBinding().layoutMobileTowerBuildingStatus.requestFocus();
                break;
            case ERROR_TYPE_BUILDING_USAGE:
                getViewBinding().layoutMobileTowerBuildingUsage.setError(error);
                getViewBinding().layoutMobileTowerBuildingUsage.requestFocus();
                break;
            case ERROR_TYPE_NEW_PROPERTY_ID:
                getViewBinding().layoutMobileTowerNewPropertyId.setError(error);
                getViewBinding().layoutMobileTowerNewPropertyId.requestFocus();
                break;
            case ERROR_TYPE_OLD_PROPERTY_ID:
                getViewBinding().layoutMobileTowerOldPropertyId.setError(error);
                getViewBinding().layoutMobileTowerOldPropertyId.requestFocus();
                break;
            case ERROR_TYPE_YEAR_OF_CONSTRUCTION:
                getViewBinding().layoutMobileTowerYearOfConstruction.setError(error);
                getViewBinding().layoutMobileTowerYearOfConstruction.requestFocus();
                break;
            case ERROR_TYPE_ELECTRIC_CONNECTIVITY:
                getViewBinding().cbElectricConnectivity.setError(error);
                getViewBinding().cbElectricConnectivity.requestFocus();
                break;
            case ERROR_TYPE_UNIQUE_ID:
                getViewBinding().layoutMobileTowerUniqueId.setError(error);
                getViewBinding().layoutMobileTowerUniqueId.requestFocus();
                break;
            case ERROR_TYPE_SERVICE_PROVIDER:
                getViewBinding().layoutMobileTowerServiceProvider.setError(error);
                getViewBinding().layoutMobileTowerServiceProvider.requestFocus();
                break;
            case ERROR_TYPE_CONSUMER_NO:
                getViewBinding().layoutMobileTowerConsumerNo.setError(error);
                getViewBinding().layoutMobileTowerConsumerNo.requestFocus();
                break;
            case ERROR_TYPE_ROAD_WIDTH:
                getViewBinding().layoutMobileTowerRoadWidth.setError(error);
                getViewBinding().layoutMobileTowerRoadWidth.requestFocus();
                break;
            case ERROR_TYPE_ROAD_TYPE:
                getViewBinding().layoutMobileTowerRoadType.setError(error);
                getViewBinding().layoutMobileTowerRoadType.requestFocus();
                break;
            case ERROR_TYPE_WARD_NO:
                getViewBinding().layoutWardNo.setError(error);
                getViewBinding().layoutWardNo.requestFocus();
                break;
            case ERROR_TYPE_REMARKS:
                getViewBinding().layoutRemarks.setError(error);
                getViewBinding().layoutRemarks.requestFocus();
                break;
            case ERROR_TYPE_PHOTO_1:
            case ERROR_TYPE_PHOTO_2:
            case ERROR_TYPE_MOBILE_TOWER_LOCATION:
                showToast(error);
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutMobileTowerBuildingName.setErrorEnabled(false);
        getViewBinding().layoutMobileTowerPlace.setErrorEnabled(false);
        getViewBinding().layoutMobileTowerOwnerAddress.setErrorEnabled(false);
        getViewBinding().layoutMobileTowerOwnerName.setErrorEnabled(false);
        getViewBinding().layoutMobileTowerOwnerMobile.setErrorEnabled(false);
        getViewBinding().layoutMobileTowerBuildingStatus.setErrorEnabled(false);
        getViewBinding().layoutMobileTowerBuildingUsage.setErrorEnabled(false);
        getViewBinding().layoutMobileTowerNewPropertyId.setErrorEnabled(false);
        getViewBinding().layoutMobileTowerOldPropertyId.setErrorEnabled(false);
        getViewBinding().layoutMobileTowerYearOfConstruction.setErrorEnabled(false);
        getViewBinding().layoutMobileTowerServiceProvider.setErrorEnabled(false);
        getViewBinding().layoutMobileTowerConsumerNo.setErrorEnabled(false);
        getViewBinding().layoutMobileTowerRoadType.setErrorEnabled(false);
        getViewBinding().layoutMobileTowerRoadWidth.setErrorEnabled(false);
        getViewBinding().layoutMobileTowerUniqueId.setErrorEnabled(false);
        getViewBinding().layoutWardNo.setErrorEnabled(false);
        getViewBinding().layoutRemarks.setErrorEnabled(false);
    }

    public void setEditData() {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
            UtilityAssets.MobileTower mobileTowerDetails = AppCacheData.getOurInstance().getBuildingAssetData().getMobileTowerDetails();
            if (mobileTowerDetails != null) {
                getViewBinding().etBuildingName.setText(mobileTowerDetails.getBuildingName());
                getViewBinding().etMobileTowerPlace.setText(mobileTowerDetails.getPlace());
                getViewBinding().etMobileTowerOwnerName.setText(String.valueOf(mobileTowerDetails.getOwnerName()));
                getViewBinding().etMobileTowerOwnerAddress.setText(mobileTowerDetails.getOwnerAddress());
                getViewBinding().etMobileTowerOwnerMobile.setText(String.valueOf(mobileTowerDetails.getOwnerMobile()));
                getViewBinding().srBuildingStatus.setText(String.valueOf(mobileTowerDetails.getBuildingStatus()));
                getViewBinding().srBuildingUsage.setText(String.valueOf(mobileTowerDetails.getBuildingUsage()));
                getViewBinding().etNewPropertyId.setText(mobileTowerDetails.getNewPropertyId());
                getViewBinding().etOldPropertyId.setText(mobileTowerDetails.getOldPropertyId());
                getViewBinding().etServiceProvider.setText(mobileTowerDetails.getServiceProvider());
                getViewBinding().etConsumerNumber.setText(mobileTowerDetails.getConsumerNo());
                getViewBinding().etYearOfConstruction.setText(String.valueOf(mobileTowerDetails.getYearOfConstruction()));
                getViewBinding().etMobileTowerUniqueId.setText(mobileTowerDetails.getUniqueId());
                getViewBinding().etRoadType.setText(mobileTowerDetails.getRoadType());
                getViewBinding().etRoadWidth.setText(String.valueOf(mobileTowerDetails.getRoadWidth()));
                getViewBinding().etRemarks.setText(mobileTowerDetails.getRemarks());
                getViewBinding().cbElectricConnectivity.setChecked(mobileTowerDetails.isElectricConnectivity());
                wardAdapter.setContent(String.valueOf(mobileTowerDetails.getWard()));
                GeomPoint geom = mobileTowerDetails.getGeom();
                if (geom != null && geom.getCoordinates() != null && geom.getCoordinates().size() == 2) {
                    double longitude = geom.getCoordinates().get(0);
                    double latitude = geom.getCoordinates().get(1);
                    plotPoint(latitude, longitude);
                }
                Log.v(TAG, "PHOTO1 :: " + mobileTowerDetails.getPhoto1());
                Log.v(TAG, "PHOTO2 :: " + mobileTowerDetails.getPhoto2());
                onImageUploadSuccess(AppCacheData.getOurInstance().getImageBaseURL() + "" + mobileTowerDetails.getPhoto1(), AppConstants.IMAGE_PATH_PHOTO1, mobileTowerDetails.getPhoto1());
                onImageUploadSuccess(AppCacheData.getOurInstance().getImageBaseURL() + "" + mobileTowerDetails.getPhoto2(), AppConstants.IMAGE_PATH_PHOTO2, mobileTowerDetails.getPhoto2());
            }
        }
    }


    public void captureImage(int reqTypeCode) {
        if (getBaseActivity().hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) && getBaseActivity().hasPermission(android.Manifest.permission.CAMERA)) {
            getBaseActivity().openImagePicker(this, reqTypeCode);
        } else {
            getBaseActivity().requestPermissionsSafely(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA}, AppConstants.REQUEST_CODE_STORAGE_CAMERA_PERMISSION);
        }
    }


    @Override
    public void onImageCaptured(String path, int reqTypeCode) {
        if (AppCacheData.getOurInstance().getDashboardDetails() != null &&
                AppCacheData.getOurInstance().getDashboardDetails().getLocalBody() != null) {
           /* presenter.uploadImage(path, AppConstants.IMAGE_PATH_MOBILE_TOWER, AppConstants.IMAGE_PATH_PHOTO1,
                    String.valueOf(AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getLocalBodyId()));*/
            presenter.uploadImage(path, AppConstants.IMAGE_PATH_BUILDING_ASSET, reqTypeCode == REQUEST_CODE_IMAGE ?
                            AppConstants.IMAGE_PATH_PHOTO1 : reqTypeCode == REQUEST_CODE_IMAGE_2 ? AppConstants.IMAGE_PATH_PHOTO2 : AppConstants.IMAGE_PATH_MOBILE_TOWER,
                    String.valueOf(AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getLocalBodyId()));
        }
    }

    @Override
    public void onImageUploadSuccess(String imagePath, String imageType, String response) {
        switch (imageType) {
            case AppConstants.IMAGE_PATH_PHOTO1:
                photo = response;
                break;
            case AppConstants.IMAGE_PATH_PHOTO2:
                photo2 = response;
                break;
        }
        Glide.with(this)
                .load(imagePath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_property_image_place_holder)
                .error(R.mipmap.ic_property_image_place_holder)
                .skipMemoryCache(false)
                .into(imageType.equals(AppConstants.IMAGE_PATH_PHOTO1) ? getViewBinding().imgMobileTower1 : getViewBinding().imgMobileTower2);

    }


    @Override
    public void onImageRemoved(int reqTypeCode) {

        getViewBinding().imgMobileTower1.setImageResource(R.mipmap.ic_property_image_place_holder);
        switch (reqTypeCode) {
            case REQUEST_CODE_IMAGE:
                photo = null;
                getViewBinding().imgMobileTower1.setImageResource(R.mipmap.ic_property_image_place_holder);
                break;
            case REQUEST_CODE_IMAGE_2:
                photo2 = null;
                getViewBinding().imgMobileTower2.setImageResource(R.mipmap.ic_property_image_place_holder);
                break;
        }

    }


    @Override
    public void onPause() {
        getViewBinding().includeMiniMapProperty.miniMap.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        getViewBinding().includeMiniMapProperty.miniMap.resume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        getViewBinding().includeMiniMapProperty.miniMap.dispose();
        super.onDestroy();
    }

    @Override
    public void onAddOrUpdateSuccess(boolean isUpdate) {
        if (isUpdate) {
            getBaseActivity().finish();
        } else {
            getBaseActivity().launchUtilityHome(false, false);
        }
    }
}
