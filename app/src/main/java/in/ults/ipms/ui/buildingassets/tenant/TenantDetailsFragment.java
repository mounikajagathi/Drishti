package in.ults.ipms.ui.buildingassets.tenant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.CommonSpinnerAdapter;
import in.ults.ipms.data.network.model.response.BuildingAssetSpinnerResponse;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.databinding.FragmentTenantDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseFragment;

public class TenantDetailsFragment extends BaseFragment<FragmentTenantDetailsBinding> implements ITenantDetailsView {

    public static final String TAG = TenantDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_FIRST_NAME = 1;
    public static final int ERROR_TYPE_LAST_NAME = 2;
    public static final int ERROR_TYPE_EMAIL = 3;
    public static final int ERROR_TYPE_MOBILE = 4;
    public static final int ERROR_TYPE_LAND_PHONE = 5;
    public static final int ERROR_TYPE_GENDER = 6;
    public static final int ERROR_TYPE_PLACE_NAME = 7;
    public static final int ERROR_TYPE_VILLAGE = 8;
    public static final int ERROR_TYPE_STREET = 9;
    public static final int ERROR_TYPE_TENANT_HOUSE = 10;
    public static final int ERROR_TYPE_TENANT_NATIVE = 11;
    public static final int ERROR_TYPE_TENANT_POSTOFFICE = 12;
    public static final int ERROR_TYPE_TENANT_SURVEY_NUMBER = 13;
    public static final int ERROR_TYPE_RENT_AMOUNT = 14;

    @Inject
    ITenantDetailsPresenter<ITenantDetailsView, ITenantDetailsInteractor> presenter;

    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Genders> genderAdapter;

    public static TenantDetailsFragment newInstance() {
        return new TenantDetailsFragment();
    }

    @Override
    protected  FragmentTenantDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentTenantDetailsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.tenant_details_title);
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
    protected void init(View view){
        getViewBinding().btnTenantDetails.setOnClickListener(v -> submitOnClick());
        genderAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srTenantGender, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getGenders());

//        if(AppCacheData.getOurInstance().isAssetUpdate()){
            setEditData();
