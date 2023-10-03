package in.ults.ipms.ui.utility.stadium;

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
import in.ults.ipms.data.network.model.response.UtilityAssets;
import in.ults.ipms.data.network.model.response.UtilitySpinnerResponse;
import in.ults.ipms.databinding.FragmentStadiumDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BaseFragment;
import in.ults.ipms.ui.mapoverlays.MapOverlayBottomSheet;
import in.ults.ipms.utils.AppConstants;

public class StadiumDetailsFragment extends BaseFragment<FragmentStadiumDetailsBinding> implements IStadiumDetailsView, BaseActivity.ImagePickListener {

    public static final String TAG = StadiumDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_NAME = 1;
    public static final int ERROR_TYPE_AREA = 2;
    public static final int ERROR_TYPE_LOCATION = 3;
    public static final int ERROR_TYPE_ADDRESS = 4;
    public static final int ERROR_TYPE_SURVEY_NO = 5;
    public static final int ERROR_TYPE_NO_GALLERY = 6;
    public static final int ERROR_TYPE_ELECTRICITY = 7;
    public static final int ERROR_TYPE_GALLERY = 8;
    public static final int ERROR_TYPE_BATHROOM = 9;
    public static final int ERROR_TYPE_GALLERY_COVERAGE = 10;
    public static final int ERROR_TYPE_WARD_NO = 11;
    public static final int ERROR_TYPE_REMARKS = 12;
    public static final int ERROR_TYPE_PHOTO = 13;
    public static final int ERROR_TYPE_STADIUM_LOCATION = 14;

    public static StadiumDetailsFragment newInstance() {
        return new StadiumDetailsFragment();
    }

    @Inject
    IStadiumDetailsPresenter<IStadiumDetailsView, IStadiumDetailsInteractor> presenter;

