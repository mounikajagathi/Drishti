package in.ults.ipms.ui.buildingassets.establishment;

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
import in.ults.ipms.databinding.FragmentEstablishmentDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseFragment;

public class EstablishmentDetailsFragment extends BaseFragment<FragmentEstablishmentDetailsBinding> implements IEstablishmentDetailsView {

    public static final String TAG = EstablishmentDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_ESTABLISHMENT_NAME = 1;
    public static final int ERROR_TYPE_ESTABLISHMENT_POST = 2;
    public static final int ERROR_TYPE_ESTABLISHMENT_TYPE = 3;
    public static final int ERROR_TYPE_ESTABLISHMENT_YEAR = 4;
    public static final int ERROR_TYPE_MOBILE = 5;
    public static final int ERROR_TYPE_LAND_PHONE = 6;
    public static final int ERROR_TYPE_EMAIL = 7;
    public static final int ERROR_TYPE_INCHARGE_NAME = 8;
    public static final int ERROR_TYPE_INCHARGE_ROLE = 9;
    public static final int ERROR_TYPE_INCHARGE_POST = 10;
    public static final int ERROR_TYPE_TAX_PAYMENT_STATUS = 11;
    public static final int ERROR_TYPE_NUMBER_OF_EMPLOYEE = 12;
    public static final int ERROR_TYPE_LICENSE = 13;
    public static final int ERROR_TYPE_LICENSE_NUMBER = 14;
    public static final int ERROR_TYPE_GST_STATUS = 15;
    public static final int ERROR_TYPE_GST_NUMBER = 16;

    @Inject
    IEstablishmentDetailsPresenter<IEstablishmentDetailsView, IEstablishmentDetailsInteractor> presenter;

    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.EstLicense> estLicenseAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.EstGstStatus> estGstStatusAdapter;

    public static EstablishmentDetailsFragment newInstance() {
        return new EstablishmentDetailsFragment();
    }

    @Override
    protected  FragmentEstablishmentDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentEstablishmentDetailsBinding .inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.establishment_details_title);
    }

    @Override
    protected void initInjector() {getFragmentComponent().inject(this);

    }

    @Override
    protected void attachPresenter() {presenter.onAttach(this);

    }

    @Override
    protected void detachPresenter() {presenter.onDetach();

    }

    @Override
    protected void init(View view){
        getViewBinding().btnEstablishmentDetails.setOnClickListener(v -> submitOnClick());
        estLicenseAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srEstablishmentLicense, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getEstLicense());
        estGstStatusAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srEstablishmentGst, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getEstGstStatus());
//        if(AppCacheData.getOurInstance().isAssetUpdate()){
            setEditData();
