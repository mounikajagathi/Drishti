package in.ults.ipms.ui.buildingassets.building;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

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
import in.ults.ipms.data.network.model.response.BuildingAssetSpinnerResponse;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.data.network.model.response.GeomPoint;
import in.ults.ipms.databinding.FragmentBuildingDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseFragment;
import in.ults.ipms.ui.mapoverlays.MapOverlayBottomSheet;
import in.ults.ipms.utils.AppConstants;

/**
 * Created by AmalB on 6/22/2021.
 */

public class BuildingDetailsFragment extends BaseFragment<FragmentBuildingDetailsBinding> implements IBuildingDetailsView {

    public static final String TAG = BuildingDetailsFragment.class.getSimpleName();


    public static final int ERROR_TYPE_BUILDING_NAME = 1;
    public static final int ERROR_TYPE_PERMIT_NUMBER = 2;
    public static final int ERROR_TYPE_BUILDING_UNDER = 3;
    public static final int ERROR_TYPE_BUILDING_REFID = 4;
    public static final int ERROR_TYPE_WARD_NUMBER = 5;
    public static final int ERROR_TYPE_STRUCTURE_TYPE = 6;
    public static final int ERROR_TYPE_BUILDING_LOCATION = 7;

    Set<String> selectedMapLayers;
    ArrayList<DashboardResponse.LayerCategoryChild> layers;


    @Inject
    IBuildingDetailsPresenter<IBuildingDetailsView, IBuildingDetailsInteractor> presenter;

    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.BuildingUnder> buildingUnderAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.WardNos> wardNosAdapter;

    private ArcGISMap map;
    private Viewpoint initialViewPoint;
    private GraphicsOverlay pointOverlay;
    private double latitude = 0.0;
    private double longitude = 0.0;

    public static BuildingDetailsFragment newInstance() {
        return new BuildingDetailsFragment();
    }

