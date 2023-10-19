package in.ults.ipms.ui.utility.drainage.map;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.geometry.GeodeticCurveType;
import com.esri.arcgisruntime.geometry.Geometry;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.GeometryType;
import com.esri.arcgisruntime.geometry.ImmutablePart;
import com.esri.arcgisruntime.geometry.LinearUnit;
import com.esri.arcgisruntime.geometry.LinearUnitId;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.SketchCreationMode;
import com.esri.arcgisruntime.mapping.view.SketchEditor;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.inject.Inject;

import in.ults.ipms.BuildConfig;
import in.ults.ipms.R;
import in.ults.ipms.data.StaticData;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.data.network.model.response.GeomPolyLine;
import in.ults.ipms.databinding.FragmentDrainageMapBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseFragment;
import in.ults.ipms.ui.mapoverlays.MapOverlayBottomSheet;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.views.adaptivestate.AdaptiveStateConstants;

public class DrainageMapFragment extends BaseFragment<FragmentDrainageMapBinding> implements IDrainageMapView {

    public static final String TAG = DrainageMapFragment.class.getSimpleName();

    @Inject
    IDrainageMapPresenter<IDrainageMapView, IDrainageMapInteractor> presenter;

    private ArcGISMap map;

    private Viewpoint initialViewPoint;

    private GraphicsOverlay measurementOverlay;

    private SketchEditor mSketchEditor;

    Set<String> selectedMapLayers;

    ArrayList<DashboardResponse.LayerCategoryChild> layers;

    GeomPolyLine geom;

    public static DrainageMapFragment newInstance() {
        return new DrainageMapFragment();
    }

    @Override
    protected FragmentDrainageMapBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentDrainageMapBinding.inflate(inflater, container, attachToParent);
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
    protected String getToolbarTitle() {
        return getString(R.string.drainage_details_location);
    }

    @Override
    protected void init(View v) {
        super.init(v);
        selectedMapLayers = new HashSet<>();
        layers = new ArrayList<>();
        ArcGISRuntimeEnvironment.setApiKey(BuildConfig.ARC_GIS_API_KEY);
        ArcGISRuntimeEnvironment.setLicense(BuildConfig.ARC_GIS_LICENSE);
        getViewBinding().mapView.setAttributionTextVisible(false);
        getViewBinding().includeSketchBar.sketchOk.setOnClickListener(view -> sketchOkOnClick());
        getViewBinding().includeSketchBar.sketchCancel.setOnClickListener(view -> sketchResetOnClick());
        getViewBinding().includeSketchBar.sketchRedo.setOnClickListener(view -> sketchRedoOnClick());
        getViewBinding().includeSketchBar.sketchUndo.setOnClickListener(view -> sketchUndoOnClick());
        getViewBinding().btnDrainageMapNext.setOnClickListener(view -> {
            if (geom!=null && geom.getCoordinates().size()>0) {
                getBaseActivity().launchDrainageDetails(true, false, geom);
            } else {
                showDialog(getString(R.string.drainage_details_location), getString(R.string.err_drainage_details_location), null, null);
            }
        });
        getViewBinding().fabLayers.setOnClickListener(view1 -> {
            MapOverlayBottomSheet layersBottomSheet = MapOverlayBottomSheet.newInstance();
            layersBottomSheet.setArcGISMap(map);
            layersBottomSheet.setLayerList(layers);
            layersBottomSheet.setSelectedLayersIDs(selectedMapLayers);
            layersBottomSheet.show(getParentFragmentManager(), MapOverlayBottomSheet.TAG);
        });
    }

    @Override
    protected void setUp(View view) {
        super.setUp(view);
        presenter.fetchRoadDropDown();
    }

