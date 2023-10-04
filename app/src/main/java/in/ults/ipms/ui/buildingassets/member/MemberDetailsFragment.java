package in.ults.ipms.ui.buildingassets.member;

import android.app.DatePickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.CommonMultiSelectAdapter;
import in.ults.ipms.adapters.CommonSpinnerAdapter;
import in.ults.ipms.adapters.DisabilityAdapter;
import in.ults.ipms.data.network.model.request.DeleteAssetDataRequest;
import in.ults.ipms.data.network.model.response.BuildingAssetSpinnerResponse;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.databinding.FragmentMemberDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseFragment;


public class MemberDetailsFragment extends BaseFragment<FragmentMemberDetailsBinding> implements IMemberDetailsView {

    public static final String TAG = MemberDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_FIRST_NAME = 1;
    public static final int ERROR_TYPE_LAST_NAME = 2;
    public static final int ERROR_TYPE_GENDER = 3;
    public static final int ERROR_TYPE_AGE = 4;
    public static final int ERROR_TYPE_MEMBER_OCCUPATION = 5;
    public static final int ERROR_TYPE_BLOOD_GROUP = 6;
    public static final int ERROR_TYPE_DOB = 7;
    public static final int ERROR_TYPE_CASTE = 8;
    public static final int ERROR_TYPE_RELIGION = 9;
    public static final int ERROR_TYPE_EDUCATION_CATEGORY = 10;
    public static final int ERROR_TYPE_EDUCATION = 11;
    public static final int ERROR_TYPE_MARITAL_STATUS = 12;
    public static final int ERROR_TYPE_IS_COOLIE_WAGE = 13;
    public static final int ERROR_TYPE_IS_ALIVE = 14;
    public static final int ERROR_TYPE_IS_QUALIFIED = 15;
    public static final int ERROR_TYPE_BANK_ACCOUNT = 16;
    public static final int ERROR_TYPE_NRI_OR_NRK = 17;
    public static final int ERROR_TYPE_BANK_TYPE = 18;
    public static final int ERROR_TYPE_PENSION = 19;
    public static final int ERROR_TYPE_DISEASE = 20;
    public static final int ERROR_TYPE_DISABILITY = 21;
    public static final int ERROR_TYPE_DISABILITY_PERCENTAGE = 22;
    public static int selectedPosition = -1;

    public static final int REMOVE_TYPE_DISABILITY = 20;

    @Inject
    IMemberDetailsPresenter<IMemberDetailsView, IMemberDetailsInteractor> presenter;

    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Genders> genderAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Jobs> jobAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.BloodGroups> bloodGroupAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Religions> religionsAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Castes> castesAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.EducationCategories> educationCategoryAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.Educations> educationAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.MaritalStatus> maritalStatusAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.IsCoolieWage> isCoolieWageAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.IsAlive> isAliveAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.IsQualified> isQualifiedAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.BankAccount> bankAccountAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.NriOrNrk> nriOrNrkAdapter;
    CommonMultiSelectAdapter<BuildingAssetSpinnerResponse.DiseaseTypes> diseaseAdapter;
    CommonMultiSelectAdapter<BuildingAssetSpinnerResponse.PensionTypes> pensionAdapter;
    CommonMultiSelectAdapter<BuildingAssetSpinnerResponse.BankType> bankTypeAdapter;

    @Inject
    DisabilityAdapter disabilityAdapter;


    public static MemberDetailsFragment newInstance() {
        return new MemberDetailsFragment();
    }