//        }
    }

    void submitOnClick() {
        String establishmentName = Objects.requireNonNull(getViewBinding().etEstablishmentName.getText()).toString().trim();
        String establishmentPost = Objects.requireNonNull(getViewBinding().etEstablishmentPost.getText()).toString().trim();
        String establishmentType = Objects.requireNonNull(getViewBinding().etEstablishmentType.getText()).toString().trim();
        String establishmentYear = Objects.requireNonNull(getViewBinding().etEstablishmentYear.getText()).toString().trim();
        String mobile = Objects.requireNonNull(getViewBinding().etEstablishmentMobile.getText()).toString().trim();
        String landphone = Objects.requireNonNull(getViewBinding().etEstablishmentLandPhone.getText()).toString().trim();
        String email = Objects.requireNonNull(getViewBinding().etEstablishmentEmail.getText()).toString().trim();
        String inchargeName = Objects.requireNonNull(getViewBinding().etEstablishmentInchargeName.getText()).toString().trim();
        String inchargeRole = Objects.requireNonNull(getViewBinding().etEstablishmentInchargeRole.getText()).toString().trim();
        String inchargePost = Objects.requireNonNull(getViewBinding().etEstablishmentInchargePost.getText()).toString().trim();
        String professionalTaxPaymentStatus = Objects.requireNonNull(getViewBinding().etEstablishmentProfessionalTaxPaymentStatus.getText()).toString().trim();
        String numberOfEmployees = Objects.requireNonNull(getViewBinding().etEstablishmentNumberOfEmployees.getText()).toString().trim();
        String licenseNumber = Objects.requireNonNull(getViewBinding().etEstablishmentLicenseNumber.getText()).toString().trim();
        String gstNumber = Objects.requireNonNull(getViewBinding().etEstablishmentGstNumber.getText()).toString().trim();
        String gst = (String) getViewBinding().srEstablishmentGst.getTag();
        String license = (String) getViewBinding().srEstablishmentLicense.getTag();
        presenter.validateData(establishmentName,establishmentType,establishmentPost,establishmentYear,mobile,landphone,email,inchargeName,inchargeRole,inchargePost,professionalTaxPaymentStatus,numberOfEmployees,license,licenseNumber,gst,gstNumber);
    }

    @Override
    public void showEstablishmentDetailsFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_ESTABLISHMENT_NAME:
                getViewBinding().layoutEstablishmentName.setError(error);
                getViewBinding().layoutEstablishmentName.requestFocus();
                break;
            case ERROR_TYPE_ESTABLISHMENT_POST:
                getViewBinding().layoutEstablishmentPost.setError(error);
                getViewBinding().layoutEstablishmentPost.requestFocus();
                break;
            case ERROR_TYPE_ESTABLISHMENT_TYPE:
                getViewBinding().layoutEstablishmentType.setError(error);
                getViewBinding().layoutEstablishmentType.requestFocus();
                break;
            case ERROR_TYPE_ESTABLISHMENT_YEAR:
                getViewBinding().layoutEstablishmentYear.setError(error);
                getViewBinding().layoutEstablishmentYear.requestFocus();
                break;
            case ERROR_TYPE_MOBILE:
                getViewBinding().layoutEstablishmentMobile.setError(error);
                getViewBinding().layoutEstablishmentMobile.requestFocus();
                break;
            case ERROR_TYPE_LAND_PHONE:
                getViewBinding().layoutEstablishmentLandPhone.setError(error);
                getViewBinding().layoutEstablishmentLandPhone.requestFocus();
                break;
            case ERROR_TYPE_EMAIL:
                getViewBinding().layoutEstablishmentEmail.setError(error);
                getViewBinding().layoutEstablishmentEmail.requestFocus();
                break;
            case ERROR_TYPE_INCHARGE_NAME:
                getViewBinding().layoutEstablishmentInchargeName.setError(error);
                getViewBinding().layoutEstablishmentInchargeName.requestFocus();
                break;
            case ERROR_TYPE_INCHARGE_ROLE:
                getViewBinding().layoutEstablishmentInchargeRole.setError(error);
                getViewBinding().layoutEstablishmentInchargeRole.requestFocus();
                break;
            case ERROR_TYPE_INCHARGE_POST:
                getViewBinding().layoutEstablishmentInchargePost.setError(error);
                getViewBinding().layoutEstablishmentInchargePost.requestFocus();
                break;
            case ERROR_TYPE_TAX_PAYMENT_STATUS:
                getViewBinding().layoutEstablishmentProfessionalTaxPaymentStatus.setError(error);
                getViewBinding().layoutEstablishmentProfessionalTaxPaymentStatus.requestFocus();
                break;
            case ERROR_TYPE_NUMBER_OF_EMPLOYEE:
                getViewBinding().layoutEstablishmentNumberOfEmployees.setError(error);
                getViewBinding().layoutEstablishmentNumberOfEmployees.requestFocus();
                break;
            case ERROR_TYPE_LICENSE:
                getViewBinding().layoutEstablishmentLicense.setError(error);
                getViewBinding().layoutEstablishmentLicense.requestFocus();
                break;
            case ERROR_TYPE_LICENSE_NUMBER:
                getViewBinding().layoutEstablishmentLicenseNumber.setError(error);
                getViewBinding().layoutEstablishmentLicenseNumber.requestFocus();
                break;
            case ERROR_TYPE_GST_STATUS:
                getViewBinding().layoutEstablishmentGst.setError(error);
                getViewBinding().layoutEstablishmentGst.requestFocus();
                break;
            case ERROR_TYPE_GST_NUMBER:
                getViewBinding().layoutEstablishmentGstNumber.setError(error);
                getViewBinding().layoutEstablishmentGstNumber.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutEstablishmentName.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentPost.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentType.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentYear.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentMobile.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentLandPhone.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentEmail.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentInchargeName.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentInchargeRole.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentInchargePost.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentProfessionalTaxPaymentStatus.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentNumberOfEmployees.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentLicense.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentLicenseNumber.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentGst.setErrorEnabled(false);
        getViewBinding().layoutEstablishmentGstNumber.setErrorEnabled(false);
    }

    public void setEditData() {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getEstablishmentDetails() != null) {
            BuildingAssets.EstablishmentDetails establishmentDetails = AppCacheData.getOurInstance().getBuildingAssetData().getEstablishmentDetails();
            getViewBinding().etEstablishmentName.setText(establishmentDetails.getEstbName());
            getViewBinding().etEstablishmentType.setText(establishmentDetails.getEstbType());
            getViewBinding().etEstablishmentPost.setText(establishmentDetails.getEstbPost());
            getViewBinding().etEstablishmentYear.setText(String.valueOf(establishmentDetails.getEstbYear()));
            getViewBinding().etEstablishmentMobile.setText(establishmentDetails.getMobile());
            getViewBinding().etEstablishmentLandPhone.setText(establishmentDetails.getLandphone());
            getViewBinding().etEstablishmentEmail.setText(establishmentDetails.getEmail());
            getViewBinding().etEstablishmentInchargeName.setText(establishmentDetails.getInchrg_name());
            getViewBinding().etEstablishmentInchargeRole.setText(establishmentDetails.getInchrgRole());
            getViewBinding().etEstablishmentInchargePost.setText(establishmentDetails.getInchrgPost());
            getViewBinding().etEstablishmentProfessionalTaxPaymentStatus.setText(establishmentDetails.getProfTaxPayStatus());
            getViewBinding().etEstablishmentNumberOfEmployees.setText(String.valueOf(establishmentDetails.getNo_of_employees()));
            getViewBinding().etEstablishmentLicenseNumber.setText(establishmentDetails.getLicenceNo());
            getViewBinding().etEstablishmentGstNumber.setText(establishmentDetails.getGstNo());
            estLicenseAdapter.setContent(establishmentDetails.getLicence());
            estGstStatusAdapter.setContent(establishmentDetails.getGstStatus());
        }
    }

    @Override
    public void onAddOrUpdateSuccess() {
        getBaseActivity().launchBuildAssetHome(false,false);
    }
}