    @Override
    public void setBaseMapAndDefaultCentre() {
        if (AppCacheData.getOurInstance().getDashboardDetails() != null) {
            if (AppCacheData.getOurInstance().getDashboardDetails().getLocalBody() != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        initialViewPoint = new Viewpoint(AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getDefaultLat(),
                AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getDefaultLon(),
                StaticData.getArcGisScale()[AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getDefaultZoom()]);
        map = new ArcGISMap(BasemapStyle.ARCGIS_IMAGERY);
        map.setInitialViewpoint(initialViewPoint);
        map.setMinScale(StaticData.getArcGisScale()[4]);
        map.setMaxScale(StaticData.getArcGisScale()[23]);
        getViewBinding().mapView.setMap(map);
        map.addDoneLoadingListener(() -> {
            if (map.getLoadStatus() == LoadStatus.LOADED) {
                setBaseMap();
                setSketchBar();
                if (AppCacheData.getOurInstance().isAssetUpdate()) {
                    setEditData();
                }
            }
        });
    }

    public void setDrainageGeom(GeomPolyLine geom){
        if (geom != null && geom.getCoordinates().size() > 0) {
            SimpleLineSymbol mLineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.RED, 3);
            if (geom.getType().equalsIgnoreCase(AppConstants.GEOM_TYPE_MULTI_LINE_STRING)) {
                for (ArrayList<ArrayList<Double>> item : geom.getCoordinates()) {
                    PointCollection linePoints = new PointCollection(SpatialReferences.getWgs84());
                    for (ArrayList<Double> coordinates : item) {
                        if (coordinates.size() == 2) {
                            double latitude = coordinates.get(0);
                            double longitude = coordinates.get(1);
                            linePoints.add(latitude, longitude);
                        }
                    }
                    Polyline line = new Polyline(linePoints);
                    LinearUnit polylineUnit = new LinearUnit(LinearUnitId.METERS);
                    double polylineLength = GeometryEngine.lengthGeodetic(line, polylineUnit, GeodeticCurveType.GEODESIC);
                    getViewBinding().includeSketchBar.lengthAreaRadiusText.setText(String.format(Locale.ENGLISH, "%s %.2f m", getResources().getString(R.string.dashboard_length), polylineLength));
                    getViewBinding().mapView.setViewpointGeometryAsync(line);
                    if (mSketchEditor != null && measurementOverlay != null) {
                        getViewBinding().includeSketchBar.sketchRedo.setEnabled(false);
                        getViewBinding().includeSketchBar.sketchUndo.setEnabled(false);
                        getViewBinding().includeSketchBar.sketchOk.setEnabled(false);
                        mSketchEditor.stop();
                        Graphic graphic = new Graphic(line);
                        if (graphic.getGeometry().getGeometryType() == GeometryType.POLYLINE) {
                            graphic.setSymbol(mLineSymbol);
                            measurementOverlay.getGraphics().add(graphic);
                        }
                    }
                }

            }
        }
    }


    private void setBaseMap() {
        ArrayList<DashboardResponse.BaseLayers> baseLayerData = AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getBaseLayers();
        for (DashboardResponse.BaseLayers layer : baseLayerData) {
            DashboardResponse.LayerCategoryChild childLayers = new DashboardResponse.LayerCategoryChild();
            String[] name = layer.getLayerName().split(":");
            String layerName = name.length > 0 ? name[name.length - 1] : "";
            childLayers.setLayerType(layer.getLayerType());
            childLayers.setLabel(layer.getLabel());
            childLayers.setLayerName(layerName);
            childLayers.setUrl(layer.getUrl());
            childLayers.setValue(layer.getLabel());
            layers.add(childLayers);
        }
        layers.add(AppCacheData.getOurInstance().getDashboardDetails().getWardBoundary());

        if (AppCacheData.getOurInstance().getUtilitySpinnerData() != null &&
                AppCacheData.getOurInstance().getUtilitySpinnerData().getLayerDetails() != null) {
            layers.add(AppCacheData.getOurInstance().getUtilitySpinnerData().getLayerDetails().getDrainage());
        }
    }


    public void setEditData() {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getDrainageDetails() != null) {
            geom = AppCacheData.getOurInstance().getBuildingAssetData().getDrainageDetails().getGeom();
            setDrainageGeom(geom);
        }

    }

