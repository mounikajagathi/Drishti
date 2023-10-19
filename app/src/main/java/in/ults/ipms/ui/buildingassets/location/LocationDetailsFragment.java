package in.ults.ipms.ui.buildingassets.location;

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

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.google.gson.Gson;

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
import in.ults.ipms.databinding.FragmentLocationDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseFragment;
import in.ults.ipms.ui.mapoverlays.MapOverlayBottomSheet;
import in.ults.ipms.utils.AppConstants;

public class LocationDetailsFragment extends BaseFragment<FragmentLocationDetailsBinding> implements ILocationDetailsView {

    public static final String TAG = LocationDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_PLACE_NAME = 1;
    public static final int ERROR_TYPE_ZONE = 2;
    public static final int ERROR_TYPE_WARD_NUMBER = 3;
    public static final int ERROR_TYPE_POSTOFFICE = 4;
    public static final int ERROR_TYPE_PROPERTY_LOCATION = 5;

    public static LocationDetailsFragment newInstance() {
        return new LocationDetailsFragment();
    }

    @Inject
    ILocationDetailsPresenter<ILocationDetailsView, ILocationDetailsInteractor> presenter;


    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.BuildingZones> buildingZonesAdapter;

    private ArcGISMap map;
    private Viewpoint initialViewPoint;
    private GraphicsOverlay pointOverlay;
    private double latitude = 0.0;
    private double longitude = 0.0;

    Set<String> selectedMapLayers;
    ArrayList<DashboardResponse.LayerCategoryChild> layers;

    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.WardNos> wardNosAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Postoffices> postofficesAdapter;

    @Override
    protected FragmentLocationDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentLocationDetailsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.location_details_title);
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
        getViewBinding().btnLocationDetails.setOnClickListener(v -> submitOnClick());
        getViewBinding().includeMiniMapProperty.fabLayers.setOnClickListener(view1 -> {
            MapOverlayBottomSheet layersBottomSheet = MapOverlayBottomSheet.newInstance();
            layersBottomSheet.setArcGISMap(map);
            layersBottomSheet.setLayerList(layers);
            layersBottomSheet.setSelectedLayersIDs(selectedMapLayers);
            layersBottomSheet.show(getParentFragmentManager(), MapOverlayBottomSheet.TAG);
        });
        buildingZonesAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srLocationZone, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getBuildingZones());
        wardNosAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srLocationWardNumber, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getWardNos());
        postofficesAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srLocationPostOffice, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getPostoffices());

//        if(AppCacheData.getOurInstance().isAssetUpdate()){
        setEditData();
//        }

    }

    @Override
    protected void setUp(View view) {
        super.setUp(view);
        getViewBinding().includeMiniMapProperty.miniMapDelete.setOnClickListener(v -> {
            getViewBinding().includeMiniMapProperty.etMiniMapSearch.setText("");
            latitude = 0.0;
            longitude = 0.0;
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
        map = new ArcGISMap(BasemapStyle.ARCGIS_IMAGERY);
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
                    String[] name = layer.getLayerName().split(":");
                    String layerName = name.length > 0 ? name[name.length - 1] : "";
                    childLayers.setLabel(layer.getLabel());
                    childLayers.setLayerType(layer.getLayerType());
                    childLayers.setLayerName(layerName);
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
                    getViewBinding().nsvLocationDetails.requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    getViewBinding().nsvLocationDetails.requestDisallowInterceptTouchEvent(false);
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
            BitmapDrawable pinStarBlueDrawable = (BitmapDrawable) ContextCompat.getDrawable(getBaseActivity(), R.drawable.ic_marker_goto_xy);
            PictureMarkerSymbol pinSymbol = PictureMarkerSymbol.createAsync(Objects.requireNonNull(pinStarBlueDrawable)).get();
            pinSymbol.setHeight(getResources().getDimensionPixelOffset(R.dimen.standard_icon_size_medium));
            pinSymbol.setWidth(getResources().getDimensionPixelOffset(R.dimen.standard_icon_size_medium));
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
        String placeName = (String) Objects.requireNonNull(getViewBinding().etLocationPlaceName.getText()).toString().trim();
        String zone = (String) getViewBinding().srLocationZone.getTag();
        String wardNumber = (String) getViewBinding().srLocationWardNumber.getTag();
        String postOffice = (String) getViewBinding().srLocationPostOffice.getTag();
        Log.d("zone submit", ""+zone);

        presenter.validateData(placeName, zone, wardNumber, postOffice, latitude, longitude);
    }

    @Override
    public void showLocationDetailsFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_PLACE_NAME:
                getViewBinding().layoutLocationPlaceName.setError(error);
                getViewBinding().layoutLocationPlaceName.requestFocus();
                break;
            case ERROR_TYPE_ZONE:
                getViewBinding().layoutLocationZone.setError(error);
                getViewBinding().layoutLocationZone.requestFocus();
                break;
            case ERROR_TYPE_WARD_NUMBER:
                getViewBinding().layoutLocationWardNumber.setError(error);
                getViewBinding().layoutLocationWardNumber.requestFocus();
                break;
            case ERROR_TYPE_POSTOFFICE:
                getViewBinding().layoutLocationPostOffice.setError(error);
                getViewBinding().layoutLocationPostOffice.requestFocus();
                break;
            case ERROR_TYPE_PROPERTY_LOCATION:
                showToast(error);
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutLocationPlaceName.setErrorEnabled(false);
        getViewBinding().layoutLocationZone.setErrorEnabled(false);
        getViewBinding().layoutLocationWardNumber.setErrorEnabled(false);
        getViewBinding().layoutLocationPostOffice.setErrorEnabled(false);
    }

    public void setEditData() {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails() != null) {
            BuildingAssets.PropertyDetails propertyDetails = AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails();
            getViewBinding().etLocationPlaceName.setText(propertyDetails.getPlace_name());
            buildingZonesAdapter.setContent(String.valueOf(propertyDetails.getBldgZone()));
            Log.d("zone....", new Gson().toJson(propertyDetails.getBldgZone()));
            wardNosAdapter.setContent(String.valueOf(propertyDetails.getWard()));
            postofficesAdapter.setContent(String.valueOf(propertyDetails.getPostOffice()));
            GeomPoint geom = propertyDetails.getGeom();
            if (geom != null && geom.getCoordinates() != null && geom.getCoordinates().size() == 2) {
                double longitude = geom.getCoordinates().get(0);
                double latitude = geom.getCoordinates().get(1);
                plotPoint(latitude, longitude);
            }
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
    public void goToNext() {
        getBaseActivity().launchRoadDetails(true, false);
    }

    @Override
    public void onAddOrUpdateSuccess() {
        getBaseActivity().launchBuildAssetHome(false, false);
    }
}
