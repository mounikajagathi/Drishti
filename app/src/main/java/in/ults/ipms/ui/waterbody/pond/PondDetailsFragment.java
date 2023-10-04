package in.ults.ipms.ui.waterbody.pond;

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
import in.ults.ipms.data.network.model.response.WaterBodyAssets;
import in.ults.ipms.data.network.model.response.WaterBodySpinnerResponse;
import in.ults.ipms.databinding.FragmentPondDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BaseFragment;
import in.ults.ipms.ui.mapoverlays.MapOverlayBottomSheet;
import in.ults.ipms.utils.AppConstants;

public class PondDetailsFragment extends BaseFragment<FragmentPondDetailsBinding> implements IPondDetailsView, BaseActivity.ImagePickListener {

    public static final String TAG = PondDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_NAME = 1;
    public static final int ERROR_TYPE_PLACE = 2;
    public static final int ERROR_TYPE_OBID = 3;
    public static final int ERROR_TYPE_AREA = 4;
    public static final int ERROR_TYPE_CAPACITY = 5;
    public static final int ERROR_TYPE_MAINTAINED_BY = 6;
    public static final int ERROR_TYPE_USAGE = 7;
    public static final int ERROR_TYPE_ODOUR = 8;
    public static final int ERROR_TYPE_POND_STATUS = 9;
    public static final int ERROR_TYPE_POND_TYPE = 10;
    public static final int ERROR_TYPE_PRESENT_CONDITION = 11;
    public static final int ERROR_TYPE_NATURE = 12;
    public static final int ERROR_TYPE_SIDEWALL = 13;
    public static final int ERROR_TYPE_SIDEWALL_TYPE = 14;
    public static final int ERROR_TYPE_POND_CONDITION = 15;
    public static final int ERROR_TYPE_WARD_NO = 16;
    public static final int ERROR_TYPE_REMARKS = 17;
    public static final int ERROR_TYPE_PHOTO = 18;
    public static final int ERROR_TYPE_LOCATION = 19;

    public static PondDetailsFragment newInstance() {
        return new PondDetailsFragment();
    }

    @Inject
    IPondDetailsPresenter<IPondDetailsView, IPondDetailsInteractor> presenter;

    CommonSpinnerAdapter<WaterBodySpinnerResponse.MaintainedBy> maintainedByAdapter;
    CommonSpinnerAdapter<WaterBodySpinnerResponse.Usage> usageAdapter;
    CommonSpinnerAdapter<WaterBodySpinnerResponse.Odour> odourAdapter;
    CommonSpinnerAdapter<WaterBodySpinnerResponse.PondStatus> pondStatusAdapter;
    CommonSpinnerAdapter<WaterBodySpinnerResponse.PondType> pondTypeAdapter;
    CommonSpinnerAdapter<WaterBodySpinnerResponse.PresentCondition> presentConditionAdapter;
    CommonSpinnerAdapter<WaterBodySpinnerResponse.Nature> natureAdapter;
    CommonSpinnerAdapter<WaterBodySpinnerResponse.Sidewall> sideWallAdapter;
    CommonSpinnerAdapter<WaterBodySpinnerResponse.SidewallType> sideWallTypeAdapter;
    CommonSpinnerAdapter<WaterBodySpinnerResponse.PondCondition> pondConditionAdapter;
    CommonSpinnerAdapter<WaterBodySpinnerResponse.Ward> wardAdapter;

    private ArcGISMap map;
    private Viewpoint initialViewPoint;
    private GraphicsOverlay pointOverlay;
    private double latitude = 0.0;
    private double longitude = 0.0;
    Set<String> selectedMapLayers;
    ArrayList<DashboardResponse.LayerCategoryChild> layers;
    private String photo;
    static final int REQUEST_CODE_IMAGE = 101;