    @Override
    protected  FragmentMemberDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentMemberDetailsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.member_details_title);
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
        getViewBinding().btnMemberDetails.setOnClickListener(v -> submitOnClick());
        diseaseAdapter = new CommonMultiSelectAdapter<>();
        pensionAdapter = new CommonMultiSelectAdapter<>();
        bankTypeAdapter = new CommonMultiSelectAdapter<>();
        genderAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srMemberGender, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getGenders());
        jobAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srMemberOccupation, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getJobs());
        religionsAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srMemberReligion, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getReligions());
        castesAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srMemberCaste, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getCastes());
        bloodGroupAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srMemberBloodGroup, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getBloodGroups());
        educationCategoryAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srMemberEducationCategory, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getEducationCategories());
        educationAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srMemberEducation, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getEducations());
        maritalStatusAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srMemberMaritalStatus, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getMaritalStatus());
        isCoolieWageAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srMemberIsCoolieWage, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getIsCoolieWage());
        isAliveAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srMemberIsAlive, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getIsAlive());
        isQualifiedAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srMemberIsQualified, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getIsQualified());
        bankAccountAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srMemberBankAccount, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getBankAccount());
        nriOrNrkAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srMemberNrkOrNri, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getNriOrNrk());
        diseaseAdapter.setLocalDataSet(AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getDiseaseTypes());
        pensionAdapter.setLocalDataSet(AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getPensionTypes());
        bankTypeAdapter.setLocalDataSet(AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getBankTypes());
        final Calendar newCalendar = Calendar.getInstance();
        final DatePickerDialog datePicker = new DatePickerDialog(getBaseActivity(), (view1, year, monthOfYear, dayOfMonth) -> {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            getViewBinding().etMemberDOB.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(newDate.getTime()));
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePicker.getDatePicker().setMaxDate(newCalendar.getTimeInMillis());
        getViewBinding().etMemberDOB.setOnClickListener(v -> datePicker.show());
        getViewBinding().rvDisease.setAdapter(diseaseAdapter);
        getViewBinding().rvBankType.setAdapter(bankTypeAdapter);
        getViewBinding().rvPension.setAdapter(pensionAdapter);
        getViewBinding().rvDisability.setAdapter(disabilityAdapter);
        disabilityAdapter.setDisabilityList(AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getDisabilityTypes());
        disabilityAdapter.setRemoveListener((position, pk) -> {
            DeleteAssetDataRequest request = new DeleteAssetDataRequest();
            request.setDisability(new DeleteAssetDataRequest.AssetDeleteDetails(pk));
            presenter.deleteAssetDetails(REMOVE_TYPE_DISABILITY, request, position);
        });

        if(/*AppCacheData.getOurInstance().isAssetUpdate() &&*/ selectedPosition != -1){
            setEditData();
        }
    }

    void submitOnClick() {
        String firstName = Objects.requireNonNull(getViewBinding().etMemberFirstName.getText()).toString().trim();
        String lastName = Objects.requireNonNull(getViewBinding().etMemberLastName.getText()).toString().trim();
        String gender = (String) getViewBinding().srMemberGender.getTag();
        String age = Objects.requireNonNull(getViewBinding().etMemberAge.getText()).toString().trim();
        boolean isMonth = getViewBinding().cbMemberIsMonth.isChecked();
        String memberOccupation = (String) getViewBinding().srMemberOccupation.getTag();
        String memberDOB = Objects.requireNonNull(getViewBinding().etMemberDOB.getText()).toString().trim();
        String religion = (String) getViewBinding().srMemberReligion.getTag();
        String caste = (String) getViewBinding().srMemberCaste.getTag();
        String bloodGroup = (String) getViewBinding().srMemberBloodGroup.getTag();
        String educationCategory = (String) getViewBinding().srMemberEducationCategory.getTag();
        String education = (String) getViewBinding().srMemberEducation.getTag();
        String maritalStatus = (String) getViewBinding().srMemberMaritalStatus.getTag();
        String isCoolieWage = (String) getViewBinding().srMemberIsCoolieWage.getTag();
        String isAlive = (String) getViewBinding().srMemberIsAlive.getTag();
        String isQualified = (String) getViewBinding().srMemberIsQualified.getTag();
        String bankAccount = (String) getViewBinding().srMemberBankAccount.getTag();
        String nriOrNrk = (String) getViewBinding().srMemberNrkOrNri.getTag();
        ArrayList<String> bankType = bankTypeAdapter.getSelectedId();
        ArrayList<String> pension = pensionAdapter.getSelectedId();
        ArrayList<String> disease = diseaseAdapter.getSelectedId();
        presenter.validateData(firstName,lastName,gender,age,isMonth,memberOccupation,bloodGroup, memberDOB, religion, caste,educationCategory,education,maritalStatus,isCoolieWage,isAlive,isQualified,bankAccount,nriOrNrk,bankType,pension,disease,disabilityAdapter);
    }

    @Override
    public void showMemberDetailsFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_FIRST_NAME:
                getViewBinding().layoutMemberFirstName.setError(error);
                getViewBinding().layoutMemberFirstName.requestFocus();
                break;
            case ERROR_TYPE_LAST_NAME:
                getViewBinding().layoutMemberLastName.setError(error);
                getViewBinding().layoutMemberLastName.requestFocus();
                break;
            case ERROR_TYPE_GENDER:
                getViewBinding().layoutMemberGender.setError(error);
                getViewBinding().layoutMemberGender.requestFocus();
                break;
            case ERROR_TYPE_AGE:
                getViewBinding().layoutMemberAge.setError(error);
                getViewBinding().layoutMemberAge.requestFocus();
                break;
            case ERROR_TYPE_MEMBER_OCCUPATION:
                getViewBinding().layoutMemberOccupation.setError(error);
                getViewBinding().layoutMemberOccupation.requestFocus();
                break;
            case ERROR_TYPE_BLOOD_GROUP:
                getViewBinding().layoutMemberBloodGroup.setError(error);
                getViewBinding().layoutMemberBloodGroup.requestFocus();
                break;
            case ERROR_TYPE_DOB:
                getViewBinding().layoutMemberDOB.setError(error);
                getViewBinding().layoutMemberDOB.requestFocus();
                break;
            case ERROR_TYPE_RELIGION:
                getViewBinding().layoutMemberReligion.setError(error);
                getViewBinding().layoutMemberReligion.requestFocus();
                break;
            case ERROR_TYPE_CASTE:
                getViewBinding().layoutMemberCaste.setError(error);
                getViewBinding().layoutMemberCaste.requestFocus();
                break;
            case ERROR_TYPE_EDUCATION_CATEGORY:
                getViewBinding().layoutMemberEducationCategory.setError(error);
                getViewBinding().layoutMemberEducationCategory.requestFocus();
                break;
            case ERROR_TYPE_EDUCATION:
                getViewBinding().layoutMemberEducation.setError(error);
                getViewBinding().layoutMemberEducation.requestFocus();
                break;
            case ERROR_TYPE_MARITAL_STATUS:
                getViewBinding().layoutMemberMaritalStatus.setError(error);
                getViewBinding().layoutMemberMaritalStatus.requestFocus();
                break;
            case ERROR_TYPE_IS_COOLIE_WAGE:
                getViewBinding().layoutMemberIsCoolieWage.setError(error);
                getViewBinding().layoutMemberIsCoolieWage.requestFocus();
                break;
            case ERROR_TYPE_IS_ALIVE:
                getViewBinding().layoutMemberIsAlive.setError(error);
                getViewBinding().layoutMemberIsAlive.requestFocus();
                break;
            case ERROR_TYPE_IS_QUALIFIED:
                getViewBinding().layoutMemberIsQualified.setError(error);
                getViewBinding().layoutMemberIsQualified.requestFocus();
                break;
            case ERROR_TYPE_BANK_ACCOUNT:
                getViewBinding().layoutMemberBankAccount.setError(error);
                getViewBinding().layoutMemberBankAccount.requestFocus();
                break;
            case ERROR_TYPE_NRI_OR_NRK:
                getViewBinding().layoutMemberNrkOrNri.setError(error);
                getViewBinding().layoutMemberNrkOrNri.requestFocus();
                break;
            case ERROR_TYPE_BANK_TYPE:
            case ERROR_TYPE_PENSION:
            case ERROR_TYPE_DISEASE:
                showToast(error);
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutMemberFirstName.setErrorEnabled(false);
        getViewBinding().layoutMemberLastName.setErrorEnabled(false);
        getViewBinding().layoutMemberGender.setErrorEnabled(false);
        getViewBinding().layoutMemberAge.setErrorEnabled(false);
        getViewBinding().layoutMemberOccupation.setErrorEnabled(false);
        getViewBinding().layoutMemberBloodGroup.setErrorEnabled(false);
        getViewBinding().layoutMemberDOB.setErrorEnabled(false);
        getViewBinding().layoutMemberReligion.setErrorEnabled(false);
        getViewBinding().layoutMemberCaste.setErrorEnabled(false);
        getViewBinding().layoutMemberEducationCategory.setErrorEnabled(false);
        getViewBinding().layoutMemberEducation.setErrorEnabled(false);
        getViewBinding().layoutMemberMaritalStatus.setErrorEnabled(false);
        getViewBinding().layoutMemberIsCoolieWage.setErrorEnabled(false);
        getViewBinding().layoutMemberIsAlive.setErrorEnabled(false);
        getViewBinding().layoutMemberIsQualified.setErrorEnabled(false);
        getViewBinding().layoutMemberBankAccount.setErrorEnabled(false);
        getViewBinding().layoutMemberNrkOrNri.setErrorEnabled(false);
    }

    public void setEditData() {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getMemberDetails() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getMemberDetails().size() > selectedPosition) {
            ArrayList<BuildingAssets.MemberDetails> memberDetails;
            memberDetails = AppCacheData.getOurInstance().getBuildingAssetData().getMemberDetails();
            BuildingAssets.MemberDetails memberDetail = memberDetails.get(selectedPosition);
            getViewBinding().etMemberFirstName.setText(memberDetail.getFirstname());
            getViewBinding().etMemberLastName.setText(memberDetail.getLastname());
            genderAdapter.setContent(String.valueOf(memberDetail.getGender()));
            getViewBinding().etMemberAge.setText(String.valueOf(memberDetail.getAge()));
            getViewBinding().etMemberDOB.setText(memberDetail.getDateOfBirth());
            getViewBinding().cbMemberIsMonth.setChecked(memberDetail.isMonth());
            jobAdapter.setContent(String.valueOf(memberDetail.getOccuppation()));
            bloodGroupAdapter.setContent(String.valueOf(memberDetail.getBloodGroup()));
            religionsAdapter.setContent(String.valueOf(memberDetail.getReligion()));
            castesAdapter.setContent(String.valueOf(memberDetail.getCaste()));
            educationCategoryAdapter.setContent(String.valueOf(memberDetail.getEducationCategory()));
            educationAdapter.setContent(String.valueOf(memberDetail.getEducation()));
            maritalStatusAdapter.setContent(String.valueOf(memberDetail.getMaritalStatus()));
            isCoolieWageAdapter.setContent(memberDetail.getIsCoolieWage());
            isAliveAdapter.setContent(memberDetail.getIsAlive());
            isQualifiedAdapter.setContent(memberDetail.getIsQualified());
            bankAccountAdapter.setContent(memberDetail.getBankAccount());
            bankTypeAdapter.setSelectedId(memberDetail.getBankType());
            nriOrNrkAdapter.setContent(memberDetail.getNriOrNrk());
            diseaseAdapter.setSelectedId(memberDetail.getDiseases());
            pensionAdapter.setSelectedId(memberDetail.getPension());

        }
    }

    @Override
    public void onAddOrUpdateSuccess() {
        onFragmentBackPressed();
    }

}