    CommonSpinnerAdapter<UtilitySpinnerResponse.Ward> wardAdapter;

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
    protected FragmentStadiumDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentStadiumDetailsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.stadium_details_title);
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
        getViewBinding().imgStadium.setOnClickListener(v -> captureImage(REQUEST_CODE_IMAGE));
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
                if (AppCacheData.getOurInstance().getUtilitySpinnerData() != null &&
                        AppCacheData.getOurInstance().getUtilitySpinnerData().getLayerDetails() != null) {
                    layers.add(AppCacheData.getOurInstance().getUtilitySpinnerData().getLayerDetails().getStadium());
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
                    getViewBinding().nsvStadiumDetails.requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    getViewBinding().nsvStadiumDetails.requestDisallowInterceptTouchEvent(false);
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
        String stadiumName = Objects.requireNonNull(getViewBinding().etStadiumName.getText()).toString().trim();
        String area = Objects.requireNonNull(getViewBinding().etStadiumArea.getText()).toString().trim();
        String address = Objects.requireNonNull(getViewBinding().etAddress.getText()).toString().trim();
        String gallery = Objects.requireNonNull(getViewBinding().etGallery.getText()).toString().trim();
        String galleryCoverage = Objects.requireNonNull(getViewBinding().etGalleryCoverage.getText()).toString().trim();
        String noGallery = Objects.requireNonNull(getViewBinding().etNoGallery.getText()).toString().trim();
        String surveyNo = Objects.requireNonNull(getViewBinding().etSurveyNo.getText()).toString().trim();
        String bathroom = Objects.requireNonNull(getViewBinding().etBathRoom.getText()).toString().trim();
        String electricity = Objects.requireNonNull(getViewBinding().etElectricity.getText()).toString().trim();
        String location = Objects.requireNonNull(getViewBinding().etLocation.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewBinding().etRemarks.getText()).toString().trim();
        String wardNo = (String) getViewBinding().srWardNo.getTag();
        presenter.validateData(stadiumName, location, address, area, surveyNo, noGallery, electricity, bathroom, galleryCoverage, gallery, wardNo, remarks, photo, latitude, longitude);
    }

    @Override
    public void showErrors(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_NAME:
                getViewBinding().layoutStadiumName.setError(error);
                getViewBinding().layoutStadiumName.requestFocus();
                break;
            case ERROR_TYPE_AREA:
                getViewBinding().layoutStadiumArea.setError(error);
                getViewBinding().layoutStadiumArea.requestFocus();
                break;
            case ERROR_TYPE_LOCATION:
                getViewBinding().layoutStadiumAddress.setError(error);
                getViewBinding().layoutStadiumAddress.requestFocus();
                break;
            case ERROR_TYPE_ADDRESS:
                getViewBinding().layoutStadiumLocation.setError(error);
                getViewBinding().layoutStadiumLocation.requestFocus();
                break;
            case ERROR_TYPE_SURVEY_NO:
                getViewBinding().layoutStadiumGallery.setError(error);
                getViewBinding().layoutStadiumGallery.requestFocus();
                break;
            case ERROR_TYPE_NO_GALLERY:
                getViewBinding().layoutStadiumNoGallery.setError(error);
                getViewBinding().layoutStadiumNoGallery.requestFocus();
                break;
            case ERROR_TYPE_ELECTRICITY:
                getViewBinding().layoutStadiumGalleryCoverage.setError(error);
                getViewBinding().layoutStadiumGalleryCoverage.requestFocus();
                break;
            case ERROR_TYPE_GALLERY:
                getViewBinding().layoutStadiumBathRoom.setError(error);
                getViewBinding().layoutStadiumBathRoom.requestFocus();
                break;
            case ERROR_TYPE_BATHROOM:
                getViewBinding().layoutStadiumElectricity.setError(error);
                getViewBinding().layoutStadiumElectricity.requestFocus();
                break;
            case ERROR_TYPE_GALLERY_COVERAGE:
                getViewBinding().layoutStadiumSurveyNo.setError(error);
                getViewBinding().layoutStadiumSurveyNo.requestFocus();
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
            case ERROR_TYPE_STADIUM_LOCATION:
                showToast(error);
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {

        getViewBinding().layoutStadiumArea.setErrorEnabled(false);
        getViewBinding().layoutStadiumName.setErrorEnabled(false);
        getViewBinding().layoutStadiumAddress.setErrorEnabled(false);
        getViewBinding().layoutStadiumLocation.setErrorEnabled(false);
        getViewBinding().layoutStadiumGallery.setErrorEnabled(false);
        getViewBinding().layoutStadiumNoGallery.setErrorEnabled(false);
        getViewBinding().layoutStadiumGalleryCoverage.setErrorEnabled(false);
        getViewBinding().layoutStadiumBathRoom.setErrorEnabled(false);
        getViewBinding().layoutStadiumElectricity.setErrorEnabled(false);
        getViewBinding().layoutStadiumSurveyNo.setErrorEnabled(false);
        getViewBinding().layoutWardNo.setErrorEnabled(false);
        getViewBinding().layoutRemarks.setErrorEnabled(false);
    }

    public void setEditData() {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
            UtilityAssets.Stadium stadiumDetails = AppCacheData.getOurInstance().getBuildingAssetData().getStadiumDetails();
            if (stadiumDetails != null) {
                getViewBinding().etStadiumArea.setText(String.valueOf(stadiumDetails.getArea()));
                getViewBinding().etStadiumName.setText(stadiumDetails.getName());
                getViewBinding().etAddress.setText(stadiumDetails.getAddress());
                getViewBinding().etGallery.setText(stadiumDetails.getGallery());
                getViewBinding().etGalleryCoverage.setText(stadiumDetails.getGalleryCoverage());
                getViewBinding().etNoGallery.setText(stadiumDetails.getNoGallery());
                getViewBinding().etSurveyNo.setText(stadiumDetails.getSurveyNo());
                getViewBinding().etBathRoom.setText(stadiumDetails.getBathroom());
                getViewBinding().etElectricity.setText(stadiumDetails.getElectricity());
                getViewBinding().etLocation.setText(stadiumDetails.getLocation());
                getViewBinding().etRemarks.setText(stadiumDetails.getRemarks());
                wardAdapter.setContent(String.valueOf(stadiumDetails.getWard()));
                GeomPoint geom = stadiumDetails.getGeom();
                if (geom != null && geom.getCoordinates() != null && geom.getCoordinates().size() == 2) {
                    double longitude = geom.getCoordinates().get(0);
                    double latitude = geom.getCoordinates().get(1);
                    plotPoint(latitude, longitude);
                }
                onImageUploadSuccess(AppCacheData.getOurInstance().getImageBaseURL() + "" + stadiumDetails.getPhoto1(), null, stadiumDetails.getPhoto1());
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
            presenter.uploadImage(path, AppConstants.IMAGE_PATH_CANAL_LINE, AppConstants.IMAGE_PATH_PHOTO1,
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
                .into(getViewBinding().imgStadium);
    }


    @Override
    public void onImageRemoved(int reqTypeCode) {
        photo = null;
        getViewBinding().imgStadium.setImageResource(R.mipmap.ic_property_image_place_holder);
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
        if (getViewBinding().includeMiniMapProperty.miniMap != null) {
            getViewBinding().includeMiniMapProperty.miniMap.dispose();
        }
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
