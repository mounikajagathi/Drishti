package in.ults.ipms.ui.buildingassets.member;

import static in.ults.ipms.ui.buildingassets.member.MemberDetailsFragment.selectedPosition;

import java.util.ArrayList;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.DisabilityAdapter;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;

public class MemberDetailsPresenter<V extends IMemberDetailsView, I extends IMemberDetailsInteractor> extends BasePresenter<V, I> implements IMemberDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public MemberDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String firstName, String lastName, String gender, String age, boolean isMonth, String memberOccupation, String bloodGroup, String memberDOB, String religion, String caste, String educationCategory, String education, String maritalStatus, String isCoolieWage, String isAlive, String isQualified, String bankAccount, String nriOrNrk, ArrayList<String> bankType, ArrayList<String> pension, ArrayList<String> disease, DisabilityAdapter disabilityAdapter) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(firstName)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_FIRST_NAME, baseActivity.getResources().getString(R.string.err_first_name));
            return;
        }
        if (CommonUtils.isNullString(lastName)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_LAST_NAME, baseActivity.getResources().getString(R.string.err_last_name));
            return;
        }
        if (CommonUtils.isNullString(gender)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_GENDER, baseActivity.getResources().getString(R.string.err_gender));
            return;
        }
        if (CommonUtils.isNullString(age)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_AGE, baseActivity.getResources().getString(R.string.err_age));
            return;
        }
        if (CommonUtils.isNullString(memberOccupation)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_MEMBER_OCCUPATION, baseActivity.getResources().getString(R.string.err_occupation));
            return;
        }
        if (CommonUtils.isNullString(bloodGroup)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_BLOOD_GROUP, baseActivity.getResources().getString(R.string.err_blood_group));
            return;
        }
        if (CommonUtils.isNullString(memberDOB)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_DOB, baseActivity.getResources().getString(R.string.err_date_of_birth));
            return;
        }
        if (CommonUtils.isNullString(religion)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_RELIGION, baseActivity.getResources().getString(R.string.err_religion));
            return;
        }
        if (CommonUtils.isNullString(caste)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_CASTE, baseActivity.getResources().getString(R.string.err_caste));
            return;
        }
        if (CommonUtils.isNullString(educationCategory)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_EDUCATION_CATEGORY, baseActivity.getResources().getString(R.string.err_education_category));
            return;
        }
        if (CommonUtils.isNullString(education)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_EDUCATION, baseActivity.getResources().getString(R.string.err_education));
            return;
        }
        if (CommonUtils.isNullString(maritalStatus)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_MARITAL_STATUS, baseActivity.getResources().getString(R.string.err_marital_status));
            return;
        }
        if (CommonUtils.isNullString(isCoolieWage)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_IS_COOLIE_WAGE, baseActivity.getResources().getString(R.string.err_is_coolie_wage));
            return;
        }
        if (CommonUtils.isNullString(isAlive)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_IS_ALIVE, baseActivity.getResources().getString(R.string.err_is_alive));
            return;
        }
        if (CommonUtils.isNullString(isQualified)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_IS_QUALIFIED, baseActivity.getResources().getString(R.string.err_is_qualified));
            return;
        }
        if (CommonUtils.isNullString(bankAccount)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_BANK_ACCOUNT, baseActivity.getResources().getString(R.string.err_bank_account));
            return;
        }
        if (CommonUtils.isNullString(nriOrNrk)) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_NRI_OR_NRK, baseActivity.getResources().getString(R.string.err_nri_nrk));
            return;
        }
        if (bankType.size() == 0) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_BANK_TYPE, baseActivity.getResources().getString(R.string.err_bank_type));
            return;
        }
        if (pension.size() == 0) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_PENSION, baseActivity.getResources().getString(R.string.err_pension));
            return;
        }
        if (disease.size() == 0) {
            getMvpView().showMemberDetailsFieldError(MemberDetailsFragment.ERROR_TYPE_DISEASE, baseActivity.getResources().getString(R.string.err_disease));
            return;
        }
        if (!disabilityAdapter.validateData()) {
            return;
        }

        savingMemberDetails(firstName, lastName, gender, age, isMonth, memberOccupation, bloodGroup, memberDOB, religion, caste, educationCategory, education, maritalStatus, isCoolieWage, isAlive, isQualified, bankAccount, nriOrNrk, bankType, pension, disease, disabilityAdapter.getLocalDataSet());
    }

    public void savingMemberDetails(String firstName, String lastName, String gender, String age, boolean isMonth, String memberOccupation, String bloodGroup, String memberDOB, String religion, String caste, String educationCategory, String education, String maritalStatus, String isCoolieWage, String isAlive, String isQualified, String bankAccount, String nriOrNrk, ArrayList<String> bankType, ArrayList<String> pension, ArrayList<String> disease, ArrayList<BuildingAssets.Disability> disability) {
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
                ArrayList<BuildingAssets.MemberDetails> memberList = AppCacheData.getOurInstance().getBuildingAssetData().getMemberDetails();
                data.setMemberDetails(memberList == null ? new ArrayList<>() : new ArrayList<>(memberList));
                BuildingAssets.MemberDetails memberDetails;
                if (selectedPosition == -1) {
                    memberDetails = new BuildingAssets.MemberDetails();
                    memberDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                    BuildingAssets.PropertyDetails propertyDetails = AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails();
                    if (propertyDetails != null) {
                        memberDetails.setProperty(propertyDetails.getPk());
                    }
                } else {
                    memberDetails = data.getMemberDetails().get(selectedPosition);
                    memberDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                }
                memberDetails.setFirstname(firstName);
                memberDetails.setLastname(lastName);
                memberDetails.setGender(Integer.parseInt(gender));
                memberDetails.setAge(Integer.parseInt(age));
                memberDetails.setMonth(isMonth);
                memberDetails.setOccuppation(Integer.parseInt(memberOccupation));
                memberDetails.setBloodGroup(Integer.parseInt(bloodGroup));
                memberDetails.setDateOfBirth(memberDOB);
                memberDetails.setReligion(Integer.parseInt(religion));
                memberDetails.setCaste(Integer.parseInt(caste));
                memberDetails.setEducationCategory(Integer.parseInt(educationCategory));
                memberDetails.setEducation(Integer.parseInt(education));
                memberDetails.setMaritalStatus(Integer.parseInt(maritalStatus));
                memberDetails.setIsCoolieWage(isCoolieWage);
                memberDetails.setIsAlive(isAlive);
                memberDetails.setIsQualified(isQualified);
                memberDetails.setBankAccount(bankAccount);
                memberDetails.setNriOrNrk(nriOrNrk);
                memberDetails.setBankType(bankType);
                memberDetails.setPension(pension);
                memberDetails.setDiseases(disease);
                memberDetails.setDisability(disability);

                if (selectedPosition == -1) {
                    data.getMemberDetails().add(memberDetails);
                } else {
                    data.getMemberDetails().set(selectedPosition, memberDetails);
                }
                saveAssetDetails(data);
            }
        } else {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                ArrayList<BuildingAssets.MemberDetails> memberDetails;
                if (data.getMemberDetails() == null) {
                    memberDetails = new ArrayList<>();
                } else {
                    memberDetails = new ArrayList<>(data.getMemberDetails());
                }
                BuildingAssets.MemberDetails member;
                if (selectedPosition != -1 && memberDetails.size() > selectedPosition) {
                    member = memberDetails.get(selectedPosition);
                    member.setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                } else {
                    member = new BuildingAssets.MemberDetails();
                    member.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                    if (data.getPropertyDetails() != null) {
                        member.setProperty(data.getPropertyDetails().getPk());
                    }
                }
                member.setFirstname(firstName);
                member.setLastname(lastName);
                member.setGender(Integer.parseInt(gender));
                member.setAge(Integer.parseInt(age));
                member.setOccuppation(Integer.parseInt(memberOccupation));
                member.setBloodGroup(Integer.parseInt(bloodGroup));
                member.setDateOfBirth(memberDOB);
                member.setReligion(Integer.parseInt(religion));
                member.setCaste(Integer.parseInt(caste));
                member.setEducationCategory(Integer.parseInt(educationCategory));
                member.setEducation(Integer.parseInt(education));
                member.setMaritalStatus(Integer.parseInt(maritalStatus));
                member.setIsCoolieWage(isCoolieWage);
                member.setIsAlive(isAlive);
                member.setIsQualified(isQualified);
                member.setBankAccount(bankAccount);
                member.setNriOrNrk(nriOrNrk);
                member.setBankType(bankType);
                member.setPension(pension);
                member.setDiseases(disease);
                member.setDisability(disability);

                if (selectedPosition == -1) {
                    memberDetails.add(member);
                } else {
                    memberDetails.set(selectedPosition, member);
                }
                FetchAssetDataResponse.Data newData = new FetchAssetDataResponse.Data();
                newData.setMemberDetails(memberDetails);
                FetchAssetDataResponse.Data disabilityData = new FetchAssetDataResponse.Data();

                getCompositeDisposable()
                        .add(getInteractor()
                                .saveAssetDetails(newData)
                                .subscribeOn(getSchedulerProvider().io())
                                .observeOn(getSchedulerProvider().ui())
                                .flatMap((Function<FetchAssetDataResponse, ObservableSource<FetchAssetDataResponse>>) response -> {
                                    if (!isViewAttached()) {
                                        return Observable.empty();
                                    }
                                    if (AppCacheData.getOurInstance().getBuildingAssetData() != null
                                            && response.getData() != null && response.getData().getMemberDetails() != null) {
                                        setMemberData(response);
                                    } else {
                                        return Observable.empty();
                                    }
                                    return Observable.just(response);
                                })
                                .subscribeOn(getSchedulerProvider().io())
                                .observeOn(getSchedulerProvider().io())
                                .flatMap((Function<FetchAssetDataResponse, ObservableSource<FetchAssetDataResponse>>) response -> {
                                    if (response.getData().getMemberDetails() != null &&
                                            response.getData().getMemberDetails().size() > 0) {
                                        long memberId = response.getData().getMemberDetails().get(0).getPk();
                                        for (int i = 0; i < disability.size(); i++) {
                                            disability.get(i).setMember(memberId);
                                        }
                                    } else {
                                        return Observable.empty();
                                    }
                                    disabilityData.setDisability(disability);
                                    return getInteractor().saveAssetDetails(disabilityData);
                                })
                                .subscribeOn(getSchedulerProvider().io())
                                .observeOn(getSchedulerProvider().ui())
                                .doOnSubscribe(disposable -> showProgressDialog())
                                .doFinally(this::hideProgressDialog)
                                .doOnError(this::showApiCallErrors)
                                .doOnNext(response -> {
                                    if (!isViewAttached()) {
                                        return;
                                    }
                                    if (isResponseSuccess(response)) {
                                        if (AppCacheData.getOurInstance().getBuildingAssetData() != null
                                                && response.getData() != null && response.getData().getDisability() != null) {
                                            ArrayList<BuildingAssets.MemberDetails> memDetails = AppCacheData.getOurInstance().getBuildingAssetData().getMemberDetails();
                                            if (memDetails == null) {
                                                memDetails = new ArrayList<>();
                                            }
                                            if (selectedPosition == -1 && memDetails.size() > 0) {
                                                memDetails.get(memDetails.size() - 1).setDisability(response.getData().getDisability());
                                                AppCacheData.getOurInstance().getBuildingAssetData().setMemberDetails(memberDetails);
                                            } else if (memberDetails.size() > selectedPosition) {
                                                memberDetails.get(selectedPosition).setDisability(response.getData().getDisability());
                                                AppCacheData.getOurInstance().getBuildingAssetData().setMemberDetails(memberDetails);
                                            }
                                        }
                                        getMvpView().showToast(response.getMessage());
                                        getMvpView().hideProgressDialog();
                                        getMvpView().onAddOrUpdateSuccess();
                                    }
                                }).subscribe());
            }
        }
    }

    @Override
    public void onSaveAssetSuccess(FetchAssetDataResponse response) {
        if (!isViewAttached()) {
            return;
        }
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null
                && response.getData() != null && response.getData().getMemberDetails() != null) {
            setMemberData(response);
        }
        getMvpView().showToast(response.getMessage());
        getMvpView().hideProgressDialog();
        getMvpView().onAddOrUpdateSuccess();
    }


    private void setMemberData(FetchAssetDataResponse response) {
        ArrayList<BuildingAssets.MemberDetails> memberDetails = AppCacheData.getOurInstance().getBuildingAssetData().getMemberDetails();
        if (memberDetails == null) {
            memberDetails = new ArrayList<>();
        }
        if (selectedPosition == -1 && response.getData().getMemberDetails().size() > 0) {
            memberDetails.add(response.getData().getMemberDetails().get(0));
            AppCacheData.getOurInstance().getBuildingAssetData().setMemberDetails(memberDetails);
        } else if (memberDetails.size() > selectedPosition && response.getData().getMemberDetails().size() > 0) {
            memberDetails.remove(selectedPosition);
            memberDetails.add(selectedPosition, response.getData().getMemberDetails().get(0));
            AppCacheData.getOurInstance().getBuildingAssetData().setMemberDetails(memberDetails);
        }
    }
}
