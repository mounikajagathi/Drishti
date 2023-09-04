package in.ults.ipms.ui.buildingassets.survey;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class SurveyDetailsPresenter <V extends ISurveyDetailsView, I extends ISurveyDetailsInteractor>
        extends BasePresenter<V, I> implements ISurveyDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public SurveyDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    @Override
    public void validateData(String informedBy,String neighbourName,String remarks,String surveyor,String oldProIdRemark,String newProIdRemark,String cooperation,String ownershipChange) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(informedBy)) {
            getMvpView().showSurveyDetailsFieldError(SurveyDetailsFragment.ERROR_TYPE_INFORMED_BY,baseActivity.getResources().getString(R.string.err_informed_by));
            return;
        }
        if (CommonUtils.isNullString(neighbourName)) {
            getMvpView().showSurveyDetailsFieldError(SurveyDetailsFragment.ERROR_TYPE_NEIGHBOUR_NAME,baseActivity.getResources().getString(R.string.err_neighbour_name));
            return;
        }
        if (CommonUtils.isNullString(surveyor)) {
            getMvpView().showSurveyDetailsFieldError(SurveyDetailsFragment.ERROR_TYPE_SURVEYOR,baseActivity.getResources().getString(R.string.err_surveyor));
            return;
        }
        if (CommonUtils.isNullString(remarks)) {
            getMvpView().showSurveyDetailsFieldError(SurveyDetailsFragment.ERROR_TYPE_REMARKS,baseActivity.getResources().getString(R.string.err_remark));
            return;
        }
        if (CommonUtils.isNullString(oldProIdRemark)) {
            getMvpView().showSurveyDetailsFieldError(SurveyDetailsFragment.ERROR_TYPE_OLD_PRO_ID_REMARK,baseActivity.getResources().getString(R.string.err_old_pro_id_remark));
            return;
        }
        if (CommonUtils.isNullString(newProIdRemark)) {
            getMvpView().showSurveyDetailsFieldError(SurveyDetailsFragment.ERROR_TYPE_NEW_PRO_ID_REMARK,baseActivity.getResources().getString(R.string.err_new_pro_id_remark));
            return;
        }
        if (CommonUtils.isNullString(cooperation)) {
            getMvpView().showSurveyDetailsFieldError(SurveyDetailsFragment.ERROR_TYPE_COOPERATION,baseActivity.getResources().getString(R.string.err_cooperation));
            return;
        }
        if (CommonUtils.isNullString(ownershipChange)) {
            getMvpView().showSurveyDetailsFieldError(SurveyDetailsFragment.ERROR_TYPE_OWNERSHIP_CHANGE,baseActivity.getResources().getString(R.string.err_ownership_change));
            return;
        }

        savingSurveyDetails(informedBy,neighbourName,surveyor,remarks,oldProIdRemark,newProIdRemark,cooperation,ownershipChange);
    }

    public void savingSurveyDetails(String informedBy,String neighbourName,String surveyor,String remarks,String oldProIdRemark,String newProIdRemark,String cooperation,String ownershipChange){
        if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
                BuildingAssets.SurveyDetails surveyDetails = AppCacheData.getOurInstance().getBuildingAssetData().getSurveyDetails();
                data.setSurveyDetails(surveyDetails == null ? new BuildingAssets.SurveyDetails() : surveyDetails);
                data.getSurveyDetails().setInformedBy(informedBy);
                data.getSurveyDetails().setNeighbourName(neighbourName);
                data.getSurveyDetails().setSurveyor(surveyor);
                data.getSurveyDetails().setRemark(remarks);
                data.getSurveyDetails().setOldPropertyRmk(oldProIdRemark);
                data.getSurveyDetails().setNewPropertyRmk(newProIdRemark);
                data.getSurveyDetails().setCooperation(cooperation);
                data.getSurveyDetails().setOwnershipChange(ownershipChange);
                data.getSurveyDetails().setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                saveAssetDetails(data);
            }
        } else {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                BuildingAssets.SurveyDetails surveyDetails = data.getSurveyDetails();
                if (surveyDetails == null) {
                    surveyDetails = new BuildingAssets.SurveyDetails();
                    if (data.getPropertyDetails() != null) {
                        surveyDetails.setProperty(data.getPropertyDetails().getPk());
                    }
                    surveyDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                } else {
                    surveyDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                }
                surveyDetails.setInformedBy(informedBy);
                surveyDetails.setNeighbourName(neighbourName);
                surveyDetails.setSurveyor(surveyor);
                surveyDetails.setRemark(remarks);
                surveyDetails.setOldPropertyRmk(oldProIdRemark);
                surveyDetails.setNewPropertyRmk(newProIdRemark);
                surveyDetails.setCooperation(cooperation);
                surveyDetails.setOwnershipChange(ownershipChange);
                FetchAssetDataResponse.Data newData = new FetchAssetDataResponse.Data();
                newData.setSurveyDetails(surveyDetails);
                saveAssetDetails(newData);
            }
        }
    }

    @Override
    public void onSaveAssetSuccess(FetchAssetDataResponse response) {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showToast(response.getMessage());
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null && response.getData() != null) {
            AppCacheData.getOurInstance().getBuildingAssetData().setSurveyDetails(response.getData().getSurveyDetails());
        }
        getMvpView().onAddOrUpdateSuccess();
    }
}
