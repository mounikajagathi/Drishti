package in.ults.ipms.ui.utility.road.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.CommonSpinnerAdapter;
import in.ults.ipms.data.network.model.response.GeomPolyLine;
import in.ults.ipms.data.network.model.response.UtilityAssets;
import in.ults.ipms.data.network.model.response.UtilitySpinnerResponse;
import in.ults.ipms.databinding.FragmentAddRoadBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BaseFragment;
import in.ults.ipms.utils.AppConstants;

public class RoadDetailsFragment extends BaseFragment<FragmentAddRoadBinding> implements IRoadDetailsView, BaseActivity.ImagePickListener {

    public static final String TAG = RoadDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_ROAD_NAME = 1;
    public static final int ERROR_TYPE_START_POINT = 2;
    public static final int ERROR_TYPE_END_POINT = 3;
    public static final int ERROR_TYPE_ROAD_MATERIAL = 4;
    public static final int ERROR_TYPE_ROAD_CATEGORY = 5;
    public static final int ERROR_TYPE_MAINTAINED_BY = 6;
    public static final int ERROR_TYPE_LENGTH_MTR = 7;
    public static final int ERROR_TYPE_CARRIAGE_WIDTH_MTR = 8;
    public static final int ERROR_TYPE_ROADWAY_WIDTH_MTR = 9;
    public static final int ERROR_TYPE_FOOTPATH_WIDTH_MTR = 10;
    public static final int ERROR_TYPE_RIGHTWAY_WIDTH_MTR = 11;
    public static final int ERROR_TYPE_FOOT_PATH = 12;
    public static final int ERROR_TYPE_FOOT_PATH_PLACEMENT = 13;
    public static final int ERROR_TYPE_FOOT_PATH_CONS_MAT = 14;
    public static final int ERROR_TYPE_ENVIRONMENT = 15;
    public static final int ERROR_TYPE_BOARD_RESOLUTION = 16;
    public static final int ERROR_TYPE_ASSET_ID = 17;
    public static final int ERROR_TYPE_WARD_NUMBER = 18;
    public static final int ERROR_TYPE_REMARKS = 19;
    public static final int ERROR_TYPE_PHOTO = 20;
    private String photo;
    static final int REQUEST_CODE_IMAGE = 101;
    String roadLength;
    GeomPolyLine geom;

    @Inject
    IRoadDetailsPresenter<IRoadDetailsView, IRoadDetailsInteractor> presenter;

    CommonSpinnerAdapter<UtilitySpinnerResponse.RoadTypes> roadMaterialAdapter;
    CommonSpinnerAdapter<UtilitySpinnerResponse.RoadCategories> roadCategoryAdapter;
    CommonSpinnerAdapter<UtilitySpinnerResponse.MaintainedBy> maintainedByAdapter;
    CommonSpinnerAdapter<UtilitySpinnerResponse.Ward> wardAdapter;


    public static RoadDetailsFragment newInstance() {
        return new RoadDetailsFragment();
    }

