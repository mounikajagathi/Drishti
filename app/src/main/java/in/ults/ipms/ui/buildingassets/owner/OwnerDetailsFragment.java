package in.ults.ipms.ui.buildingassets.owner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.CommonSpinnerAdapter;
import in.ults.ipms.data.network.model.response.BuildingAssetSpinnerResponse;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.databinding.FragmentOwnerDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseFragment;

public class OwnerDetailsFragment extends BaseFragment<FragmentOwnerDetailsBinding> implements IOwnerDetailsView {

    public static final String TAG = OwnerDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_FIRST_NAME = 1;
    public static final int ERROR_TYPE_LAST_NAME = 2;
    public static final int ERROR_TYPE_NAME_SANJAYA = 3;
    public static final int ERROR_TYPE_EMAIL = 4;
    public static final int ERROR_TYPE_MOBILE = 5;
    public static final int ERROR_TYPE_LAND_PHONE = 6;
    public static final int ERROR_TYPE_GENDER = 7;
    public static final int ERROR_TYPE_PLACE_NAME = 8;
    public static final int ERROR_TYPE_VILLAGE = 9;
    public static final int ERROR_TYPE_STREET = 10;
    public static final int ERROR_TYPE_OWNER_HOUSE = 11;
    public static final int ERROR_TYPE_OWNER_OCCUPATION = 12;
    public static final int ERROR_TYPE_OWNER_STATE = 13;
    public static final int ERROR_TYPE_OWNER_POSTOFFICE = 14;
    public static final int ERROR_TYPE_OWNER_PINCODE = 15;
    public static final int ERROR_TYPE_OWNER_SURVEY_NUMBER = 16;
    public static int selectedPosition = -1;


    @Inject
    IOwnerDetailsPresenter<IOwnerDetailsView, IOwnerDetailsInteractor> presenter;

    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Genders> genderAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Jobs> jobsAdapter;

    public static OwnerDetailsFragment newInstance() {
        return new OwnerDetailsFragment();
    }

