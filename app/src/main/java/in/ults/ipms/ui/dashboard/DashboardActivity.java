package in.ults.ipms.ui.dashboard;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.data.FeatureQueryResult;
import com.esri.arcgisruntime.geometry.AngularUnit;
import com.esri.arcgisruntime.geometry.AngularUnitId;
import com.esri.arcgisruntime.geometry.AreaUnit;
import com.esri.arcgisruntime.geometry.AreaUnitId;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.geometry.GeodeticCurveType;
import com.esri.arcgisruntime.geometry.Geometry;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.GeometryType;
import com.esri.arcgisruntime.geometry.LinearUnit;
import com.esri.arcgisruntime.geometry.LinearUnitId;
import com.esri.arcgisruntime.geometry.Multipoint;
import com.esri.arcgisruntime.geometry.Part;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.layers.WmsLayer;
import com.esri.arcgisruntime.layers.WmtsLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.IdentifyLayerResult;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.mapping.view.SketchCreationMode;
import com.esri.arcgisruntime.mapping.view.SketchEditor;
import com.esri.arcgisruntime.security.AuthenticationChallenge;
import com.esri.arcgisruntime.security.AuthenticationChallengeHandler;
import com.esri.arcgisruntime.security.AuthenticationChallengeResponse;
import com.esri.arcgisruntime.security.AuthenticationManager;
import com.esri.arcgisruntime.security.SelfSignedResponse;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.toolkit.scalebar.Scalebar;
import com.esri.arcgisruntime.toolkit.scalebar.style.Style;
import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import in.ults.ipms.BuildConfig;
import in.ults.ipms.R;
import in.ults.ipms.adapters.DashboardMenuFeatureAdapter;
import in.ults.ipms.adapters.MinimalInfoAdapter;
import in.ults.ipms.data.StaticData;
import in.ults.ipms.data.network.ApiInterface;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.data.network.model.response.FeatureDataResponse;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.data.network.model.response.GeomPolyLine;
import in.ults.ipms.data.network.model.response.GeomPolygon;
import in.ults.ipms.data.network.model.response.WFSModel;
import in.ults.ipms.databinding.ActivityDashboardBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.buildingassets.BuildingAssetsActivity;
import in.ults.ipms.ui.dashboard.basemap.BaseMapBottomSheet;
import in.ults.ipms.ui.dashboard.count.CountBottomSheet;
import in.ults.ipms.ui.dashboard.gotolocation.GoToLocationDialog;
import in.ults.ipms.ui.dashboard.layers.LayersBottomSheet;
import in.ults.ipms.ui.dashboard.legends.LegendsBottomSheet;
import in.ults.ipms.ui.dashboard.location.LocationDialog;
import in.ults.ipms.ui.dashboard.measure.MeasureDialog;
import in.ults.ipms.ui.dashboard.opacity.OpacityBottomSheet;
import in.ults.ipms.ui.dashboard.search.SearchActivity;
import in.ults.ipms.ui.dashboard.settings.SettingsBottomSheet;
import in.ults.ipms.ui.detailedinfo.DetailedInfoActivity;
import in.ults.ipms.ui.otherassets.OtherAssetsActivity;
import in.ults.ipms.ui.utility.UtilityActivity;
import in.ults.ipms.ui.waterbody.WaterBodyActivity;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.location.GpsUtils;
import in.ults.ipms.utils.network.UnsafeOkHttpClient;
import in.ults.ipms.views.ARCGISCircle;
import in.ults.ipms.views.adaptivestate.AdaptiveStateConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DashboardActivity extends BaseActivity<ActivityDashboardBinding> implements IDashboardView, GoToLocationDialog.OnLatLonListener, MeasureDialog.GeometryListener {
    public static final String TAG = DashboardActivity.class.getSimpleName();

    @Inject
    IDashboardPresenter<IDashboardView, IDashboardInteractor> presenter;

    @Inject
    LinearLayoutManager lmDashboardMenu;

    @Inject
    DashboardMenuFeatureAdapter dashboardMenuAdapter;

    private ArcGISMap map;

    private Viewpoint initialViewPoint;

    private GraphicsOverlay goToLocationOverlay;

    private GraphicsOverlay measurementOverlay;

    private SketchEditor mSketchEditor;

    private Dialog minimalInfoDialog;

    private SimpleMarkerSymbol mPointSymbol;

    private SimpleLineSymbol mLineSymbol;

    private SimpleLineSymbol outline;

    private GraphicsOverlay dataOverlay;

    private boolean featureInfoClicked = false;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int REQUEST_CODE_GPS = 11;

    @Override
    protected boolean setDarkStatusBarIcon() {
        return true;
    }

    @Override
    protected boolean hideNavigationIcons() {
        return true;
    }

    @Override
    protected boolean setTransparentStatusBar() {
        return true;
    }


    @Override
    protected ActivityDashboardBinding initViewBinding() {
        return ActivityDashboardBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initInjector() {
        getActivityComponent().inject(this);
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
    protected void init() {
        super.init();
        // license with a license key
        ArcGISRuntimeEnvironment.setApiKey(BuildConfig.ARC_GIS_API_KEY);
        ArcGISRuntimeEnvironment.setLicense(BuildConfig.ARC_GIS_LICENSE);
        AuthenticationChallengeHandler customChallengeHandler = authenticationChallenge -> {
            if (authenticationChallenge.getType() == AuthenticationChallenge.Type.SELF_SIGNED_CHALLENGE) {
                return new AuthenticationChallengeResponse(
                        AuthenticationChallengeResponse.Action.CONTINUE_WITH_SELF_SIGNED_RESPONSE,
                        new SelfSignedResponse(true, true));

            }
            return null;
        };
        AuthenticationManager.setAuthenticationChallengeHandler(customChallengeHandler);
        getViewBinding().mapView.setAttributionTextVisible(false);
        getViewBinding().mapInnerLayout.setPadding(0, CommonUtils.getStatusBarHeight(this), 0, 0);
        lmDashboardMenu.setOrientation(LinearLayoutManager.HORIZONTAL);
        getViewBinding().rvDashboardMenu.setLayoutManager(lmDashboardMenu);
        getViewBinding().rvDashboardMenu.setAdapter(dashboardMenuAdapter);
        dashboardMenuAdapter.setClickListener(this::onDashboardMenuItemClick);
        getViewBinding().fabHome.setOnClickListener(view -> homeButtonClick());
        getViewBinding().fabMeasure.setOnClickListener(view -> measurementButtonClick());
        getViewBinding().fabLocation.setOnClickListener(view -> locationButtonClick());
        getViewBinding().fabZoomOut.setOnClickListener(view -> zoomOutButtonClick());
        getViewBinding().fabZoomIn.setOnClickListener(view -> zoomInButtonClick());
        getViewBinding().fabAddProperty.setOnClickListener(view -> addProperty());
        getViewBinding().includeSketchBar.sketchOk.setOnClickListener(view -> sketchOkOnClick());
        getViewBinding().includeSketchBar.sketchCancel.setOnClickListener(view -> sketchCancelOnClick());
        getViewBinding().includeSketchBar.sketchRedo.setOnClickListener(view -> sketchRedoOnClick());
        getViewBinding().includeSketchBar.sketchUndo.setOnClickListener(view -> sketchUndoOnClick());
        getViewBinding().includeZoomToBar.txtShowInfo.setOnClickListener(view -> {
            if (minimalInfoDialog != null) {
                minimalInfoDialog.show();
            }
            setZoomInfoVisibility(true);
        });
        getViewBinding().includeZoomToBar.txtCloseInfo.setOnClickListener(view -> {
            if (dataOverlay != null) {
                dataOverlay.getGraphics().clear();
            }
            setZoomInfoVisibility(true);
        });

        getViewBinding().includeSearchView.searchView.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, SearchActivity.class);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(DashboardActivity.this, getViewBinding().includeSearchView.searchView, "search");
            ActivityCompat.startActivityForResult(this, intent, SearchActivity.SEARCH_ACTIVITY_FOR_RESULT, options.toBundle());
        });
    }

    public void setZoomInfoVisibility(boolean canShow) {
        getViewBinding().includeZoomToBar.zoomToBar.setVisibility(canShow ? View.GONE : View.VISIBLE);
        getViewBinding().includeSearchView.searchView.setVisibility(canShow ? View.VISIBLE : View.GONE);
        getViewBinding().rvDashboardMenu.setVisibility(canShow ? View.VISIBLE : View.GONE);
        getViewBinding().fabAddProperty.setVisibility(canShow ? View.VISIBLE : View.GONE);
        getViewBinding().fabMeasure.setVisibility(canShow ? View.VISIBLE : View.GONE);
        getViewBinding().fabLocation.setVisibility(canShow ? View.VISIBLE : View.GONE);
        getViewBinding().fabHome.setVisibility(canShow ? View.VISIBLE : View.GONE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SearchActivity.SEARCH_ACTIVITY_FOR_RESULT) {
            if (data != null) {
                String searchResult = data.getStringExtra("search_result_id");
                Set<String> dataSet = new HashSet<>();
                dataSet.add(searchResult);
                presenter.getFeatureInfoData(dataSet);
            }
        }
    }


    private void onDashboardMenuItemClick(int position, View v) {
        switch (dashboardMenuAdapter.getLocalDataSet().get(position).getMenuName()) {
            case AppConstants.MENU_TYPE_LAYERS:
                LayersBottomSheet layersBottomSheet = LayersBottomSheet.newInstance();
                layersBottomSheet.setArcGISMap(map);
                layersBottomSheet.show(getSupportFragmentManager(), LayersBottomSheet.TAG);
                break;
            case AppConstants.MENU_TYPE_COUNT:
                CountBottomSheet countBottomSheet = CountBottomSheet.newInstance();
                countBottomSheet.show(getSupportFragmentManager(), LayersBottomSheet.TAG);
                break;
            case AppConstants.MENU_TYPE_LEGENDS:
                LegendsBottomSheet lbSheet = LegendsBottomSheet.newInstance();
                lbSheet.setArcGISMap(map);
                lbSheet.show(getSupportFragmentManager(), LegendsBottomSheet.TAG);
                break;
            case AppConstants.MENU_TYPE_OPACITY:
                OpacityBottomSheet obSheet = OpacityBottomSheet.newInstance();
                obSheet.setArcGISMap(map);
                obSheet.show(getSupportFragmentManager(), OpacityBottomSheet.TAG);
                break;
            case AppConstants.MENU_TYPE_BASEMAP:
                BaseMapBottomSheet bSheet = BaseMapBottomSheet.newInstance();
                bSheet.setArcGISMap(map);
                bSheet.show(getSupportFragmentManager(), BaseMapBottomSheet.TAG);
                break;
            case AppConstants.MENU_TYPE_SETTINGS:
                SettingsBottomSheet sSheet = SettingsBottomSheet.newInstance();
                sSheet.show(getSupportFragmentManager(), SettingsBottomSheet.TAG);
                break;
            case AppConstants.MENU_TYPE_LOGOUT:
                presenter.logoutUser();
                break;
            default:
                break;
        }
    }

    @Override
    protected void setUp() {
        presenter.getDashboardDetails();
    }

    @Override
    public void setBaseMapAndDefaultCentre() {
        if (AppCacheData.getOurInstance().getDashboardDetails() != null) {
            if (AppCacheData.getOurInstance().getDashboardDetails().getLocalBody() != null) {
                setUpMap();
            }
            if (AppCacheData.getOurInstance().getDashboardDetails().getRole() != null) {
                ArrayList<DashboardResponse.Menu> menus = AppCacheData.getOurInstance().getDashboardDetails().getRole().getMenu();
                dashboardMenuAdapter.setLocalDataSet(menus);
                dashboardMenuAdapter.notifyDataSetChanged();
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setUpMap() {
        getViewBinding().includeSearchView.searchView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int radius = getViewBinding().includeSearchView.searchView.getHeight();
                getViewBinding().includeSearchView.searchView.setRadius((float) radius);
                getViewBinding().includeSearchView.searchView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        initialViewPoint = new Viewpoint(AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getDefaultLat(),
                AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getDefaultLon(),
                StaticData.getArcGisScale()[AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getDefaultZoom()]);
        getViewBinding().mapView.setOnTouchListener(new MapTouchListener(this, getViewBinding().mapView));
        map = new ArcGISMap(Basemap.createOpenStreetMap());
        if (map.getBasemap().getBaseLayers().size() > 0) {
            map.getBasemap().getBaseLayers().get(0).setId(AppConstants.BASE_MAP_NAME);
            map.getBasemap().getBaseLayers().get(0).setName(AppConstants.BASE_MAP_NAME);
        }
        map.setInitialViewpoint(initialViewPoint);
        map.setMinScale(StaticData.getArcGisScale()[4]);
        map.setMaxScale(StaticData.getArcGisScale()[23]);
        getViewBinding().mapView.setMap(map);
        map.addDoneLoadingListener(() -> {
            if (map.getLoadStatus() == LoadStatus.LOADED) {
                setBaseMap();
                setScaleBar();
                setSketchBar();
            } else if (map.getLoadStatus() == LoadStatus.FAILED_TO_LOAD) {
                System.out.println("@@@--->" + map.getLoadError().getMessage());
                System.out.println("@@@--->" + map.getLoadError().getCause());
            }
        });
    }

    private void setBaseMap() {
        ArrayList<DashboardResponse.BaseLayers> baseLayerData = AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getBaseLayers();
        for (DashboardResponse.BaseLayers layer : baseLayerData) {
            String baseLayerUrl = layer.getUrl()  + "/wms";
            List<String> baseLayerName = new ArrayList<>();
            String[] name = layer.getLayerName().split(":");
            if(name.length == 2){
                baseLayerName.add(name[1]);
                if(!layer.getUrl().contains(name[0])) {
                    baseLayerUrl = layer.getUrl() + name[0] + "/wms";
                }
            }
            WmsLayer baseLayer = new WmsLayer(baseLayerUrl, baseLayerName);
            baseLayer.setOpacity(layer.getOpacity());
            baseLayer.setName(layer.getLabel());
            map.getBasemap().getBaseLayers().add(baseLayer);
        }



        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sdt.ulgis.com/") // Replace with your base URL
                .client(UnsafeOkHttpClient.getUnsafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create a WFS service instance
        ApiInterface wfsService = retrofit.create(ApiInterface.class);

        // Make an API request to fetch WFS data
        Call<WFSModel> call = wfsService.getWFS();
        call.enqueue(new Callback<WFSModel>() {
            @Override
            public void onResponse(Call<WFSModel> call, Response<WFSModel> response) {
                if (response.isSuccessful()) {
                    WFSModel feature = response.body();
                    if (feature != null) {
                        // Access the feature data here
                        String propertyName = feature.getType();
                        // Handle the data as needed

// Extract the coordinates for each polygon within the MultiPolygon
                        ArrayList<ArrayList<ArrayList<ArrayList<Double>>>> polygons = feature.getFeatures().get(0).getGeometry().getCoordinates();

// Create a list to hold individual Polygon geometries
                        List<Polygon> polygonList = new ArrayList<>();
                        for (int k = 0; k < polygons.size(); k++) {
                            ArrayList<ArrayList<ArrayList<Double>>> poly = polygons.get(k);

// Iterate through each polygon in the MultiPolygon
                            for (int i = 0; i < poly.size(); i++) {
                                ArrayList<ArrayList<Double>> polygonCoordinates = poly.get(i);

                                // Create a list to hold rings for the current Polygon
                                PointCollection ringPoints = new PointCollection(SpatialReferences.getWgs84());

                                // Iterate through the coordinates of the current polygon
                                for (int j = 0; j < polygonCoordinates.size(); j++) {
                                    ArrayList<Double> ringCoordinates = polygonCoordinates.get(j);

                                    // Create points for the current ring
                                    double x = ringCoordinates.get(0);
                                    double y = ringCoordinates.get(1);
                                    Point point = new Point(x, y);
                                    ringPoints.add(point);

                                    // If it's the last point in the ring, close the ring
                                    if (j == polygonCoordinates.size() - 1) {
                                        ringPoints.add(ringPoints.get(0)); // Close the ring
                                    }
                                }

                                // Create a Polygon geometry for the current polygon
//                            Polygon polygon = new Polygon(ringPoints);

                                // Create a Part with the ring points
//                                Part part = new Part(ringPoints);

                                // Create a Multipart with the part
//                            Multipart multipart = new Multipart(part);

                                // Create a Polygon geometry for the current polygon
                                Polygon polygon = new Polygon(ringPoints);

                                // Add the completed polygon to the list
                                polygonList.add(polygon);
                            }
                        }

// Create a MultiPolygon geometry from the list of Polygon geometries
//                        GeometryEngine. multiPolygonGeometry = new GeometryCollection(polygonList);

                        Geometry resultGeometry = polygonList.get(0);

                        for (int i = 1; i < polygonList.size(); i++) {
                            Polygon nextPolygon = polygonList.get(i);

                            // Check if the geometries have the same type (e.g., both are polygons)
                            if (resultGeometry.getGeometryType() == GeometryType.POLYGON && nextPolygon.getGeometryType() == GeometryType.POLYGON) {
                                // Merge the current result and the next polygon
                                resultGeometry = GeometryEngine.union(resultGeometry, nextPolygon);
                            }
                        }



                        SimpleFillSymbol fillSymbol = new SimpleFillSymbol(
                                SimpleFillSymbol.Style.SOLID,
                                Color.argb(100, 0, 0, 255), // Blue fill color with transparency
                                null // No outline symbol
                        );
                        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
                        getViewBinding().mapView.getGraphicsOverlays().add(graphicsOverlay);
// Create a graphic with the extent polygon and fill symbol
                        Graphic extentGraphic = new Graphic(polygonList.get(0), fillSymbol);
                        graphicsOverlay.getGraphics().add(extentGraphic);

//                        WmsLayer wmsLayer = new WmsLayer("https://sdt.ulgis.com/geoserver/kollam_corporation/wms", Collections.singletonList("kollam"));
//                        WmtsLayer wmsLayer = new WmtsLayer("https://sdt.ulgis.com/geoserver/kollam_corporation/gwc/service/wmts", "kollam_corporation:kollam");
////                        WmsLayer wmsLayer = new WmsLayer("https://sdt.ulgis.com/geoserver/drishti/wms", Collections.singletonList("ipms_localbody"));
////                        wmsLayer.getCustomParameters().put("cql_filter","localbody_id=2");
//                        map.getOperationalLayers().add(wmsLayer);
//                        final Envelope ep = resultGeometry.getExtent();
//                        wmsLayer.addDoneLoadingListener(new Runnable() {
//                            @Override
//                            public void run() {
//                                if(wmsLayer.getLoadStatus() == LoadStatus.LOADED){
//
//
//
//                                    System.out.println("@@@--->"+wmsLayer.getFullExtent().toJson());
//                                    // Clip the WMS layer with the polygon
//                                    Geometry clippedGeometry = GeometryEngine.clip(wmsLayer.getFullExtent(), ep.getExtent());
//                                    WmsLayer clippedWmsLayer = new WmsLayer(modifyWmsLayerUrl("https://sdt.ulgis.com/geoserver/kollam_corporation/wms", clippedGeometry),Collections.singletonList("kollam"));
////
////// Add the clipped WMS layer to the map
////                                    map.getOperationalLayers().add(clippedWmsLayer);
////                                    System.out.println("@@@--->" + wmsLayer.getUri().toString());
//                                    Envelope boundingBox = new Envelope(75.332491082717098, 8.8193042072989396, 76.673812899745798, 12.0590424874439, SpatialReferences.getWgs84());
//
//                                    // Assuming you have a WMS layer named 'wmsLayer'
////                                    wmsLayer.setDefinitionExpression("1=1"); // Reset the expression to display all features
//// Apply the clipping by setting a filter based on your clipGeometry
////                                    wmsLayer.setDefinitionExpression("ST_INTERSECTS(SHAPE, " + clipGeometry + ")");
//
//                                }
//                            }
//                        });


                    }
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<WFSModel> call, Throwable t) {
                // Handle the network request failure

                System.out.println("@@@_-->" + t.getMessage());
            }
        });




//        // Load the WMS layer
//        WmsLayer wmsLayer = new WmsLayer("https://sdt.ulgis.com/geoserver/kollam_corporation/wms", Collections.singletonList("kollam"));
//        System.out.println("@@@--->"+wmsLayer.getUri().toString());
//        map.getOperationalLayers().add(wmsLayer);
//
//         Define the URL of the WFS service
//        String wfsUrl = "https://sdt.ulgis.com/geoserver/wfs?version=1.3.0&request=GetFeature&outputFormat=application%2Fjson&service=WFS&typeName=drishti:ipms_localbody&srsname=EPSG:3857&cql_filter=localbody_id%3D2&authkey=a1213a5b-5131-41e4-97bb-ebcf80de3923";
//
//        ServiceFeatureTable serviceFeatureTable = new ServiceFeatureTable(wfsUrl);
//
//        // Create a query to retrieve features
//        QueryParameters queryParameters = new QueryParameters();
//        queryParameters.setWhereClause("1=1"); // Query all features, you can customize this query
//
//        // Execute the query
//        Future<FeatureQueryResult> queryResult = serviceFeatureTable.queryFeaturesAsync(queryParameters);
//
//        try {
//            FeatureQueryResult result = queryResult.get();
//
//            // Convert the FeatureQueryResult to JSON
////            String jsonResult = convertFeatureQueryResultToJson(result);
//
////            System.out.println("@@@_-->"+jsonResult);
//            // Handle the JSON data as needed
////            Log.d("WFS JSON", jsonResult);
//        } catch (InterruptedException | ExecutionException e) {
//            System.out.println("@@@_-->"+e.getMessage());
//            e.printStackTrace();
//        }


//        FeatureTable featureTable = new ServiceFeatureTable(wfsUrl);
//        FeatureLayer featureLayer = new FeatureLayer(featureTable);
//        featureLayer.loadAsync();

        // Add the feature layer to the map
//        map.getOperationalLayers().add(featureLayer);

        // Set the map to the MapView
//        mapView.setMap(map);

        // Listen for the load status change
//        featureLayer.addLoadStatusChangedListener(loadStatusChangedEvent -> {
//            if (loadStatusChangedEvent.getNewLoadStatus() == LoadStatus.LOADED) {
//                System.out.println("@@@--->Loaded");
//
//                // Convert the feature layer data to GeoJSON
////                ListenableFuture<String> geoJsonFuture = featureLayer.();
////                geoJsonFuture.addDoneListener(() -> {
////                    try {
////                        String geoJson = geoJsonFuture.get();
////                        // Now you have the GeoJSON data
////                        // You can parse and use it as needed
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                });
//            }

//            if (loadStatusChangedEvent.getNewLoadStatus() == LoadStatus.FAILED_TO_LOAD) {
//                System.out.println("@@@--->FAILED_TO_LOAD");
//
//                // Convert the feature layer data to GeoJSON
////                ListenableFuture<String> geoJsonFuture = featureLayer.();
////                geoJsonFuture.addDoneListener(() -> {
////                    try {
////                        String geoJson = geoJsonFuture.get();
////                        // Now you have the GeoJSON data
////                        // You can parse and use it as needed
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                });
//            }
//
//        });


// Create a WFS feature table and a feature layer from the WFS URL
//        ServiceFeatureTable featureTable = new ServiceFeatureTable(wfsUrl);
//        FeatureLayer wfsLayer = new FeatureLayer(featureTable);
//
//        map.getOperationalLayers().add(wfsLayer);


//        String layerUrl ="https://sdt.ulgis.com/geoserver/wfs?version=1.3.0&request=GetFeature&outputFormat=application%2Fjson&service=WFS&typeName=drishti:ipms_localbody&srsname=EPSG:3857&cql_filter=localbody_id%3D2&authkey=a1213a5b-5131-41e4-97bb-ebcf80de3923";
//// Load the boundary feature layer
//        ServiceFeatureTable featureTable = new ServiceFeatureTable(layerUrl);
//
//// Create a FeatureLayer from the feature table
////        FeatureLayer featureLayer = new FeatureLayer(featureTable);
//        FeatureLayer boundaryFeatureLayer = new FeatureLayer(featureTable);
//        map.getOperationalLayers().add(boundaryFeatureLayer);
//
//// Step 2: Get the Geometry of the Boundary
//
//        QueryParameters queryParameters = new QueryParameters();
//        queryParameters.setWhereClause("1=1"); // Query all features
//
////        System.out.println("@@@--->"+new Gson().toJson( boundaryFeatureLayer.getFeatureTable().getExtent()));
//        ListenableFuture<FeatureQueryResult> queryResult = boundaryFeatureLayer.getFeatureTable().queryFeaturesAsync(queryParameters);
//        queryResult.addDoneListener(() -> {
//            try {
//                Geometry boundaryGeometry = null;
//
//                FeatureQueryResult result = queryResult.get();
//                Iterator<Feature> iterator = result.iterator();
//                if (iterator.hasNext()) {
//                    Feature boundaryFeature = iterator.next();
//                    boundaryGeometry = boundaryFeature.getGeometry();
//                }
//
//                // Step 3: Clip the WMS Layer
//                if (boundaryGeometry != null) {
//
//                    Geometry geom = GeometryEngine.clip(wmsLayer.getFullExtent(), (Envelope) Sample.getLocalBody());
//                    // Now you have a clipped extent, and you can create a new WmsLayer with this extent
//                    // and add it to the map
//                    WmsLayer wms = new WmsLayer()
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });


    }
