package in.ults.ipms.ui.buildingassets.property;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.CommonAutoCompleteAdapter;
import in.ults.ipms.adapters.CommonSpinnerAdapter;
import in.ults.ipms.data.network.model.response.BuildingAssetSpinnerResponse;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.data.network.model.response.BuildingNameSpinnerResponse;
import in.ults.ipms.databinding.FragmentPropertyDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BaseFragment;
import in.ults.ipms.utils.AppConstants;

/**
 * Created by AmalB on 6/22/2021.
 */

public class PropertyDetailsFragment extends BaseFragment<FragmentPropertyDetailsBinding> implements IPropertyDetailsView, BaseActivity.ImagePickListener {

    public static final String TAG = PropertyDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_BUILDING_NAME = 1;
    public static final int ERROR_TYPE_BUILDING_CONDITION = 2;
    public static final int ERROR_TYPE_DOOR_STATUS = 3;
    public static final int ERROR_TYPE_WHICH_FLOOR = 4;
    public static final int ERROR_TYPE_BUILDING_STATUS = 5;
    public static final int ERROR_TYPE_BUILDING_USAGE = 6;
    public static final int ERROR_TYPE_NEW_PRO_ID = 7;
    public static final int ERROR_TYPE_OLD_PRO_ID = 8;
    public static final int ERROR_TYPE_NUMBER_YEARS = 9;
    public static final int ERROR_TYPE_YEAR_OF_CONSTRUCTION = 10;
    public static final int ERROR_TYPE_ANY_INSTITUTE = 16;
    public static final int ERROR_TYPE_CENTRALISED_AC = 17;
    public static final int ERROR_TYPE_NORMAL_AC = 18;
    public static final int ERROR_TYPE_ANY_STRUCTURAL_CHANGE = 19;
    public static final int ERROR_TYPE_HIGHER_FLOOR_GT_250SQMTS = 20;
    public static final int ERROR_TYPE_ANY_OTHER_LOWER_FLOOR_TYPE = 21;
    public static final int ERROR_TYPE_PHOTO1 = 22;
    public static final int ERROR_TYPE_PHOTO2 = 23;
    public static final int ERROR_TYPE_PLAN = 24;

    static final int REQUEST_CODE_IMAGE_ONE = 101;
    static final int REQUEST_CODE_IMAGE_TWO = 102;
    static final int REQUEST_CODE_IMAGE_PLAN = 103;

    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.BuildingConditions> buildingConditionAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.DoorStatus> doorStatusAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.FloorNumbers> floorNumbersAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.BuildingStatus> buildingStatusAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.BuildingUsages> buildingUsageAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.AnyInstitute> anyInstituteAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.CentralisedAc> centralisedAcAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.NormalAc> normalAcAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.AnyStructuralChange> anyStructuralChangeAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.HigherFloorGrt250Sqm> higherFloorGrt250SqmAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.AnyOtherLowerFloorType> anyOtherLowerFloorTypeAdapter;
    CommonAutoCompleteAdapter<BuildingNameSpinnerResponse.BuildingName> buildingNameAdapter;

    @Inject
    IPropertyDetailsPresenter<IPropertyDetailsView, IPropertyDetailsInteractor> presenter;

    private String photo1;
    private String photo2;
    private String plan;

    public static PropertyDetailsFragment newInstance() {
        return new PropertyDetailsFragment();
    }