    @Override
    protected FragmentOwnerDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentOwnerDetailsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.owner_details_title);
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
        getViewBinding().btnOwnerDetails.setOnClickListener(v -> submitOnClick());
        genderAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srOwnerGender, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getGenders());
        jobsAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srOwnerOccupation, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getJobs());

        if (/*AppCacheData.getOurInstance().isAssetUpdate()  &&*/ selectedPosition!=-1) {
            setEditData();
        }
    }

    void submitOnClick() {
        String firstName = Objects.requireNonNull(getViewBinding().etOwnerFirstName.getText()).toString().trim();
        String lastName = Objects.requireNonNull(getViewBinding().etOwnerLastName.getText()).toString().trim();
        String nameSanjaya = Objects.requireNonNull(getViewBinding().etOwnerNameSanjaya.getText()).toString().trim();
        String email = Objects.requireNonNull(getViewBinding().etOwnerEmail.getText()).toString().trim();
        String mobile = Objects.requireNonNull(getViewBinding().etOwnerMobile.getText()).toString().trim();
        String landphone = Objects.requireNonNull(getViewBinding().etOwnerLandline.getText()).toString().trim();
        String gender = (String) getViewBinding().srOwnerGender.getTag();
        String placeName = Objects.requireNonNull(getViewBinding().etOwnerPlaceName.getText()).toString().trim();
        String village = Objects.requireNonNull(getViewBinding().etOwnerVillageName.getText()).toString().trim();
        String street = Objects.requireNonNull(getViewBinding().etOwnerStreetName.getText()).toString().trim();
        String ownerHouse = Objects.requireNonNull(getViewBinding().etOwnerHouseName.getText()).toString().trim();
        String ownerOccupation = (String) getViewBinding().srOwnerOccupation.getTag();
        String ownerState = Objects.requireNonNull(getViewBinding().etOwnerState.getText()).toString().trim();
        String ownerPostoffice = Objects.requireNonNull(getViewBinding().etOwnerPostOffice.getText()).toString().trim();
        String ownerPincode = Objects.requireNonNull(getViewBinding().etOwnerPincode.getText()).toString().trim();
        String ownerSurveyNumber = Objects.requireNonNull(getViewBinding().etOwnerSurveyNumber.getText()).toString().trim();

        presenter.validateData(firstName, lastName,nameSanjaya, email, mobile, landphone, gender, placeName, village, street, ownerHouse, ownerOccupation,ownerState, ownerPostoffice,ownerPincode, ownerSurveyNumber);

    }

    public void showOwnerDetailsFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_FIRST_NAME:
                getViewBinding().layoutOwnerFirstName.setError(error);
                getViewBinding().layoutOwnerFirstName.requestFocus();
                break;
            case ERROR_TYPE_LAST_NAME:
                getViewBinding().layoutOwnerLastName.setError(error);
                getViewBinding().layoutOwnerLastName.requestFocus();
                break;
            case ERROR_TYPE_NAME_SANJAYA:
                getViewBinding().layoutOwnerNameSanjaya.setError(error);
                getViewBinding().layoutOwnerNameSanjaya.requestFocus();
                break;
            case ERROR_TYPE_EMAIL:
                getViewBinding().layoutOwnerEmail.setError(error);
                getViewBinding().layoutOwnerEmail.requestFocus();
                break;
            case ERROR_TYPE_MOBILE:
                getViewBinding().layoutOwnerMobile.setError(error);
                getViewBinding().layoutOwnerMobile.requestFocus();
                break;
            case ERROR_TYPE_LAND_PHONE:
                getViewBinding().layoutOwnerLandline.setError(error);
                getViewBinding().layoutOwnerLandline.requestFocus();
                break;
            case ERROR_TYPE_GENDER:
                getViewBinding().layoutOwnerGender.setError(error);
                getViewBinding().layoutOwnerGender.requestFocus();
                break;
            case ERROR_TYPE_PLACE_NAME:
                getViewBinding().layoutOwnerPlaceName.setError(error);
                getViewBinding().layoutOwnerPlaceName.requestFocus();
                break;
            case ERROR_TYPE_VILLAGE:
                getViewBinding().layoutOwnerVillageName.setError(error);
                getViewBinding().layoutOwnerVillageName.requestFocus();
                break;
            case ERROR_TYPE_STREET:
                getViewBinding().layoutOwnerStreetName.setError(error);
                getViewBinding().layoutOwnerStreetName.requestFocus();
                break;
            case ERROR_TYPE_OWNER_HOUSE:
                getViewBinding().layoutOwnerHouseName.setError(error);
                getViewBinding().layoutOwnerHouseName.requestFocus();
                break;
            case ERROR_TYPE_OWNER_OCCUPATION:
                getViewBinding().layoutOwnerOccupation.setError(error);
                getViewBinding().layoutOwnerOccupation.requestFocus();
                break;
            case ERROR_TYPE_OWNER_STATE:
                getViewBinding().layoutOwnerState.setError(error);
                getViewBinding().layoutOwnerState.requestFocus();
                break;
            case ERROR_TYPE_OWNER_POSTOFFICE:
                getViewBinding().layoutOwnerPostOffice.setError(error);
                getViewBinding().layoutOwnerPostOffice.requestFocus();
                break;
            case ERROR_TYPE_OWNER_PINCODE:
                getViewBinding().layoutOwnerPincode.setError(error);
                getViewBinding().layoutOwnerPincode.requestFocus();
                break;
            case ERROR_TYPE_OWNER_SURVEY_NUMBER:
                getViewBinding().layoutOwnerSurveyNumber.setError(error);
                getViewBinding().layoutOwnerSurveyNumber.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutOwnerFirstName.setErrorEnabled(false);
        getViewBinding().layoutOwnerLastName.setErrorEnabled(false);
        getViewBinding().layoutOwnerNameSanjaya.setErrorEnabled(false);
        getViewBinding().layoutOwnerEmail.setErrorEnabled(false);
        getViewBinding().layoutOwnerMobile.setErrorEnabled(false);
        getViewBinding().layoutOwnerLandline.setErrorEnabled(false);
        getViewBinding().layoutOwnerGender.setErrorEnabled(false);
        getViewBinding().layoutOwnerPlaceName.setErrorEnabled(false);
        getViewBinding().layoutOwnerVillageName.setErrorEnabled(false);
        getViewBinding().layoutOwnerStreetName.setErrorEnabled(false);
        getViewBinding().layoutOwnerHouseName.setErrorEnabled(false);
        getViewBinding().layoutOwnerOccupation.setErrorEnabled(false);
        getViewBinding().layoutOwnerState.setErrorEnabled(false);
        getViewBinding().layoutOwnerPostOffice.setErrorEnabled(false);
        getViewBinding().layoutOwnerPincode.setErrorEnabled(false);
        getViewBinding().layoutOwnerSurveyNumber.setErrorEnabled(false);
    }


    public void setEditData() {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getOwnerDetails() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getOwnerDetails().size() > selectedPosition) {
            ArrayList<BuildingAssets.OwnerDetails> ownerDetails = AppCacheData.getOurInstance().getBuildingAssetData().getOwnerDetails();
            BuildingAssets.OwnerDetails ownerDetail = ownerDetails.get(selectedPosition);
            getViewBinding().etOwnerFirstName.setText(ownerDetail.getFirstname());
            getViewBinding().etOwnerLastName.setText(ownerDetail.getLastname());
            getViewBinding().etOwnerNameSanjaya.setText(ownerDetail.getOwnerNameSanjaya());
            genderAdapter.setContent(String.valueOf(ownerDetail.getGender()));
            getViewBinding().etOwnerPlaceName.setText(ownerDetail.getPlaceName());
            getViewBinding().etOwnerVillageName.setText(ownerDetail.getVillage());
            getViewBinding().etOwnerStreetName.setText(ownerDetail.getStreet());
            getViewBinding().etOwnerHouseName.setText(ownerDetail.getOwnerHouse());
            getViewBinding().etOwnerMobile.setText(ownerDetail.getMobile());
            getViewBinding().etOwnerLandline.setText(ownerDetail.getLandphone());
            getViewBinding().etOwnerEmail.setText(ownerDetail.getEmail());
            jobsAdapter.setContent(String.valueOf(ownerDetail.getOwnerOccup()));
            getViewBinding().etOwnerState.setText(ownerDetail.getOwnerState());
            getViewBinding().etOwnerPostOffice.setText(ownerDetail.getOwnerPost());
            getViewBinding().etOwnerPincode.setText(ownerDetail.getOwnerPin());
            getViewBinding().etOwnerSurveyNumber.setText(ownerDetail.getOwnerSurveyNo());
        }
    }

    @Override
    public void onAddOrUpdateSuccess() {
        onFragmentBackPressed();
    }

}