//
//    private void clip(Geometry geom){
//        WmsLayer wmsLayer = new WmsLayer("https://sdt.ulgis.com/geoserver/kollam_corporation/wms", Collections.singletonList("kollam"));
//                        map.getOperationalLayers().add(wmsLayer);
//                        wmsLayer.addDoneLoadingListener(new Runnable() {
//                            @Override
//                            public void run() {
//                                if(wmsLayer.getLoadStatus() == LoadStatus.LOADED){
//
//                                    Envelope boundingBox = new Envelope(75.332491082717098, 8.8193042072989396, 76.673812899745798, 12.0590424874439, SpatialReferences.getWgs84());
//
//
//                                    // Clip the WMS layer with the polygon
//                                    Geometry clippedGeometry = GeometryEngine.clip(wmsLayer.getFullExtent(), geom.getExtent());
//                                    WmsLayer clippedWmsLayer = new WmsLayer(modifyWmsLayerUrl("https://sdt.ulgis.com/geoserver/kollam_corporation/wms", clippedGeometry),Collections.singletonList("kollam"));
////
////// Add the clipped WMS layer to the map
//                                    map.getOperationalLayers().add(clippedWmsLayer);
////                                    System.out.println("@@@--->" + wmsLayer.getUri().toString());
//
//                                    // Assuming you have a WMS layer named 'wmsLayer'
////                                    wmsLayer.setDefinitionExpression("1=1"); // Reset the expression to display all features
//// Apply the clipping by setting a filter based on your clipGeometry
////                                    wmsLayer.setDefinitionExpression("ST_INTERSECTS(SHAPE, " + clipGeometry + ")");
//
//                                }
//                            }
//                        });
//    }
//
//
    private String modifyWmsLayerUrl(String originalUrl, Geometry clippedGeometry) {
        // Get the extent of the clipped geometry
        Envelope extent = clippedGeometry.getExtent();

        // Create a modified URL with a bounding box parameter
        String modifiedUrl = originalUrl + "?bbox=" +
                extent.getXMin() + "," +
                extent.getYMin() + "," +
                extent.getXMax() + "," +
                extent.getYMax();

        return modifiedUrl;
    }

    private String convertFeatureQueryResultToJson(FeatureQueryResult featureQueryResult) {
        // Convert the FeatureQueryResult to JSON format
        // You can use your preferred JSON library here, such as Gson or JSONObject

        // Example using Gson:
        Gson gson = new Gson();
        return gson.toJson(featureQueryResult);

        // Example using JSONObject:
        // JSONObject json = new JSONObject();
        // // Convert the features to a JSON array
        // JSONArray featuresArray = new JSONArray();
        // for (Feature feature : featureQueryResult) {
        //     // Convert each feature to JSON and add it to the array
        //     JSONObject featureJson = feature.toJson();
        //     featuresArray.put(featureJson);
        // }
        // try {
        //     json.put("features", featuresArray);
        // } catch (JSONException e) {
        //     e.printStackTrace();
        // }
        // return json.toString();

//        return ""; // Replace with your preferred JSON conversion code
    }


    private void setSketchBar() {
        LinearUnit polylineUnit = new LinearUnit(LinearUnitId.METERS);
        AreaUnit polygonUnit = new AreaUnit(AreaUnitId.SQUARE_METERS);
        AngularUnit angularUnit = new AngularUnit(AngularUnitId.DEGREES);
        mSketchEditor = new SketchEditor();
        getViewBinding().mapView.setSketchEditor(mSketchEditor);
        mSketchEditor.addGeometryChangedListener(sketchGeometryChangedEvent -> {
            if (getViewBinding().includeSketchBar.sketchOk.isEnabled()) {
                Geometry sketchGeometry = mSketchEditor.getGeometry();
                if (sketchGeometry != null) {
                    if (sketchGeometry.getGeometryType() == GeometryType.POLYLINE) {
                        double polylineLength = GeometryEngine.lengthGeodetic(sketchGeometry, polylineUnit, GeodeticCurveType.GEODESIC);
                        getViewBinding().includeSketchBar.lengthAreaRadiusText.setText(String.format(Locale.ENGLISH, "%s %.2f m", getResources().getString(R.string.dashboard_length), polylineLength));
                        getViewBinding().includeSketchBar.sketchUndo.setEnabled(mSketchEditor.canUndo());
                        getViewBinding().includeSketchBar.sketchRedo.setEnabled(mSketchEditor.canRedo());
                        Polyline polylinePoint = (Polyline) GeometryEngine.project(sketchGeometry, SpatialReferences.getWgs84());
                        if (polylinePoint.getParts().size() > 0 && polylinePoint.getParts().get(0).size() > 0) {
                            double latitude = polylinePoint.getParts().get(0).getPoint(polylinePoint.getParts().get(0).size() - 1).getY();
                            double longitude = polylinePoint.getParts().get(0).getPoint(polylinePoint.getParts().get(0).size() - 1).getX();
                            getViewBinding().latLongTextView.setText(String.format(Locale.ENGLISH, "Lat  : %.4f\nLon : %.4f", latitude, longitude));
                        }
                    } else if (sketchGeometry.getGeometryType() == GeometryType.POLYGON) {
                        double polygonArea = GeometryEngine.areaGeodetic(sketchGeometry, polygonUnit, GeodeticCurveType.GEODESIC);
                        getViewBinding().includeSketchBar.lengthAreaRadiusText.setText(String.format(Locale.ENGLISH, "%s %.2f ㎡", getResources().getString(R.string.dashboard_area), polygonArea));
                        getViewBinding().includeSketchBar.sketchUndo.setEnabled(mSketchEditor.canUndo());
                        getViewBinding().includeSketchBar.sketchRedo.setEnabled(mSketchEditor.canRedo());
                        Polygon polygonPoint = (Polygon) GeometryEngine.project(sketchGeometry, SpatialReferences.getWgs84());
                        if (polygonPoint.getParts().size() > 0 && polygonPoint.getParts().get(0).size() > 0) {
                            double latitude = polygonPoint.getParts().get(0).getPoint(polygonPoint.getParts().get(0).size() - 1).getY();
                            double longitude = polygonPoint.getParts().get(0).getPoint(polygonPoint.getParts().get(0).size() - 1).getX();
                            getViewBinding().latLongTextView.setText(String.format(Locale.ENGLISH, "Lat  : %.4f\nLon : %.4f", latitude, longitude));
                        }
                    } else if (sketchGeometry.getGeometryType() == GeometryType.MULTIPOINT) {
                        Multipoint circlePoint = (Multipoint) GeometryEngine.project(sketchGeometry, SpatialReferences.getWgs84());
                        if (circlePoint.getPoints().size() > 1) {
                            mSketchEditor.removeSelectedVertex();
                            double radius = GeometryEngine.distanceGeodetic(circlePoint.getPoints().get(0), circlePoint.getPoints().get(circlePoint.getPoints().size() - 1), polylineUnit, angularUnit, GeodeticCurveType.GEODESIC).getDistance();
                            getViewBinding().includeSketchBar.lengthAreaRadiusText.setText(String.format(Locale.ENGLISH, "%s %.2f m", getResources().getString(R.string.dashboard_radius), radius));
                            double latitude = circlePoint.getPoints().get(0).getY();
                            double longitude = circlePoint.getPoints().get(0).getX();
                            getViewBinding().latLongTextView.setText(String.format(Locale.ENGLISH, "Lat  : %.4f\nLon : %.4f", latitude, longitude));
                            measurementOverlay.getGraphics().clear();
                            ARCGISCircle locationRadius =
                                    new ARCGISCircle
                                            .Builder(this, SpatialReferences.getWgs84())
                                            .center(new Point(longitude, latitude))
                                            .radius(radius)
                                            .strokeColor(Color.RED)
                                            .strokeWidth(getResources().getDimensionPixelOffset(R.dimen.dimen_half_dp))
                                            .fillColor(Color.parseColor("#20FF0000"))
                                            .fillPattern(SimpleFillSymbol.Style.SOLID)
                                            .build();
                            locationRadius.addCircleOn(measurementOverlay);
                        }
                        if (circlePoint.getPoints().size() == 0) {
                            measurementOverlay.getGraphics().clear();
                            getViewBinding().includeSketchBar.lengthAreaRadiusText.setText(String.format(Locale.ENGLISH, "%s %.2f m", getResources().getString(R.string.dashboard_radius), 0.0));
                        }
                    }
                }
            }
        });
    }

    private void setScaleBar() {
        Scalebar mScaleBar = new Scalebar(getViewBinding().mapView.getContext());
        mScaleBar.setStyle(Style.LINE);
        mScaleBar.setAlignment(Scalebar.Alignment.CENTER);
        mScaleBar.setLineColor(Color.RED);
        mScaleBar.setFillColor(ContextCompat.getColor(this, R.color.colorRed));
        mScaleBar.setTextSize(getResources().getDimensionPixelSize(R.dimen.standard_text_size_small_xxx));
        mScaleBar.setTypeface(Objects.requireNonNull(ResourcesCompat.getFont(this, R.font.roboto_medium)));
        mScaleBar.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        mScaleBar.addToMapView(getViewBinding().mapView);
    }


    private void sketchUndoOnClick() {
        if (mSketchEditor != null && mSketchEditor.canUndo()) {
            mSketchEditor.undo();
        }
    }

    private void sketchRedoOnClick() {
        if (mSketchEditor != null && mSketchEditor.canRedo()) {
            mSketchEditor.redo();
        }
    }

    private void sketchCancelOnClick() {
        getViewBinding().includeSketchBar.sketchBar.setVisibility(View.GONE);
        findViewById(R.id.includeSearchView).setVisibility(View.VISIBLE);
        findViewById(R.id.rvDashboardMenu).setVisibility(View.VISIBLE);
        if (measurementOverlay != null) {
            measurementOverlay.getGraphics().clear();
        }
        if (mSketchEditor != null) {
            mSketchEditor.stop();
        }
    }

    private void sketchOkOnClick() {
        SimpleLineSymbol mLineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.RED, 3);
        SimpleFillSymbol mFillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.CROSS, Color.parseColor("#10FF0000"), mLineSymbol);
        if (mSketchEditor != null && measurementOverlay != null) {
            boolean circleSketchValid = true;
            if (mSketchEditor.getGeometry().getGeometryType() == GeometryType.MULTIPOINT) {
                if (measurementOverlay.getGraphics().size() == 0) {
                    circleSketchValid = false;
                }
            }
            if (mSketchEditor.isSketchValid() && circleSketchValid) {
                Geometry sketchGeometry = mSketchEditor.getGeometry();
                getViewBinding().includeSketchBar.sketchRedo.setEnabled(false);
                getViewBinding().includeSketchBar.sketchUndo.setEnabled(false);
                getViewBinding().includeSketchBar.sketchOk.setEnabled(false);
                mSketchEditor.stop();
                if (sketchGeometry != null) {
                    Graphic graphic = new Graphic(sketchGeometry);
                    if (graphic.getGeometry().getGeometryType() == GeometryType.POLYGON) {
                        graphic.setSymbol(mFillSymbol);
                        measurementOverlay.getGraphics().add(graphic);
                    } else if (graphic.getGeometry().getGeometryType() == GeometryType.POLYLINE) {
                        graphic.setSymbol(mLineSymbol);
                        measurementOverlay.getGraphics().add(graphic);
                    } else if (graphic.getGeometry().getGeometryType() == GeometryType.MULTIPOINT) {
                        if (measurementOverlay.getGraphics().size() > 0) {
                            Graphic circleGraphics = new Graphic(measurementOverlay.getGraphics().get(0).getGeometry());
                            circleGraphics.setSymbol(mFillSymbol);
                            measurementOverlay.getGraphics().clear();
                            measurementOverlay.getGraphics().add(circleGraphics);
                        }
                    }
                }
            } else {
                showDialog(getString(R.string.dashboard_measurement), getString(R.string.dashboard_invalid_geometry), null, null);
            }
        }
    }

    private void homeButtonClick() {
        if (goToLocationOverlay != null) {
            goToLocationOverlay.getGraphics().clear();
        }
        sketchCancelOnClick();
        getViewBinding().mapView.setViewpoint(initialViewPoint);
    }

    private void zoomInButtonClick() {
        double scale = getViewBinding().mapView.getMapScale();
        getViewBinding().mapView.setViewpointScaleAsync(scale - (scale / 2));
    }

    private void addProperty() {
        showAssetsListDialog();
    }

    private void zoomOutButtonClick() {
        double scale = getViewBinding().mapView.getMapScale();
        getViewBinding().mapView.setViewpointScaleAsync(scale + (scale / 2));
    }

    private void locationButtonClick() {
        new GpsUtils(this).turnGPSOn(REQUEST_CODE_GPS, isGPSEnable -> {
            if (isGPSEnable) {
                LocationDialog.newInstance(type -> {
                    if (type == LocationDialog.LOCATION_TYPE_GO_TO_XY) {
                        goToLocationButtonClick();
                    } else if (type == LocationDialog.LOCATION_TYPE_CURRENT) {
                        if (goToLocationOverlay != null) {
                            goToLocationOverlay.getGraphics().clear();
                        }
                        checkLocationPermission();
                    }
                }).show(this.getSupportFragmentManager(), LocationDialog.TAG);
            }
        });
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            initializeLocationDisplay();
        }
    }

    private void initializeLocationDisplay() {
        LocationDisplay locationDisplay = getViewBinding().mapView.getLocationDisplay();
        locationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.RECENTER);
        locationDisplay.addLocationChangedListener(locationChangedEvent -> {
            if (locationChangedEvent.getLocation().getPosition() != null) {
                double latitude = locationChangedEvent.getLocation().getPosition().getY();
                double longitude = locationChangedEvent.getLocation().getPosition().getX();
                onGoToLocationSuccess(latitude, longitude);
                locationDisplay.stop();
            }
        });
        locationDisplay.startAsync();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeLocationDisplay();
            } else {
                showToast("Permission Denied By User \nPlease enable manually from settings.");
            }
        }
    }

    private void goToLocationButtonClick() {
        if (goToLocationOverlay != null) {
            goToLocationOverlay.getGraphics().clear();
        }
        GoToLocationDialog.newInstance(this).show(this.getSupportFragmentManager(), GoToLocationDialog.TAG);
    }

    private void measurementButtonClick() {
        MeasureDialog.newInstance(this).show(this.getSupportFragmentManager(), MeasureDialog.TAG);
    }

    @Override
    public void onGeometryClick(int type) {
        if (measurementOverlay == null) {
            measurementOverlay = new GraphicsOverlay();
            getViewBinding().mapView.getGraphicsOverlays().add(measurementOverlay);
        }
        if (mSketchEditor != null) {
            sketchCancelOnClick();
            getViewBinding().includeSketchBar.sketchBar.setVisibility(View.VISIBLE);
            findViewById(R.id.includeSearchView).setVisibility(View.GONE);
            findViewById(R.id.rvDashboardMenu).setVisibility(View.GONE);
            getViewBinding().includeSketchBar.sketchUndo.setEnabled(false);
            getViewBinding().includeSketchBar.sketchRedo.setEnabled(false);
            getViewBinding().includeSketchBar.sketchOk.setEnabled(true);
            if (type == MeasureDialog.GEOMETRY_TYPE_POLYLINE) {
                getViewBinding().includeSketchBar.lengthAreaRadiusText.setText(String.format(Locale.ENGLISH, "%s %.2f m", getResources().getString(R.string.dashboard_length), 0.0));
                mSketchEditor.start(SketchCreationMode.POLYLINE);
            } else if (type == MeasureDialog.GEOMETRY_TYPE_POLYGON) {
                getViewBinding().includeSketchBar.lengthAreaRadiusText.setText(String.format(Locale.ENGLISH, "%s %.2f ㎡", getResources().getString(R.string.dashboard_area), 0.0));
                mSketchEditor.start(SketchCreationMode.POLYGON);
            } else if (type == MeasureDialog.GEOMETRY_TYPE_CIRCLE) {
                getViewBinding().includeSketchBar.lengthAreaRadiusText.setText(String.format(Locale.ENGLISH, "%s %.2f m", getResources().getString(R.string.dashboard_radius), 0.0));
                mSketchEditor.start(SketchCreationMode.MULTIPOINT);
            }
        }
    }

    @Override
    public void onGoToLocationSuccess(double latitude, double longitude) {
        try {
            if (goToLocationOverlay == null) {
                goToLocationOverlay = new GraphicsOverlay();
                getViewBinding().mapView.getGraphicsOverlays().add(goToLocationOverlay);
            }
            Point point = new Point(longitude, latitude, SpatialReferences.getWgs84());
            BitmapDrawable pinStarBlueDrawable = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.ic_marker_goto_xy);
            PictureMarkerSymbol pinSymbol = PictureMarkerSymbol.createAsync(Objects.requireNonNull(pinStarBlueDrawable)).get();
            pinSymbol.setHeight(getResources().getDimensionPixelOffset(R.dimen.standard_icon_size_medium));
            pinSymbol.setWidth(getResources().getDimensionPixelOffset(R.dimen.standard_icon_size_medium));
            pinSymbol.loadAsync();
            Graphic graphic = new Graphic(point, pinSymbol);
            goToLocationOverlay.getGraphics().add(graphic);
            getViewBinding().mapView.setViewpoint(new Viewpoint(latitude, longitude, StaticData.getArcGisScale()[11]));
            getViewBinding().latLongTextView.setText(String.format(Locale.ENGLISH, "Lat  : %.4f\nLon : %.4f", latitude, longitude));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    class MapTouchListener extends DefaultMapViewOnTouchListener {

        public MapTouchListener(Context context, MapView mapView) {
            super(context, mapView);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            android.graphics.Point screenPoint = new android.graphics.Point(Math.round(e.getX()), Math.round(e.getY()));
            Point mapPoint = getViewBinding().mapView.screenToLocation(screenPoint);
            if (mapPoint != null) {
                Point wgs84Point = (Point) GeometryEngine.project(mapPoint, SpatialReferences.getWgs84());
                double latitude = wgs84Point.getY();
                double longitude = wgs84Point.getX();
                getViewBinding().latLongTextView.setText(String.format(Locale.ENGLISH, "Lat  : %.4f\nLon : %.4f", latitude, longitude));
            }
            if (getViewBinding().includeSketchBar.sketchBar.getVisibility() != View.VISIBLE &&
                    getViewBinding().includeZoomToBar.zoomToBar.getVisibility() != View.VISIBLE && !featureInfoClicked) {
                featureInfoClicked = true;
                identifyResult(screenPoint);
            }
            return true;
        }
    }


    @Override
    protected void onPause() {
        getViewBinding().mapView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getViewBinding().mapView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getViewBinding().mapView != null) {
            getViewBinding().mapView.dispose();
        }
    }


    @Override
    public void onBackPressed() {
        if (getViewBinding().includeSketchBar.sketchBar.getVisibility() == View.VISIBLE) {
            sketchCancelOnClick();
        } else {
            finishAffinity();
        }
    }


    /**
     * Performs an identify on layers at the given screenpoint and calls handleIdentifyResults(...) to process them.
     *
     * @param screenPoint in Android graphic coordinates.
     */
    private void identifyResult(android.graphics.Point screenPoint) {

        final ListenableFuture<List<IdentifyLayerResult>> identifyLayerResultsFuture = getViewBinding().mapView
                .identifyLayersAsync(screenPoint, 6, false, 5);

        identifyLayerResultsFuture.addDoneListener(() -> {
            try {
                List<IdentifyLayerResult> identifyLayerResults = identifyLayerResultsFuture.get();
                handleIdentifyResults(identifyLayerResults);
            } catch (InterruptedException | ExecutionException e) {
                Log.e(TAG, "Error identifying results: " + e.getMessage());
            }
        });
    }

    private void handleIdentifyResults(List<IdentifyLayerResult> identifyLayerResults) {
        Set<String> featureIDSet = new HashSet<>();
        for (IdentifyLayerResult identifyLayerResult : identifyLayerResults) {
            if (identifyLayerResult.getElements().size() > 0) {
                Document document = Jsoup.parse(identifyLayerResult.getElements().get(0).getAttributes().values().toString());
                Elements tables = document.select("TABLE"); //select the second table:     "Group Block"
                for (int k = 0; k < tables.size(); k++) {
                    Element table = tables.get(k); //select the second table:     "Group Block"
                    if (table.className().equalsIgnoreCase("featureInfo")) {
                        Elements rows = table.select("TR");
                        for (int i = 0; i < rows.size(); i++) {
                            Element row = rows.get(i);
                            Elements cols = row.select("TD");
                            if (cols.size() > 0) {
                                String featureID = cols.get(0).toString().toLowerCase()
                                        .replace("<td>", "")
                                        .replace("</td>", "");
                                if (featureID.length() > 0) {
                                    featureIDSet.add(featureID);
                                }
                            }
                        }
                    }
                }
            }
        }

        if (featureIDSet.size() > 0) {
            presenter.getFeatureInfoData(featureIDSet);
        } else {
            featureInfoClicked = false;
        }
    }


    @Override
    public void onFeatureDetailsFetched(ArrayList<FeatureDataResponse.Data> data) {
        featureInfoClicked = false;
        setPointsSelected(data);
        if (data.get(0).getMinimalInfo() != null) {
            minimalInfoDialog = new Dialog(this);
            minimalInfoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            minimalInfoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            minimalInfoDialog.setCancelable(false);
            minimalInfoDialog.setCanceledOnTouchOutside(false);
            View contentView = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.dialog_minimal_info, null, false);
            minimalInfoDialog.setContentView(contentView);
            final Window window = minimalInfoDialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawableResource(R.color.colorTransparent);
            window.setGravity(Gravity.CENTER);
            final MinimalInfoAdapter adapter = new MinimalInfoAdapter();
            TextView counter = minimalInfoDialog.findViewById(R.id.minimalInfoCounter);
            Button moreButton = minimalInfoDialog.findViewById(R.id.btnMore);
            Button zoomButton = minimalInfoDialog.findViewById(R.id.btnZoomTo);
            ((TextView) minimalInfoDialog.findViewById(R.id.txtDialogTitle)).setText(String.format(Locale.getDefault(), "%s ( %d )", getString(R.string.dashboard_total_features), data.size()));
            minimalInfoDialog.findViewById(R.id.btnCloseDialog).setOnClickListener(view -> {
                dataOverlay.getGraphics().clear();
                minimalInfoDialog.dismiss();
            });
            minimalInfoDialog.findViewById(R.id.btnMinimalInfoPrev).setOnClickListener(view -> {
                String txt = counter.getText().toString();
                int count = Integer.parseInt(txt) - 1;
                if (count > 0 && count < data.size()) {
                    FeatureDataResponse.Data singleData = data.get(count - 1);
                    adapter.setLocalDataSet(singleData.getMinimalInfo());
                    counter.setText(String.valueOf(count));

                    if ((singleData.getDetailedInfo() != null &&
                            singleData.getDetailedInfo().size() > 0) ||
                            (singleData.getImages() != null &&
                                    singleData.getImages().size() > 0)) {
                        moreButton.setVisibility(View.VISIBLE);
                    } else {
                        moreButton.setVisibility(View.INVISIBLE);
                    }
                }
            });
            minimalInfoDialog.findViewById(R.id.btnMinimalInfoNext).setOnClickListener(view -> {
                String txt = counter.getText().toString();
                int count = Integer.parseInt(txt) + 1;
                if (count > 0 && count <= data.size()) {
                    FeatureDataResponse.Data singleData = data.get(count - 1);
                    adapter.setLocalDataSet(singleData.getMinimalInfo());
                    counter.setText(String.valueOf(count));

                    if ((singleData.getDetailedInfo() != null &&
                            singleData.getDetailedInfo().size() > 0) ||
                            (singleData.getImages() != null &&
                                    singleData.getImages().size() > 0)) {
                        moreButton.setVisibility(View.VISIBLE);
                    } else {
                        moreButton.setVisibility(View.INVISIBLE);
                    }
                }
            });
            moreButton.setOnClickListener(view -> {
                String txt = counter.getText().toString();
                int count = Integer.parseInt(txt);
                if (count > 0 && count <= data.size()) {
                    AppCacheData.getOurInstance().setSelectedFeatureInfoDetails(data.get(count - 1));
                    Intent intent = new Intent(DashboardActivity.this, DetailedInfoActivity.class);
                    startActivity(intent);
                }
            });
            zoomButton.setOnClickListener(view -> {
                String txt = counter.getText().toString();
                int count = Integer.parseInt(txt);
                if (count > 0 && count <= data.size()) {
                    FeatureDataResponse.Data data1 = data.get(count - 1);
                    FeatureDataResponse.Geom geom = data1.getGeom();
                    if (geom.getType().equalsIgnoreCase(AppConstants.GEOM_TYPE_POINT)) {
                        if (geom.getCoordinates() != null && geom.getCoordinates().size() == 2 && geom.getCoordinates().get(0) instanceof Double) {
                            double latitude = (double) geom.getCoordinates().get(0);
                            double longitude = (double) geom.getCoordinates().get(1);
                            Point point = new Point(latitude, longitude, SpatialReferences.getWgs84());
                            getViewBinding().mapView.setViewpointCenterAsync(point, StaticData.getArcGisScale()[16]);
                        }
                    }
                    if (geom.getType().equalsIgnoreCase(AppConstants.GEOM_TYPE_MULTI_LINE_STRING)) {
                        if (geom.getCoordinates() != null && geom.getCoordinates().size() > 0 && geom.getCoordinates().get(0) instanceof ArrayList) {
                            for (Object item : geom.getCoordinates()) {
                                PointCollection linePoints = new PointCollection(SpatialReferences.getWgs84());
                                if (item instanceof ArrayList) {
                                    ArrayList<Object> objects = (ArrayList<Object>) item;
                                    for (Object obj : objects) {
                                        if (obj instanceof ArrayList) {
                                            ArrayList<Object> coordinates = (ArrayList<Object>) obj;
                                            if (coordinates.size() == 2 && coordinates.get(0) instanceof Double) {
                                                double latitude = (double) coordinates.get(0);
                                                double longitude = (double) coordinates.get(1);
                                                linePoints.add(latitude, longitude);
                                            }
                                        }
                                    }
                                }
                                Polyline line = new Polyline(linePoints);
                                getViewBinding().mapView.setViewpointGeometryAsync(line);
                            }

                        }
                    }

                }
                setZoomInfoVisibility(false);
                minimalInfoDialog.hide();
            });
            RecyclerView rvMinimalInfo = minimalInfoDialog.findViewById(R.id.rvMinimalInfo);
            ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) rvMinimalInfo.getLayoutParams();
            lp.matchConstraintMaxHeight = CommonUtils.getScreenWidth(this);
            rvMinimalInfo.setLayoutParams(lp);
            rvMinimalInfo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            rvMinimalInfo.setAdapter(adapter);
            adapter.setLocalDataSet(data.get(0).getMinimalInfo());
            counter.setText("1");
            minimalInfoDialog.show();
        }
    }


    public void showAssetsListDialog() {
        Dialog assetDialog = new Dialog(this);
        assetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        assetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        assetDialog.setCancelable(false);
        assetDialog.setCanceledOnTouchOutside(false);
        View contentView = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.dialog_asset_list, null, false);
        assetDialog.setContentView(contentView);
        final Window window = assetDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawableResource(R.color.colorTransparent);
        window.setGravity(Gravity.CENTER);
        ((TextView) assetDialog.findViewById(R.id.txtDialogTitle)).setText(getString(R.string.dashboard_layers));
        assetDialog.findViewById(R.id.btnCloseDialog).setOnClickListener(view -> {
            assetDialog.dismiss();
        });
        assetDialog.findViewById(R.id.txtBuildingAsset).setOnClickListener(view -> {
            assetDialog.dismiss();
            AppCacheData.getOurInstance().setAssetUpdate(false);
            AppCacheData.getOurInstance().setBuildingAssetPropertyAdded(false);
            AppCacheData.getOurInstance().setBuildingAssetData(new FetchAssetDataResponse.Data());
            startActivity(new Intent(DashboardActivity.this, BuildingAssetsActivity.class));
        });

        assetDialog.findViewById(R.id.txtRoadUtility).setOnClickListener(view -> {
            assetDialog.dismiss();
            AppCacheData.getOurInstance().setAssetUpdate(false);
            startActivity(new Intent(DashboardActivity.this, UtilityActivity.class));
        });
        assetDialog.findViewById(R.id.txtWaterBody).setOnClickListener(view -> {
            assetDialog.dismiss();
            AppCacheData.getOurInstance().setAssetUpdate(false);
            startActivity(new Intent(DashboardActivity.this, WaterBodyActivity.class));
        });

        assetDialog.findViewById(R.id.txtOtherAssets).setOnClickListener(view -> {
            assetDialog.dismiss();
            AppCacheData.getOurInstance().setAssetUpdate(false);
            startActivity(new Intent(DashboardActivity.this, OtherAssetsActivity.class));
        });
        assetDialog.show();
    }

    @Override
    public void onDashboardInternetError() {
        getAdaptiveStateLayout().showState(AdaptiveStateConstants.ADAPTIVE_STATE_NO_NETWORK);
    }

    @Override
    public void onDashboardAPIError() {
        getAdaptiveStateLayout().showState(AdaptiveStateConstants.ADAPTIVE_STATE_SERVER_ERROR);
    }

    @Override
    public void showProgressBar() {
        getAdaptiveStateLayout().showState(AdaptiveStateConstants.ADAPTIVE_STATE_PROGRESS_BAR);
    }

    @Override
    public void hideProgressBar() {
        getAdaptiveStateLayout().showActualState();
    }

    @Override
    public void onAdaptiveStateClick(int stateType, int buttonId) {
        if (buttonId == R.id.btnTryAgainInternet) {
            if (isNetworkConnected()) {
                presenter.getDashboardDetails();
            } else {
                super.onInternetConnectionFailure();
            }
        } else if (buttonId == R.id.btnTryAgainServerError) {
            presenter.getDashboardDetails();
        }
    }


    public void setPointsSelected(ArrayList<FeatureDataResponse.Data> data) {
        dataOverlay = new GraphicsOverlay();
        getViewBinding().mapView.getGraphicsOverlays().add(dataOverlay);
        mPointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFF2EA0A, 12);
        mLineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFFFF8800, 4);
        outline = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFFFF0000, 1);
        mPointSymbol.setOutline(outline);
        for (FeatureDataResponse.Data geoms : data) {
            if (geoms.getGeom() != null) {
                FeatureDataResponse.Geom geom = geoms.getGeom();
                if (geom.getType().equalsIgnoreCase(AppConstants.GEOM_TYPE_POINT)) {
                    if (geom.getCoordinates() != null && geom.getCoordinates().size() == 2 && geom.getCoordinates().get(0) instanceof Double) {
                        double latitude = (double) geom.getCoordinates().get(0);
                        double longitude = (double) geom.getCoordinates().get(1);
                        Point point = new Point(latitude, longitude, SpatialReferences.getWgs84());
                        Graphic gPoint = new Graphic(point, mPointSymbol);
                        dataOverlay.getGraphics().add(gPoint);
                    }
                }
                if (geom.getType().equalsIgnoreCase(AppConstants.GEOM_TYPE_MULTI_LINE_STRING)) {
                    if (geom.getCoordinates() != null && geom.getCoordinates().size() > 0 && geom.getCoordinates().get(0) instanceof ArrayList) {
                        for (Object item : geom.getCoordinates()) {
                            PointCollection linePoints = new PointCollection(SpatialReferences.getWgs84());
                            if (item instanceof ArrayList) {
                                ArrayList<Object> objects = (ArrayList<Object>) item;
                                for (Object obj : objects) {
                                    if (obj instanceof ArrayList) {
                                        ArrayList<Object> coordinates = (ArrayList<Object>) obj;
                                        if (coordinates.size() == 2 && coordinates.get(0) instanceof Double) {
                                            double latitude = (double) coordinates.get(0);
                                            double longitude = (double) coordinates.get(1);
                                            linePoints.add(latitude, longitude);
                                        }
                                    }
                                }
                            }
                            Polyline line = new Polyline(linePoints);
                            Graphic gLine = new Graphic(line, mLineSymbol);
                            dataOverlay.getGraphics().add(gLine);
                        }

                    }
                }
            }
        }

    }

}