    @Override
    protected FragmentBuildingDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentBuildingDetailsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.building_details_title);
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
        getViewBinding().includeMiniMapBuilding.fabLayers.setOnClickListener(view1 -> {
            MapOverlayBottomSheet layersBottomSheet = MapOverlayBottomSheet.newInstance();
            layersBottomSheet.setArcGISMap(map);
            layersBottomSheet.setLayerList(layers);
            layersBottomSheet.setSelectedLayersIDs(selectedMapLayers);
            layersBottomSheet.show(getParentFragmentManager(), MapOverlayBottomSheet.TAG);
        });
        buildingUnderAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srBuildingUnder, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getBuildingUnder());
        wardNosAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srBuildingWardNumber, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getWardNos());
    }

    @Override
    protected void setUp(View view) {
        super.setUp(view);
        getViewBinding().btnBuildingDetails.setOnClickListener(v -> submitOnClick());
        getViewBinding().includeMiniMapBuilding.miniMapDelete.setOnClickListener(v -> {
            getViewBinding().includeMiniMapBuilding.etMiniMapSearch.setText("");
            latitude = 0.0;
            longitude = 0.0;
            if (pointOverlay != null) {
                pointOverlay.getGraphics().clear();
            }
        });
        getViewBinding().includeMiniMapBuilding.miniMapExtend.setOnClickListener(v -> {
            if (initialViewPoint != null) {
                getViewBinding().includeMiniMapBuilding.miniMap.setViewpoint(initialViewPoint);
            }
        });
        getViewBinding().includeMiniMapBuilding.miniMapSearch.setOnClickListener(v -> {
            String latLon = getViewBinding().includeMiniMapBuilding.etMiniMapSearch.getText().toString();
            String[] latLonArray = latLon.split(",");
            if (latLonArray.length == 2) {
                double longitude = Double.parseDouble(latLonArray[0]);
                double latitude = Double.parseDouble(latLonArray[1]);
                plotPoint(latitude, longitude);
            } else {
                showToast(R.string.err_lat_lon_search);
            }
        });

        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                    AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails() != null) {
                long buildingID = AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails().getBuilding();
                presenter.fetchBuildAssetData(String.valueOf(buildingID), AppConstants.APP_TYPE_BUILDING_ASSET, AppConstants.LAYER_TYPE_BUILDING);
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setBaseMapAndDefaultCentre() {
        ArcGISRuntimeEnvironment.setApiKey(BuildConfig.ARC_GIS_API_KEY);
        ArcGISRuntimeEnvironment.setLicense(BuildConfig.ARC_GIS_LICENSE);
        getViewBinding().includeMiniMapBuilding.miniMap.setAttributionTextVisible(false);
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
                getViewBinding().includeMiniMapBuilding.miniMap.setOnTouchListener(new MapTouchListener(getBaseActivity(), getViewBinding().includeMiniMapBuilding.miniMap));
                map.setInitialViewpoint(initialViewPoint);
                map.setMinScale(StaticData.getArcGisScale()[4]);
                map.setMaxScale(StaticData.getArcGisScale()[23]);
                getViewBinding().includeMiniMapBuilding.miniMap.setMap(map);

                ArrayList<DashboardResponse.BaseLayers> baseLayerData = AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getBaseLayers();
                for (DashboardResponse.BaseLayers layer : baseLayerData) {
                    DashboardResponse.LayerCategoryChild childLayers = new DashboardResponse.LayerCategoryChild();
//                    String[] name = layer.getLayerName().split(":");
//                    String layerName = name.length > 0 ? name[name.length - 1] : "";
                    childLayers.setLayerType(layer.getLayerType());
                    childLayers.setLabel(layer.getLabel());
                    childLayers.setLayerName(layer.getLayerName());
                    childLayers.setUrl(layer.getUrl());
                    childLayers.setValue(layer.getLabel());
                    layers.add(childLayers);
                }
                layers.add(AppCacheData.getOurInstance().getDashboardDetails().getWardBoundary());

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
                    getViewBinding().nsvBuildingDetails.requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    getViewBinding().nsvBuildingDetails.requestDisallowInterceptTouchEvent(false);
                    break;
            }
            return super.onTouch(view, event);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            android.graphics.Point screenPoint = new android.graphics.Point(Math.round(e.getX()), Math.round(e.getY()));
            Point mapPoint = getViewBinding().includeMiniMapBuilding.miniMap.screenToLocation(screenPoint);
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
                getViewBinding().includeMiniMapBuilding.miniMap.getGraphicsOverlays().add(pointOverlay);
            }
            pointOverlay.getGraphics().clear();
            Point point = new Point(longitude, latitude, SpatialReferences.getWgs84());
            BitmapDrawable pinStarBlueDrawable = (BitmapDrawable) ContextCompat.getDrawable(getBaseActivity(), R.mipmap.marker_other_buildings);
            PictureMarkerSymbol pinSymbol = PictureMarkerSymbol.createAsync(Objects.requireNonNull(pinStarBlueDrawable)).get();
            pinSymbol.setHeight(getResources().getDimensionPixelOffset(R.dimen.standard_icon_map_plot));
            pinSymbol.setWidth(getResources().getDimensionPixelOffset(R.dimen.standard_icon_map_plot));
            pinSymbol.loadAsync();
            Graphic graphic = new Graphic(point, pinSymbol);
            pointOverlay.getGraphics().add(graphic);
            getViewBinding().includeMiniMapBuilding.miniMap.setViewpoint(new Viewpoint(latitude, longitude, getViewBinding().includeMiniMapBuilding.miniMap.getMapScale()));
            getViewBinding().includeMiniMapBuilding.etMiniMapSearch.setText(String.format(Locale.ENGLISH, "%.4f,%.4f", longitude, latitude));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    void submitOnClick() {
        String buildingName = Objects.requireNonNull(getViewBinding().etBuildingName.getText()).toString().trim();
        String permitNumber = Objects.requireNonNull(getViewBinding().etBuildingPermitNumber.getText()).toString().trim();
        String buildingRefId = Objects.requireNonNull(getViewBinding().etBuildingRefId.getText()).toString().trim();
        String structureType = Objects.requireNonNull(getViewBinding().etBuildingStructureType.getText()).toString().trim();
        String wardNumber = (String) getViewBinding().srBuildingWardNumber.getTag();
        String buildingUnder = (String) getViewBinding().srBuildingUnder.getTag();
        boolean isLandmark = getViewBinding().cbBuildingIsLandmark.isChecked();
        presenter.validateData(buildingName, permitNumber, buildingUnder, buildingRefId, wardNumber,structureType, isLandmark, latitude, longitude);
    }

    public void showBuildingDetailsFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_BUILDING_NAME:
                getViewBinding().layoutBuildingName.setError(error);
                getViewBinding().layoutBuildingName.requestFocus();
                break;
            case ERROR_TYPE_PERMIT_NUMBER:
                getViewBinding().layoutBuildingPermitNumber.setError(error);
                getViewBinding().layoutBuildingPermitNumber.requestFocus();
                break;
            case ERROR_TYPE_BUILDING_UNDER:
                getViewBinding().layoutBuildingUnder.setError(error);
                getViewBinding().layoutBuildingUnder.requestFocus();
                break;
            case ERROR_TYPE_BUILDING_REFID:
                getViewBinding().layoutBuildingRefId.setError(error);
                getViewBinding().layoutBuildingRefId.requestFocus();
                break;
            case ERROR_TYPE_WARD_NUMBER:
                getViewBinding().layoutBuildingWardNumber.setError(error);
                getViewBinding().layoutBuildingWardNumber.requestFocus();
                break;
            case ERROR_TYPE_STRUCTURE_TYPE:
                getViewBinding().layoutBuildingStructureType.setError(error);
                getViewBinding().layoutBuildingStructureType.requestFocus();
                break;
            case ERROR_TYPE_BUILDING_LOCATION:
                showToast(error);
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutBuildingName.setErrorEnabled(false);
        getViewBinding().layoutBuildingPermitNumber.setErrorEnabled(false);
        getViewBinding().layoutBuildingUnder.setErrorEnabled(false);
        getViewBinding().layoutBuildingRefId.setErrorEnabled(false);
        getViewBinding().layoutBuildingWardNumber.setErrorEnabled(false);
        getViewBinding().layoutBuildingStructureType.setErrorEnabled(false);
    }

    @Override
    public void onFetchAssetSuccess(String id, String app, String layer) {
        setEditData();
    }

    public void setEditData() {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                AppCacheData.getOurInstance().getBuildingDetailsData().getBuildingDetails() != null) {
            BuildingAssets.BuildingDetails buildingDetails = AppCacheData.getOurInstance().getBuildingDetailsData().getBuildingDetails();
            getViewBinding().etBuildingName.setText(buildingDetails.getBuildingName());
            getViewBinding().etBuildingPermitNumber.setText(buildingDetails.getPermitNo());
            getViewBinding().etBuildingRefId.setText(buildingDetails.getBldgRefId());
            getViewBinding().etBuildingStructureType.setText(buildingDetails.getStructureType());
            buildingUnderAdapter.setContent(String.valueOf(buildingDetails.getBldgUnder()));
            wardNosAdapter.setContent(buildingDetails.getWard());
            getViewBinding().cbBuildingIsLandmark.setChecked(buildingDetails.isLandmark());
            GeomPoint geom = buildingDetails.getGeom();
            if (geom != null && geom.getCoordinates() != null && geom.getCoordinates().size() == 2) {
                double longitude = geom.getCoordinates().get(0);
                double latitude = geom.getCoordinates().get(1);
                plotPoint(latitude, longitude);
            }
        }
    }

    @Override
    public void onPause() {
        getViewBinding().includeMiniMapBuilding.miniMap.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        getViewBinding().includeMiniMapBuilding.miniMap.resume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (getViewBinding().includeMiniMapBuilding.miniMap != null) {
            getViewBinding().includeMiniMapBuilding.miniMap.dispose();
        }
        super.onDestroy();
    }

    @Override
    public void onAddOrUpdateSuccess() {
        getBaseActivity().launchBuildAssetHome(false, false);
    }
}