//        }
    }

    void submitOnClick() {
        String firstName = Objects.requireNonNull(getViewBinding().etTenantFirstName.getText()).toString().trim();
        String lastName = Objects.requireNonNull(getViewBinding().etTenantLastName.getText()).toString().trim();
        String email = Objects.requireNonNull(getViewBinding().etTenantEmail.getText()).toString().trim();
        String mobile = Objects.requireNonNull(getViewBinding().etTenantMobile.getText()).toString().trim();
        String landphone = Objects.requireNonNull(getViewBinding().etTenantLandline.getText()).toString().trim();
        String gender = (String) getViewBinding().srTenantGender.getTag();
        String placeName = Objects.requireNonNull(getViewBinding().etTenantPlaceName.getText()).toString().trim();
        String village = Objects.requireNonNull(getViewBinding().etTenantVillageName.getText()).toString().trim();
        String street = Objects.requireNonNull(getViewBinding().etTenantStreetName.getText()).toString().trim();
        String tenantHouse = Objects.requireNonNull(getViewBinding().etTenantHouseName.getText()).toString().trim();
        String tenantNative = Objects.requireNonNull(getViewBinding().etTenantNative.getText()).toString().trim();
        String tenantPostoffice = Objects.requireNonNull(getViewBinding().etTenantPostOffice.getText()).toString().trim();
        String tenantSurveyNumber = Objects.requireNonNull(getViewBinding().etTenantSurveyNumber.getText()).toString().trim();
        String rentAmount = Objects.requireNonNull(getViewBinding().etTenantRentAmount.getText()).toString().trim();

        presenter.validateData(firstName,lastName,email,mobile,landphone,gender,placeName,village,street,tenantHouse,tenantNative,tenantPostoffice,tenantSurveyNumber,rentAmount);
    }

    @Override
    public void showTenantDetailsFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_FIRST_NAME:
                getViewBinding().layoutTenantFirstName.setError(error);
                getViewBinding().layoutTenantFirstName.requestFocus();
                break;
            case ERROR_TYPE_LAST_NAME:
                getViewBinding().layoutTenantLastName.setError(error);
                getViewBinding().layoutTenantLastName.requestFocus();
                break;
            case ERROR_TYPE_EMAIL:
                getViewBinding().layoutTenantEmail.setError(error);
                getViewBinding().layoutTenantEmail.requestFocus();
                break;
            case ERROR_TYPE_MOBILE:
                getViewBinding().layoutTenantMobile.setError(error);
                getViewBinding().layoutTenantMobile.requestFocus();
                break;
            case ERROR_TYPE_LAND_PHONE:
                getViewBinding().layoutTenantLandline.setError(error);
                getViewBinding().layoutTenantLandline.requestFocus();
                break;
            case ERROR_TYPE_GENDER:
                getViewBinding().layoutTenantGender.setError(error);
                getViewBinding().layoutTenantGender.requestFocus();
                break;
            case ERROR_TYPE_PLACE_NAME:
                getViewBinding().layoutTenantPlaceName.setError(error);
                getViewBinding().layoutTenantPlaceName.requestFocus();
                break;
            case ERROR_TYPE_VILLAGE:
                getViewBinding().layoutTenantVillageName.setError(error);
                getViewBinding().layoutTenantVillageName.requestFocus();
                break;
            case ERROR_TYPE_STREET:
                getViewBinding().layoutTenantStreetName.setError(error);
                getViewBinding().layoutTenantStreetName.requestFocus();
                break;
            case ERROR_TYPE_TENANT_HOUSE:
                getViewBinding().layoutTenantHouseName.setError(error);
                getViewBinding().layoutTenantHouseName.requestFocus();
                break;
            case ERROR_TYPE_TENANT_NATIVE:
                getViewBinding().layoutTenantNative.setError(error);
                getViewBinding().layoutTenantNative.requestFocus();
                break;
            case ERROR_TYPE_TENANT_POSTOFFICE:
                getViewBinding().layoutTenantPostOffice.setError(error);
                getViewBinding().layoutTenantPostOffice.requestFocus();
                break;
            case ERROR_TYPE_TENANT_SURVEY_NUMBER:
                getViewBinding().layoutTenantSurveyNumber.setError(error);
                getViewBinding().layoutTenantSurveyNumber.requestFocus();
                break;
            case ERROR_TYPE_RENT_AMOUNT:
                getViewBinding().layoutTenantRentAmount.setError(error);
                getViewBinding().layoutTenantRentAmount.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {

        getViewBinding().layoutTenantFirstName.setErrorEnabled(false);
        getViewBinding().layoutTenantLastName.setErrorEnabled(false);
        getViewBinding().layoutTenantEmail.setErrorEnabled(false);
        getViewBinding().layoutTenantMobile.setErrorEnabled(false);
        getViewBinding().layoutTenantLandline.setErrorEnabled(false);
        getViewBinding().layoutTenantGender.setErrorEnabled(false);
        getViewBinding().layoutTenantPlaceName.setErrorEnabled(false);
        getViewBinding().layoutTenantVillageName.setErrorEnabled(false);
        getViewBinding().layoutTenantStreetName.setErrorEnabled(false);
        getViewBinding().layoutTenantHouseName.setErrorEnabled(false);
        getViewBinding().layoutTenantNative.setErrorEnabled(false);
        getViewBinding().layoutTenantPostOffice.setErrorEnabled(false);
        getViewBinding().layoutTenantSurveyNumber.setErrorEnabled(false);
        getViewBinding().layoutTenantRentAmount.setErrorEnabled(false);
    }

    public void setEditData() {
        if(AppCacheData.getOurInstance().getBuildingAssetData()!=null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getTenantDetails()!=null) {
            BuildingAssets.TenantDetails tenantDetails = AppCacheData.getOurInstance().getBuildingAssetData().getTenantDetails();
            getViewBinding().etTenantFirstName.setText(tenantDetails.getFirstname());
            getViewBinding().etTenantLastName.setText(tenantDetails.getLastname());
            getViewBinding().etTenantMobile.setText(tenantDetails.getMobile());
            getViewBinding().etTenantLandline.setText(tenantDetails.getLandphone());
            genderAdapter.setContent(String.valueOf(tenantDetails.getGender()));
            getViewBinding().etTenantEmail.setText(tenantDetails.getEmail());
            getViewBinding().etTenantPlaceName.setText(tenantDetails.getPlaceName());
            getViewBinding().etTenantVillageName.setText(tenantDetails.getVillage());
            getViewBinding().etTenantStreetName.setText(tenantDetails.getStreet());
            getViewBinding().etTenantHouseName.setText(tenantDetails.getTntHouseName());
            getViewBinding().etTenantNative.setText(tenantDetails.getTntNative());
            getViewBinding().etTenantPostOffice.setText(tenantDetails.getTntPostOffice());
            getViewBinding().etTenantSurveyNumber.setText(tenantDetails.getTntSurveyNo());
            getViewBinding().etTenantRentAmount.setText(tenantDetails.getRentAmount());
        }
    }

    @Override
    public void onAddOrUpdateSuccess() {
        getBaseActivity().launchBuildAssetHome(false,false);
    }
}