    private void setSketchBar() {
        LinearUnit polylineUnit = new LinearUnit(LinearUnitId.METERS);
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
                    }
                }
            }
        });

        if (measurementOverlay == null) {
            measurementOverlay = new GraphicsOverlay();
            getViewBinding().mapView.getGraphicsOverlays().add(measurementOverlay);
        }
        if (mSketchEditor != null) {
            sketchResetOnClick();
        }
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

    private void sketchResetOnClick() {
        if (measurementOverlay != null) {
            measurementOverlay.getGraphics().clear();
        }
        if (mSketchEditor != null) {
            mSketchEditor.stop();
            getViewBinding().includeSketchBar.sketchBar.setVisibility(View.VISIBLE);
            getViewBinding().includeSketchBar.sketchUndo.setEnabled(false);
            getViewBinding().includeSketchBar.sketchRedo.setEnabled(false);
            getViewBinding().includeSketchBar.sketchOk.setEnabled(true);
            geom = null;
            getViewBinding().includeSketchBar.lengthAreaRadiusText.setText(String.format(Locale.ENGLISH, "%s %.2f m", getResources().getString(R.string.dashboard_length), 0.0));
            mSketchEditor.start(SketchCreationMode.POLYLINE);
        }
    }


    private void sketchOkOnClick() {
        SimpleLineSymbol mLineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.RED, 3);
        if (mSketchEditor != null && measurementOverlay != null) {
            if (mSketchEditor.isSketchValid()) {
                Geometry sketchGeometry = mSketchEditor.getGeometry();
                geom = new GeomPolyLine();
                geom.setType(AppConstants.GEOM_TYPE_MULTI_LINE_STRING);
                if (sketchGeometry != null) {
                    Polyline polylinePoint = (Polyline) GeometryEngine.project(sketchGeometry, SpatialReferences.getWgs84());
                    ArrayList<ArrayList<ArrayList<Double>>> mainData = new ArrayList<>();
                    for (ImmutablePart part : polylinePoint.getParts()) {
                        ArrayList<ArrayList<Double>> subData = new ArrayList<>();
                        for (Point point : part.getPoints()) {
                            ArrayList<Double> coordinates = new ArrayList<>();
                            double latitude = point.getY();
                            double longitude = point.getX();
                            coordinates.add(longitude);
                            coordinates.add(latitude);
                            subData.add(coordinates);
                        }
                        mainData.add(subData);
                    }
                    geom.setCoordinates(mainData);
                    getViewBinding().includeSketchBar.sketchRedo.setEnabled(false);
                    getViewBinding().includeSketchBar.sketchUndo.setEnabled(false);
                    getViewBinding().includeSketchBar.sketchOk.setEnabled(false);
                    mSketchEditor.stop();
                    Graphic graphic = new Graphic(sketchGeometry);
                    if (graphic.getGeometry().getGeometryType() == GeometryType.POLYLINE) {
                        graphic.setSymbol(mLineSymbol);
                        measurementOverlay.getGraphics().add(graphic);
                    }
                }
            } else {
                showDialog(getString(R.string.road_utility_location), getString(R.string.dashboard_invalid_geometry), null, null);
            }
        }
    }



    @Override
    public void onRoadInternetError() {
        getAdaptiveStateLayout().showState(AdaptiveStateConstants.ADAPTIVE_STATE_NO_NETWORK);
    }

    @Override
    public void onRoadAPIError() {
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
                presenter.fetchRoadDropDown();
            } else {
                super.onInternetConnectionFailure();
            }
        } else if (buttonId == R.id.btnTryAgainServerError) {
            presenter.fetchRoadDropDown();
        }
    }

    @Override
    public void onPause() {
        getViewBinding().mapView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        getViewBinding().mapView.resume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if( getViewBinding().mapView!=null) {
            getViewBinding().mapView.dispose();
        }
        super.onDestroy();
    }
}
