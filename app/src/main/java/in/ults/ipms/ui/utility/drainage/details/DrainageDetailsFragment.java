package in.ults.ipms.ui.utility.drainage.details;

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
import in.ults.ipms.databinding.FragmentDrainageDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BaseFragment;
import in.ults.ipms.utils.AppConstants;

public class DrainageDetailsFragment extends BaseFragment<FragmentDrainageDetailsBinding> implements IDrainageDetailsView, BaseActivity.ImagePickListener {

    public static final String TAG = DrainageDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_NAME = 1;
    public static final int ERROR_TYPE_PLACE = 2;
    public static final int ERROR_TYPE_MATERIAL = 3;
    public static final int ERROR_TYPE_TYPE = 4;
    public static final int ERROR_TYPE_LENGTH = 5;
    public static final int ERROR_TYPE_DRAINAGE_LENGTH = 6;
    public static final int ERROR_TYPE_WARD_NUMBER = 7;
    public static final int ERROR_TYPE_REMARKS = 8;
    public static final int ERROR_TYPE_PHOTO = 9;
    private String photo;
    static final int REQUEST_CODE_IMAGE = 101;
    String roadLength;
    GeomPolyLine geom;

    @Inject
    IDrainageDetailsPresenter<IDrainageDetailsView, IDrainageDetailsInteractor> presenter;

    CommonSpinnerAdapter<UtilitySpinnerResponse.DrainageType> drainageTypeAdapter;
    CommonSpinnerAdapter<UtilitySpinnerResponse.DrainageMaterial> drainageMaterialAdapter;
    CommonSpinnerAdapter<UtilitySpinnerResponse.Ward> wardAdapter;

    public static DrainageDetailsFragment newInstance() {
        return new DrainageDetailsFragment();
    }

    @Override
    protected FragmentDrainageDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentDrainageDetailsBinding.inflate(inflater, container, attachToParent);
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
        return getString(R.string.drainage_details_title);
    }

    @Override
    protected void init(View view) {
        super.init(view);
        getViewBinding().btnSubmit.setOnClickListener(v -> submitAddRoadOnClick());
        getViewBinding().imgDrainage.setOnClickListener(v -> captureImage(REQUEST_CODE_IMAGE));

        if (AppCacheData.getOurInstance().getUtilitySpinnerData() != null && AppCacheData.getOurInstance().getUtilitySpinnerData().getDropDownValues() != null) {
            wardAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srWardNumber, AppCacheData.getOurInstance().getUtilitySpinnerData().getDropDownValues().getWard());
            drainageMaterialAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srDrainageMaterial, AppCacheData.getOurInstance().getUtilitySpinnerData().getDropDownValues().getDrainageMaterial());
            drainageTypeAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srDrainageType, AppCacheData.getOurInstance().getUtilitySpinnerData().getDropDownValues().getDrainageType());
        }

        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            setEditData();
        }
    }

    public void setGeom(GeomPolyLine geom) {
        this.geom = geom;
    }


    void submitAddRoadOnClick() {
        String name = Objects.requireNonNull(getViewBinding().etDrainageName.getText()).toString().trim();
        String place = Objects.requireNonNull(getViewBinding().etPlace.getText()).toString().trim();
        String material = (String) getViewBinding().srDrainageMaterial.getTag();
        String type = (String) getViewBinding().srDrainageType.getTag();
        String length = (String) getViewBinding().srDrainageType.getTag();
        String drainageLength = (String) getViewBinding().srDrainageType.getTag();
        String wardNumber = (String) getViewBinding().srWardNumber.getTag();
        String remarks = Objects.requireNonNull(getViewBinding().etRemarks.getText()).toString().trim();
        presenter.validateData(name, place, material, type,length,drainageLength, wardNumber, remarks, photo, geom);

    }

    @Override
    public void showRoadAddFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_NAME:
                getViewBinding().layoutDrainageName.setError(error);
                getViewBinding().layoutDrainageName.requestFocus();
                break;
            case ERROR_TYPE_PLACE:
                getViewBinding().layoutPlace.setError(error);
                getViewBinding().layoutPlace.requestFocus();
                break;
            case ERROR_TYPE_MATERIAL:
                getViewBinding().layoutDrainageMaterial.setError(error);
                getViewBinding().layoutDrainageMaterial.requestFocus();
                break;
            case ERROR_TYPE_TYPE:
                getViewBinding().layoutDrainageType.setError(error);
                getViewBinding().layoutDrainageType.requestFocus();
                break;
            case ERROR_TYPE_LENGTH:
                getViewBinding().layoutLength.setError(error);
                getViewBinding().layoutLength.requestFocus();
                break;
            case ERROR_TYPE_DRAINAGE_LENGTH:
                getViewBinding().layoutDrainageLength.setError(error);
                getViewBinding().layoutDrainageLength.requestFocus();
                break;
            case ERROR_TYPE_WARD_NUMBER:
                getViewBinding().layoutWardNumber.setError(error);
                getViewBinding().layoutWardNumber.requestFocus();
                break;
            case ERROR_TYPE_REMARKS:
                getViewBinding().layoutRemarks.setError(error);
                getViewBinding().layoutRemarks.requestFocus();
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
        getViewBinding().layoutDrainageName.setErrorEnabled(false);
        getViewBinding().layoutDrainageType.setErrorEnabled(false);
        getViewBinding().layoutDrainageMaterial.setErrorEnabled(false);
        getViewBinding().layoutDrainageLength.setErrorEnabled(false);
        getViewBinding().layoutLength.setErrorEnabled(false);
        getViewBinding().layoutPlace.setErrorEnabled(false);
        getViewBinding().layoutWardNumber.setErrorEnabled(false);
        getViewBinding().layoutRemarks.setErrorEnabled(false);

    }

    public void setEditData() {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getDrainageDetails() != null) {
            UtilityAssets.Drainage drainageDetails = AppCacheData.getOurInstance().getBuildingAssetData().getDrainageDetails();
            getViewBinding().etDrainageName.setText(drainageDetails.getDrainageName());
            getViewBinding().etLength.setText(drainageDetails.getLength());
            getViewBinding().etDrainageLength.setText(drainageDetails.getDrainageLength());
            getViewBinding().etPlace.setText(drainageDetails.getPlace());
            getViewBinding().etRemarks.setText(drainageDetails.getRemarks());
            drainageTypeAdapter.setContent(drainageDetails.getDrainageType());
            drainageMaterialAdapter.setContent(drainageDetails.getDrainageMaterial());
            wardAdapter.setContent(String.valueOf(drainageDetails.getWard()));
            onImageUploadSuccess(AppCacheData.getOurInstance().getImageBaseURL() +""+drainageDetails.getPhoto1(), null, drainageDetails.getPhoto1());
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
            presenter.uploadImage(path, AppConstants.IMAGE_PATH_DRAINAGE, AppConstants.IMAGE_PATH_PHOTO1,
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
                .into(getViewBinding().imgDrainage);
    }


    @Override
    public void onImageRemoved(int reqTypeCode) {
        photo = null;
        getViewBinding().imgDrainage.setImageResource(R.mipmap.ic_property_image_place_holder);
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