    @Override
    protected FragmentAddRoadBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentAddRoadBinding.inflate(inflater, container, attachToParent);
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
        return getString(R.string.road_utility_details);
    }

    @Override
    protected void init(View view) {
        super.init(view);
        getViewBinding().btnRoadDetails.setOnClickListener(v -> submitAddRoadOnClick());
        getViewBinding().imgRoad.setOnClickListener(v -> captureImage(REQUEST_CODE_IMAGE));
        if (AppCacheData.getOurInstance().getUtilitySpinnerData() != null &&
                AppCacheData.getOurInstance().getUtilitySpinnerData().getDropDownValues() != null) {
            roadMaterialAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srRURoadMaterial, AppCacheData.getOurInstance().getUtilitySpinnerData().getDropDownValues().getRoadTypes());
            roadCategoryAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srRURoadCategory, AppCacheData.getOurInstance().getUtilitySpinnerData().getDropDownValues().getRoadCategories());
            maintainedByAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srRUMaintainedBy, AppCacheData.getOurInstance().getUtilitySpinnerData().getDropDownValues().getMaintainedBy());
            wardAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srWardNo, AppCacheData.getOurInstance().getUtilitySpinnerData().getDropDownValues().getWard());
        }

        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            setEditData();
        } else {
            getViewBinding().etRULength.setText(roadLength);
        }
    }

    public void setRoadLength(String roadLength) {
        this.roadLength = roadLength;
    }

    public void setGeom(GeomPolyLine geom) {
        this.geom = geom;
    }

    public void setEditData() {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getRoadUtility() != null) {
            UtilityAssets.RoadUtility roadUtility = AppCacheData.getOurInstance().getBuildingAssetData().getRoadUtility();
            getViewBinding().etRURoadName.setText(roadUtility.getRoadName());
            getViewBinding().etRUStartPoint.setText(roadUtility.getStartPoint());
            getViewBinding().etRUEndPoint.setText(roadUtility.getEndPoint());
            roadMaterialAdapter.setContent(roadUtility.getRoadMaterial());
            roadCategoryAdapter.setContent(roadUtility.getRoadCategory());
            maintainedByAdapter.setContent(roadUtility.getMaintainedBy());
            maintainedByAdapter.setContent(roadUtility.getMaintainedBy());
            wardAdapter.setContent(String.valueOf(roadUtility.getWard()));
            getViewBinding().etRULength.setText(roadUtility.getLength());
            getViewBinding().etRUCarriageWidth.setText(roadUtility.getCarriageWidth());
            getViewBinding().etRURoadwayWidth.setText(roadUtility.getRoadwayWidth());
            getViewBinding().etRUFootpathWidth.setText(roadUtility.getFootpathWidth());
            getViewBinding().etRURightWayWidth.setText(roadUtility.getRightOfWayWidth());
            getViewBinding().etFootPath.setText(roadUtility.getFootpath());
            getViewBinding().etFootpathPlacement.setText(roadUtility.getFootpathPlacement());
            getViewBinding().etFootpathConsMat.setText(roadUtility.getFootpathConsMat());
            getViewBinding().etEnvironment.setText(roadUtility.getEnvironment());
            getViewBinding().etBoardResolution.setText(roadUtility.getBoardResolution());
            getViewBinding().etAssetID.setText(roadUtility.getAssetID());
            getViewBinding().etRURemarks.setText(roadUtility.getRemarks());
            String photo = roadUtility.getPhoto();
            onImageUploadSuccess(AppCacheData.getOurInstance().getImageBaseURL() +""+ photo, null, photo);
        }
    }

    void submitAddRoadOnClick() {
        String roadName = Objects.requireNonNull(getViewBinding().etRURoadName.getText()).toString().trim();
        String startPoint = Objects.requireNonNull(getViewBinding().etRUStartPoint.getText()).toString().trim();
        String endPoint = Objects.requireNonNull(getViewBinding().etRUEndPoint.getText()).toString().trim();
        String roadMaterial = (String) getViewBinding().srRURoadMaterial.getTag();
        String roadCategory = (String) getViewBinding().srRURoadCategory.getTag();
        String maintainedBy = (String) getViewBinding().srRUMaintainedBy.getTag();
        String length = Objects.requireNonNull(getViewBinding().etRULength.getText()).toString().trim();
        String carriageWidth = Objects.requireNonNull(getViewBinding().etRUCarriageWidth.getText()).toString().trim();//
        String roadwayWidth = Objects.requireNonNull(getViewBinding().etRURoadwayWidth.getText()).toString().trim();
        String footpathWidth = Objects.requireNonNull(getViewBinding().etRUFootpathWidth.getText()).toString().trim();
        String rightWayWidth = Objects.requireNonNull(getViewBinding().etRURightWayWidth.getText()).toString().trim();
        String footpath = Objects.requireNonNull(getViewBinding().etRURightWayWidth.getText()).toString().trim();
        String footpathPlacement = Objects.requireNonNull(getViewBinding().etRURightWayWidth.getText()).toString().trim();
        String footpathConsMat = Objects.requireNonNull(getViewBinding().etRURightWayWidth.getText()).toString().trim();
        String environment = Objects.requireNonNull(getViewBinding().etRURightWayWidth.getText()).toString().trim();
        String boardResolution = Objects.requireNonNull(getViewBinding().etRURightWayWidth.getText()).toString().trim();
        String assetID = Objects.requireNonNull(getViewBinding().etRURightWayWidth.getText()).toString().trim();
        String wardNo = (String) getViewBinding().srWardNo.getTag();
        //photo
        String remarks = Objects.requireNonNull(getViewBinding().etRURemarks.getText()).toString().trim();
        presenter.saveRoadDetails(roadName, startPoint, endPoint, roadMaterial, roadCategory, maintainedBy, length, carriageWidth, roadwayWidth, footpathWidth, rightWayWidth,footpath,footpathPlacement,footpathConsMat,environment,boardResolution,assetID,wardNo, remarks, photo, geom);

    }

    @Override
    public void showRoadAddFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_ROAD_NAME:
                getViewBinding().layoutRURoadName.setError(error);
                getViewBinding().layoutRURoadName.requestFocus();
                break;
            case ERROR_TYPE_START_POINT:
                getViewBinding().layoutRUStartPoint.setError(error);
                getViewBinding().layoutRUStartPoint.requestFocus();
                break;
            case ERROR_TYPE_END_POINT:
                getViewBinding().layoutRUEndPoint.setError(error);
                getViewBinding().layoutRUEndPoint.requestFocus();
                break;//
            case ERROR_TYPE_ROAD_MATERIAL:
                getViewBinding().layoutRURoadMaterial.setError(error);
                getViewBinding().layoutRURoadMaterial.requestFocus();
                break;
            case ERROR_TYPE_ROAD_CATEGORY:
                getViewBinding().layoutRURoadCategory.setError(error);
                getViewBinding().layoutRURoadCategory.requestFocus();
                break;
            case ERROR_TYPE_MAINTAINED_BY:
                getViewBinding().layoutRUMaintainedBy.setError(error);
                getViewBinding().layoutRUMaintainedBy.requestFocus();
                break;
            case ERROR_TYPE_LENGTH_MTR:
                getViewBinding().layoutRULength.setError(error);
                getViewBinding().layoutRULength.requestFocus();
                break;
            case ERROR_TYPE_CARRIAGE_WIDTH_MTR:
                getViewBinding().layoutRUCarriageWidth.setError(error);
                getViewBinding().layoutRUCarriageWidth.requestFocus();
                break;
            case ERROR_TYPE_ROADWAY_WIDTH_MTR:
                getViewBinding().layoutRURoadwayWidth.setError(error);
                getViewBinding().layoutRURoadwayWidth.requestFocus();
                break;
            case ERROR_TYPE_FOOTPATH_WIDTH_MTR:
                getViewBinding().layoutRUFootpathWidth.setError(error);
                getViewBinding().layoutRUFootpathWidth.requestFocus();
                break;
            case ERROR_TYPE_RIGHTWAY_WIDTH_MTR:
                getViewBinding().layoutRURightWayWidth.setError(error);
                getViewBinding().layoutRURightWayWidth.requestFocus();
                break;
            case ERROR_TYPE_FOOT_PATH:
                getViewBinding().layoutFootPath.setError(error);
                getViewBinding().layoutFootPath.requestFocus();
                break;
            case ERROR_TYPE_FOOT_PATH_PLACEMENT:
                getViewBinding().layoutFootpathPlacement.setError(error);
                getViewBinding().layoutFootpathPlacement.requestFocus();
                break;
            case ERROR_TYPE_FOOT_PATH_CONS_MAT:
                getViewBinding().layoutFootpathConsMat.setError(error);
                getViewBinding().layoutFootpathConsMat.requestFocus();
                break;
            case ERROR_TYPE_ENVIRONMENT:
                getViewBinding().layoutEnvironment.setError(error);
                getViewBinding().layoutEnvironment.requestFocus();
                break;
            case ERROR_TYPE_BOARD_RESOLUTION:
                getViewBinding().layoutBoardResolution.setError(error);
                getViewBinding().layoutBoardResolution.requestFocus();
                break;
            case ERROR_TYPE_ASSET_ID:
                getViewBinding().layoutAssetID.setError(error);
                getViewBinding().layoutAssetID.requestFocus();
                break;
            case ERROR_TYPE_WARD_NUMBER:
                getViewBinding().layoutWardNo.setError(error);
                getViewBinding().layoutWardNo.requestFocus();
                break;
            case ERROR_TYPE_REMARKS:
                getViewBinding().layoutRURemarks.setError(error);
                getViewBinding().layoutRURemarks.requestFocus();
                break;
            case ERROR_TYPE_PHOTO:
                showToast(error);
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutRURoadName.setErrorEnabled(false);
        getViewBinding().layoutRUStartPoint.setErrorEnabled(false);
        getViewBinding().layoutRUEndPoint.setErrorEnabled(false);
        getViewBinding().layoutRURoadMaterial.setErrorEnabled(false);
        getViewBinding().layoutRURoadCategory.setErrorEnabled(false);
        getViewBinding().layoutRUMaintainedBy.setErrorEnabled(false);
        getViewBinding().layoutRULength.setErrorEnabled(false);
        getViewBinding().layoutRUCarriageWidth.setErrorEnabled(false);
        getViewBinding().layoutRURoadwayWidth.setErrorEnabled(false);
        getViewBinding().layoutRUFootpathWidth.setErrorEnabled(false);
        getViewBinding().layoutRURightWayWidth.setErrorEnabled(false);
        getViewBinding().layoutFootPath.setErrorEnabled(false);
        getViewBinding().layoutFootpathPlacement.setErrorEnabled(false);
        getViewBinding().layoutFootpathConsMat.setErrorEnabled(false);
        getViewBinding().layoutEnvironment.setErrorEnabled(false);
        getViewBinding().layoutBoardResolution.setErrorEnabled(false);
        getViewBinding().layoutAssetID.setErrorEnabled(false);
        getViewBinding().layoutWardNo.setErrorEnabled(false);
        getViewBinding().layoutRURemarks.setErrorEnabled(false);

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
            presenter.uploadImage(path, AppConstants.IMAGE_PATH_ROAD, AppConstants.IMAGE_PATH_PHOTO1,
                    String.valueOf(AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getLocalBodyId()));//
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
                .into(getViewBinding().imgRoad);
    }


    @Override
    public void onImageRemoved(int reqTypeCode) {
        photo = null;
        getViewBinding().imgRoad.setImageResource(R.mipmap.ic_property_image_place_holder);
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