    @Override
    protected FragmentPondDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentPondDetailsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.pond_details_title);
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
        getViewBinding().imgPond.setOnClickListener(v -> captureImage(REQUEST_CODE_IMAGE));
        getViewBinding().btnSubmit.setOnClickListener(v -> submitOnClick());
        getViewBinding().includeMiniMapProperty.fabLayers.setOnClickListener(view1 -> {
            MapOverlayBottomSheet layersBottomSheet = MapOverlayBottomSheet.newInstance();
            layersBottomSheet.setArcGISMap(map);
            layersBottomSheet.setLayerList(layers);
            layersBottomSheet.setSelectedLayersIDs(selectedMapLayers);
            layersBottomSheet.show(getParentFragmentManager(), MapOverlayBottomSheet.TAG);
        });

        if (AppCacheData.getOurInstance().getWaterBodySpinnerData() != null && AppCacheData.getOurInstance().getWaterBodySpinnerData().getDropDownValues() != null) {
            wardAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srWardNo, AppCacheData.getOurInstance().getWaterBodySpinnerData().getDropDownValues().getWard());
            odourAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srOdour, AppCacheData.getOurInstance().getWaterBodySpinnerData().getDropDownValues().getOdours());
            usageAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srUsage, AppCacheData.getOurInstance().getWaterBodySpinnerData().getDropDownValues().getUsages());
            natureAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srNature, AppCacheData.getOurInstance().getWaterBodySpinnerData().getDropDownValues().getNatures());
            pondConditionAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPondCondition, AppCacheData.getOurInstance().getWaterBodySpinnerData().getDropDownValues().getPondConditions());
            pondStatusAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPondStatus, AppCacheData.getOurInstance().getWaterBodySpinnerData().getDropDownValues().getPondStatuses());
            pondTypeAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPondType, AppCacheData.getOurInstance().getWaterBodySpinnerData().getDropDownValues().getPondTypes());
            presentConditionAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPresentCondition, AppCacheData.getOurInstance().getWaterBodySpinnerData().getDropDownValues().getPresentConditions());
            sideWallAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srSideWall, AppCacheData.getOurInstance().getWaterBodySpinnerData().getDropDownValues().getSidewalls());
            sideWallTypeAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srSideWallType, AppCacheData.getOurInstance().getWaterBodySpinnerData().getDropDownValues().getSidewallTypes());
            maintainedByAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srMaintainedBy, AppCacheData.getOurInstance().getWaterBodySpinnerData().getDropDownValues().getMaintainedBy());
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
                if (AppCacheData.getOurInstance().getWaterBodySpinnerData() != null &&
                        AppCacheData.getOurInstance().getWaterBodySpinnerData().getLayerDetails() != null) {
                    layers.add(AppCacheData.getOurInstance().getWaterBodySpinnerData().getLayerDetails().getPond());
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
                    getViewBinding().nsvPondDetails.requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    getViewBinding().nsvPondDetails.requestDisallowInterceptTouchEvent(false);
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
            BitmapDrawable pinStarBlueDrawable = (BitmapDrawable) ContextCompat.getDrawable(getBaseActivity(), R.mipmap.marker_pond);
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
        String name = Objects.requireNonNull(getViewBinding().etPondName.getText()).toString().trim();
        String place = Objects.requireNonNull(getViewBinding().etPlace.getText()).toString().trim();
        String obId = Objects.requireNonNull(getViewBinding().etObId.getText()).toString().trim();
        String area = Objects.requireNonNull(getViewBinding().etArea.getText()).toString().trim();
        String capacity = Objects.requireNonNull(getViewBinding().etCapacity.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewBinding().etRemarks.getText()).toString().trim();
        String maintainedBy = (String) getViewBinding().srMaintainedBy.getTag();
        String usage = (String) getViewBinding().srUsage.getTag();
        String odour = (String) getViewBinding().srOdour.getTag();
        String pondStatus = (String) getViewBinding().srPondStatus.getTag();
        String pondType = (String) getViewBinding().srPondType.getTag();
        String presentCondition = (String) getViewBinding().srPresentCondition.getTag();
        String nature = (String) getViewBinding().srNature.getTag();
        String sideWall = (String) getViewBinding().srSideWall.getTag();
        String sideWallType = (String) getViewBinding().srSideWallType.getTag();
        String pondCondition = (String) getViewBinding().srPondCondition.getTag();
        String wardNo = (String) getViewBinding().srWardNo.getTag();
        presenter.validateData(name, place, obId, area, capacity, maintainedBy, usage, odour, pondStatus, pondType, presentCondition, nature, sideWall, sideWallType, pondCondition, wardNo, remarks, photo, latitude, longitude);
    }

    @Override
    public void showErrors(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_NAME:
                getViewBinding().layoutPondName.setError(error);
                getViewBinding().layoutPondName.requestFocus();
                break;
            case ERROR_TYPE_PLACE:
                getViewBinding().layoutPlace.setError(error);
                getViewBinding().layoutPlace.requestFocus();
                break;
            case ERROR_TYPE_OBID:
                getViewBinding().layoutObId.setError(error);
                getViewBinding().layoutObId.requestFocus();
                break;
            case ERROR_TYPE_AREA:
                getViewBinding().layoutArea.setError(error);
                getViewBinding().layoutArea.requestFocus();
                break;
            case ERROR_TYPE_CAPACITY:
                getViewBinding().layoutCapacity.setError(error);
                getViewBinding().layoutCapacity.requestFocus();
                break;
            case ERROR_TYPE_MAINTAINED_BY:
                getViewBinding().layoutMaintainedBy.setError(error);
                getViewBinding().layoutMaintainedBy.requestFocus();
                break;
            case ERROR_TYPE_USAGE:
                getViewBinding().layoutUsage.setError(error);
                getViewBinding().layoutUsage.requestFocus();
                break;
            case ERROR_TYPE_ODOUR:
                getViewBinding().layoutOdour.setError(error);
                getViewBinding().layoutOdour.requestFocus();
                break;
            case ERROR_TYPE_POND_STATUS:
                getViewBinding().layoutPondStatus.setError(error);
                getViewBinding().layoutPondStatus.requestFocus();
                break;
            case ERROR_TYPE_POND_TYPE:
                getViewBinding().layoutPondType.setError(error);
                getViewBinding().layoutPondType.requestFocus();
                break;
            case ERROR_TYPE_PRESENT_CONDITION:
                getViewBinding().layoutPresentCondition.setError(error);
                getViewBinding().layoutPresentCondition.requestFocus();
                break;
            case ERROR_TYPE_NATURE:
                getViewBinding().layoutNature.setError(error);
                getViewBinding().layoutNature.requestFocus();
                break;
            case ERROR_TYPE_SIDEWALL:
                getViewBinding().layoutSideWall.setError(error);
                getViewBinding().layoutSideWall.requestFocus();
                break;
            case ERROR_TYPE_SIDEWALL_TYPE:
                getViewBinding().layoutSideWallType.setError(error);
                getViewBinding().layoutSideWallType.requestFocus();
                break;
            case ERROR_TYPE_POND_CONDITION:
                getViewBinding().layoutPondCondition.setError(error);
                getViewBinding().layoutPondCondition.requestFocus();
                break;
            case ERROR_TYPE_WARD_NO:
                getViewBinding().layoutWardNo.setError(error);
                getViewBinding().layoutWardNo.requestFocus();
                break;
            case ERROR_TYPE_REMARKS:
                getViewBinding().layoutRemarks.setError(error);
                getViewBinding().layoutRemarks.requestFocus();
                break;
            case ERROR_TYPE_PHOTO:
            case ERROR_TYPE_LOCATION:
                showToast(error);
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutPondName.setErrorEnabled(false);
        getViewBinding().layoutPlace.setErrorEnabled(false);
        getViewBinding().layoutObId.setErrorEnabled(false);
        getViewBinding().layoutArea.setErrorEnabled(false);
        getViewBinding().layoutCapacity.setErrorEnabled(false);
        getViewBinding().layoutMaintainedBy.setErrorEnabled(false);
        getViewBinding().layoutUsage.setErrorEnabled(false);
        getViewBinding().layoutOdour.setErrorEnabled(false);
        getViewBinding().layoutPondStatus.setErrorEnabled(false);
        getViewBinding().layoutPondType.setErrorEnabled(false);
        getViewBinding().layoutPresentCondition.setErrorEnabled(false);
        getViewBinding().layoutNature.setErrorEnabled(false);
        getViewBinding().layoutSideWall.setErrorEnabled(false);
        getViewBinding().layoutSideWallType.setErrorEnabled(false);
        getViewBinding().layoutPondCondition.setErrorEnabled(false);
        getViewBinding().layoutWardNo.setErrorEnabled(false);
        getViewBinding().layoutRemarks.setErrorEnabled(false);
    }

    public void setEditData() {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
            WaterBodyAssets.Pond pondDetails = AppCacheData.getOurInstance().getBuildingAssetData().getPondDetails();
            if (pondDetails != null) {
                getViewBinding().etPondName.setText(pondDetails.getPondName());
                getViewBinding().etPlace.setText(pondDetails.getPlace());
                getViewBinding().etObId.setText(String.valueOf(pondDetails.getObid()));
                getViewBinding().etArea.setText(pondDetails.getArea());
                getViewBinding().etCapacity.setText(pondDetails.getCapacity());
                wardAdapter.setContent(String.valueOf(pondDetails.getWard()));
                maintainedByAdapter.setContent(String.valueOf(pondDetails.getMaintainedBy()));
                odourAdapter.setContent(pondDetails.getOdour());
                usageAdapter.setContent(pondDetails.getUsage());
                pondTypeAdapter.setContent(pondDetails.getPondType());
                pondStatusAdapter.setContent(pondDetails.getPondStatus());
                pondConditionAdapter.setContent(pondDetails.getPondcondition());
                presentConditionAdapter.setContent(pondDetails.getPresentCondition());
                sideWallTypeAdapter.setContent(pondDetails.getSidewallType());
                sideWallAdapter.setContent(String.valueOf(pondDetails.isSidewall()));
                natureAdapter.setContent(pondDetails.getNature());
                GeomPoint geom = pondDetails.getGeom();
                if (geom != null && geom.getCoordinates() != null && geom.getCoordinates().size() == 2) {
                    double longitude = geom.getCoordinates().get(0);
                    double latitude = geom.getCoordinates().get(1);
                    plotPoint(latitude, longitude);
                }
                onImageUploadSuccess(AppCacheData.getOurInstance().getImageBaseURL() +""+pondDetails.getPhoto1(), null, pondDetails.getPhoto1());
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
            presenter.uploadImage(path, AppConstants.IMAGE_PATH_POND, AppConstants.IMAGE_PATH_PHOTO1,
                    String.valueOf(AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getLocalBodyId()));
        }
    }

    @Override
    public void onImageUploadSuccess(String imagePath, String imageType, String response) {
        photo = response;
        Glide.with(this)
                .load(imagePath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_property_image_place_holder)
                .error(R.mipmap.ic_property_image_place_holder)
                .skipMemoryCache(false)
                .into(getViewBinding().imgPond);
    }


    @Override
    public void onImageRemoved(int reqTypeCode) {
        photo = null;
        getViewBinding().imgPond.setImageResource(R.mipmap.ic_property_image_place_holder);
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
            getBaseActivity().launchWaterBodyHome(false, false);
        }
    }
}