    @Override
    protected FragmentPropertyDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentPropertyDetailsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.property_details_title);
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
        getViewBinding().btnPropertyDetails.setOnClickListener(v -> submitOnClick());
        getViewBinding().imgPhoto1.setOnClickListener(v -> captureImage(REQUEST_CODE_IMAGE_ONE));
        getViewBinding().imgPhoto2.setOnClickListener(v -> captureImage(REQUEST_CODE_IMAGE_TWO));
        getViewBinding().imgPlan.setOnClickListener(v -> captureImage(REQUEST_CODE_IMAGE_PLAN));
        buildingConditionAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyBuildingCondition, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getBuildingConditions());
        doorStatusAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyDoorStatus, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getDoorStatus());
        floorNumbersAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyWhichFloor, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getFloorNumbers());
        buildingStatusAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyBuildingStatus, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getBuildingStatus());
        buildingUsageAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyBuildingUsage, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getBuildingUsages());
        anyInstituteAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyAnyInstitute, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getAnyInstitute());
        centralisedAcAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyCentralisedAC, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getCentralisedAc());
        normalAcAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyNormalAC, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getNormalAc());
        anyStructuralChangeAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyAnyStructuralChange, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getAnyStructuralChange());
        higherFloorGrt250SqmAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyHigherFloor, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getHigherFloorGrt250Sqm());
        anyOtherLowerFloorTypeAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyAnyLowerFloorType, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getAnyOtherLowerFloorType());
       presenter.getBuildingNameSpinnerData();
    }


    void submitOnClick() {

        String buildingName = (String) getViewBinding().srPropertyBuildingName.getTag();
        String buildingCondition = (String) getViewBinding().srPropertyBuildingCondition.getTag();
        String doorStatus = (String) getViewBinding().srPropertyDoorStatus.getTag();
        String whichFloor = (String) getViewBinding().srPropertyWhichFloor.getTag();
        String buildingStatus = (String) getViewBinding().srPropertyBuildingStatus.getTag();
        String buildingUsage = (String) getViewBinding().srPropertyBuildingUsage.getTag();
        String newPropertyId = Objects.requireNonNull(getViewBinding().etPropertyNewPropertyId.getText()).toString().trim();
        String oldPropertyId = Objects.requireNonNull(getViewBinding().etPropertyOldPropertyId.getText()).toString().trim();
        String numberOfYears = Objects.requireNonNull(getViewBinding().etPropertyNumberOfYears.getText()).toString().trim();
        String yearOfConstruction = Objects.requireNonNull(getViewBinding().etPropertyYearOfConstruction.getText()).toString().trim();
        String anyInstitute = (String) getViewBinding().srPropertyAnyInstitute.getTag();
        String centralisedAc = (String) getViewBinding().srPropertyCentralisedAC.getTag();
        String normalAc = (String) getViewBinding().srPropertyNormalAC.getTag();
        String anyStructuralChange = (String) getViewBinding().srPropertyAnyStructuralChange.getTag();
        String higherFloorGt250sqmts = (String) getViewBinding().srPropertyHigherFloor.getTag();
        String anyOtherLowerFloorType = (String) getViewBinding().srPropertyAnyLowerFloorType.getTag();

        presenter.validateData(buildingName, buildingCondition, doorStatus, whichFloor, buildingStatus, buildingUsage, newPropertyId, oldPropertyId, yearOfConstruction, numberOfYears, anyInstitute, centralisedAc, normalAc, anyStructuralChange, higherFloorGt250sqmts, anyOtherLowerFloorType, photo1, photo2, plan);

    }


    @Override
    public void onBuildingNameLoaded(ArrayList<BuildingNameSpinnerResponse.BuildingName> buildingNames) {
        buildingNameAdapter = CommonAutoCompleteAdapter.setAdapter(getBaseActivity(), getViewBinding().srPropertyBuildingName, buildingNames);
//        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            setEditData();
//        }
    }

    public void showPropertyDetailsFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_BUILDING_NAME:
                getViewBinding().layoutPropertyBuildingName.setError(error);
                getViewBinding().layoutPropertyBuildingName.requestFocus();
                break;
            case ERROR_TYPE_BUILDING_CONDITION:
                getViewBinding().layoutPropertyBuildingCondition.setError(error);
                getViewBinding().layoutPropertyBuildingCondition.requestFocus();
                break;
            case ERROR_TYPE_DOOR_STATUS:
                getViewBinding().layoutPropertyDoorStatus.setError(error);
                getViewBinding().layoutPropertyDoorStatus.requestFocus();
                break;
            case ERROR_TYPE_WHICH_FLOOR:
                getViewBinding().layoutPropertyWhichFloor.setError(error);
                getViewBinding().layoutPropertyWhichFloor.requestFocus();
                break;
            case ERROR_TYPE_BUILDING_STATUS:
                getViewBinding().layoutPropertyBuildingStatus.setError(error);
                getViewBinding().layoutPropertyBuildingStatus.requestFocus();
                break;
            case ERROR_TYPE_BUILDING_USAGE:
                getViewBinding().layoutPropertyBuildingUsage.setError(error);
                getViewBinding().layoutPropertyBuildingUsage.requestFocus();
                break;
            case ERROR_TYPE_NEW_PRO_ID:
                getViewBinding().layoutPropertyNewPropertyId.setError(error);
                getViewBinding().layoutPropertyNewPropertyId.requestFocus();
                break;
            case ERROR_TYPE_OLD_PRO_ID:
                getViewBinding().layoutPropertyOldPropertyId.setError(error);
                getViewBinding().layoutPropertyOldPropertyId.requestFocus();
                break;
            case ERROR_TYPE_YEAR_OF_CONSTRUCTION:
                getViewBinding().layoutPropertyYearOfConstruction.setError(error);
                getViewBinding().layoutPropertyYearOfConstruction.requestFocus();
                break;
            case ERROR_TYPE_NUMBER_YEARS:
                getViewBinding().layoutPropertyNumberOfYears.setError(error);
                getViewBinding().layoutPropertyNumberOfYears.requestFocus();
                break;
            case ERROR_TYPE_ANY_INSTITUTE:
                getViewBinding().layoutPropertyAnyInstitute.setError(error);
                getViewBinding().layoutPropertyAnyInstitute.requestFocus();
                break;
            case ERROR_TYPE_CENTRALISED_AC:
                getViewBinding().layoutPropertyCentralisedAC.setError(error);
                getViewBinding().layoutPropertyCentralisedAC.requestFocus();
                break;
            case ERROR_TYPE_NORMAL_AC:
                getViewBinding().layoutPropertyNormalAC.setError(error);
                getViewBinding().layoutPropertyNormalAC.requestFocus();
                break;
            case ERROR_TYPE_ANY_STRUCTURAL_CHANGE:
                getViewBinding().layoutPropertyAnyStructuralChange.setError(error);
                getViewBinding().layoutPropertyAnyStructuralChange.requestFocus();
                break;
            case ERROR_TYPE_HIGHER_FLOOR_GT_250SQMTS:
                getViewBinding().layoutPropertyHigherFloor.setError(error);
                getViewBinding().layoutPropertyHigherFloor.requestFocus();
                break;
            case ERROR_TYPE_ANY_OTHER_LOWER_FLOOR_TYPE:
                getViewBinding().layoutPropertyAnyLowerFloorType.setError(error);
                getViewBinding().layoutPropertyAnyLowerFloorType.requestFocus();
                break;
            case ERROR_TYPE_PHOTO1:
            case ERROR_TYPE_PHOTO2:
            case ERROR_TYPE_PLAN:
                showToast(error);
                break;
            default:
                break;
        }

    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutPropertyBuildingName.setErrorEnabled(false);
        getViewBinding().layoutPropertyBuildingCondition.setErrorEnabled(false);
        getViewBinding().layoutPropertyDoorStatus.setErrorEnabled(false);
        getViewBinding().layoutPropertyWhichFloor.setErrorEnabled(false);
        getViewBinding().layoutPropertyBuildingStatus.setErrorEnabled(false);
        getViewBinding().layoutPropertyBuildingUsage.setErrorEnabled(false);
        getViewBinding().layoutPropertyNewPropertyId.setErrorEnabled(false);
        getViewBinding().layoutPropertyOldPropertyId.setErrorEnabled(false);
        getViewBinding().layoutPropertyYearOfConstruction.setErrorEnabled(false);
        getViewBinding().layoutPropertyNumberOfYears.setErrorEnabled(false);
        getViewBinding().layoutPropertyAnyInstitute.setErrorEnabled(false);
        getViewBinding().layoutPropertyCentralisedAC.setErrorEnabled(false);
        getViewBinding().layoutPropertyNormalAC.setErrorEnabled(false);
        getViewBinding().layoutPropertyAnyStructuralChange.setErrorEnabled(false);
        getViewBinding().layoutPropertyHigherFloor.setErrorEnabled(false);
        getViewBinding().layoutPropertyAnyLowerFloorType.setErrorEnabled(false);
    }

    public void setEditData() {
        if(AppCacheData.getOurInstance().getBuildingAssetData()!=null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails()!=null) {
            BuildingAssets.PropertyDetails propertyDetails = AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails();
            buildingNameAdapter.setContent(String.valueOf(propertyDetails.getBuilding()));
            buildingConditionAdapter.setContent(String.valueOf(propertyDetails.getBldgCondition()));
            doorStatusAdapter.setContent(String.valueOf(propertyDetails.getDoorStatus()));
            floorNumbersAdapter.setContent(propertyDetails.getWhichFloor());
            buildingStatusAdapter.setContent(String.valueOf(propertyDetails.getBldgStatus()));
            buildingUsageAdapter.setContent(String.valueOf(propertyDetails.getBldgUsage()));
            getViewBinding().etPropertyNewPropertyId.setText(propertyDetails.getNewProId());
            getViewBinding().etPropertyOldPropertyId.setText(propertyDetails.getOldProId());
            getViewBinding().etPropertyYearOfConstruction.setText(String.valueOf(propertyDetails.getYearConst()));
            getViewBinding().etPropertyNumberOfYears.setText(String.valueOf(propertyDetails.getNoOfYears()));
            anyInstituteAdapter.setContent(propertyDetails.getAnyInstitute());
            centralisedAcAdapter.setContent(propertyDetails.getCentralAc());
            normalAcAdapter.setContent(propertyDetails.getNormalAc());
            anyStructuralChangeAdapter.setContent(propertyDetails.getStructuralChange());
            higherFloorGrt250SqmAdapter.setContent(propertyDetails.getHigherFloorGt());
            anyOtherLowerFloorTypeAdapter.setContent(propertyDetails.getOtherFlrType());
            Log.v(TAG, "RESPONSE :: " +new Gson().toJson(propertyDetails));
            Log.v(TAG, "PHOTO 2:: " +propertyDetails.getPhoto2());
            Log.v(TAG, "PHOTO 2:: " +propertyDetails.getPhoto2());
            onImageUploadSuccess(AppCacheData.getOurInstance().getImageBaseURL() +""+propertyDetails.getPhoto1(), AppConstants.IMAGE_PATH_PHOTO1, propertyDetails.getPhoto1());
            onImageUploadSuccess(AppCacheData.getOurInstance().getImageBaseURL() +""+propertyDetails.getPhoto2(), AppConstants.IMAGE_PATH_PHOTO2, propertyDetails.getPhoto2());
            onImageUploadSuccess(AppCacheData.getOurInstance().getImageBaseURL() +""+propertyDetails.getPlan(), AppConstants.IMAGE_PATH_PLAN, propertyDetails.getPlan());
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
            presenter.uploadImage(path, AppConstants.IMAGE_PATH_BUILDING_ASSET, reqTypeCode == REQUEST_CODE_IMAGE_ONE ?
                            AppConstants.IMAGE_PATH_PHOTO1 : reqTypeCode == REQUEST_CODE_IMAGE_TWO ? AppConstants.IMAGE_PATH_PHOTO2 : AppConstants.IMAGE_PATH_PLAN,
                    String.valueOf(AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getLocalBodyId()));
        }
    }

    @Override
    public void onImageUploadSuccess(String imagePath, String imageType, String response) {
        switch (imageType) {
            case AppConstants.IMAGE_PATH_PHOTO1:
                photo1 = response;
                break;
            case AppConstants.IMAGE_PATH_PHOTO2:
                photo2 = response;
                break;
            case AppConstants.IMAGE_PATH_PLAN:
                plan = response;
                break;
        }
        Glide.with(this)
                .load(imagePath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_property_image_place_holder)
                .error(R.mipmap.ic_property_image_place_holder)
                .skipMemoryCache(false)
                .into(imageType.equals(AppConstants.IMAGE_PATH_PHOTO1) ? getViewBinding().imgPhoto1 : imageType.equals(AppConstants.IMAGE_PATH_PHOTO2) ? getViewBinding().imgPhoto2 : getViewBinding().imgPlan);
    }


    @Override
    public void onImageRemoved(int reqTypeCode) {
        switch (reqTypeCode) {
            case REQUEST_CODE_IMAGE_ONE:
                photo1 = null;
                getViewBinding().imgPhoto1.setImageResource(R.mipmap.ic_property_image_place_holder);
                break;
            case REQUEST_CODE_IMAGE_TWO:
                photo2 = null;
                getViewBinding().imgPhoto2.setImageResource(R.mipmap.ic_property_image_place_holder);
                break;
            case REQUEST_CODE_IMAGE_PLAN:
                plan = null;
                getViewBinding().imgPlan.setImageResource(R.mipmap.ic_property_image_place_holder);
                break;
        }
    }

    @Override
    public void goToNext() {
        getBaseActivity().launchLocationDetails(true, false);
    }

    @Override
    public void onAddOrUpdateSuccess() {
        getBaseActivity().launchBuildAssetHome(false,false);
    }
}
