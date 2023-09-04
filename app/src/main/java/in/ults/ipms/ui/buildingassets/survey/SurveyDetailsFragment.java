package in.ults.ipms.ui.buildingassets.survey;

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
import in.ults.ipms.databinding.FragmentSurveyDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseFragment;

public class SurveyDetailsFragment extends BaseFragment<FragmentSurveyDetailsBinding> implements ISurveyDetailsView {

    public static final String TAG = SurveyDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_INFORMED_BY = 1;
    public static final int ERROR_TYPE_NEIGHBOUR_NAME = 2;
    public static final int ERROR_TYPE_SURVEYOR = 3;
    public static final int ERROR_TYPE_REMARKS = 4;
    public static final int ERROR_TYPE_OLD_PRO_ID_REMARK = 5;
    public static final int ERROR_TYPE_NEW_PRO_ID_REMARK = 6;
    public static final int ERROR_TYPE_COOPERATION = 7;
    public static final int ERROR_TYPE_OWNERSHIP_CHANGE = 8;

    @Inject
    ISurveyDetailsPresenter<ISurveyDetailsView, ISurveyDetailsInteractor> presenter;

    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.OwnershipChange> ownershipChangeAdapter;

    public static SurveyDetailsFragment newInstance() {
        return new SurveyDetailsFragment();
    }

    @Override
    protected  FragmentSurveyDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentSurveyDetailsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.survey_details_title);
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
        getViewBinding().btnSurveyDetails.setOnClickListener(v -> submitOnClick());
        ownershipChangeAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srSurveyOwnershipChange, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getOwnershipChange());

//        if(AppCacheData.getOurInstance().isAssetUpdate()){
            setEditData();
//        }
    }

    void submitOnClick() {
        String informedBy = Objects.requireNonNull(getViewBinding().etSurveyInformedBy.getText()).toString().trim();
        String neighbourName = Objects.requireNonNull(getViewBinding().etSurveyNeighbourName.getText()).toString().trim();
        String surveyor = Objects.requireNonNull(getViewBinding().etSurveySurveyor.getText()).toString().trim();
        String remarks = Objects.requireNonNull(getViewBinding().etSurveyRemarks.getText()).toString().trim();
        String oldProIdRemark = Objects.requireNonNull(getViewBinding().etSurveyOldPropertyRemarks.getText()).toString().trim();
        String newProIdRemark = Objects.requireNonNull(getViewBinding().etSurveyNewPropertyRemarks.getText()).toString().trim();
        String cooperation = Objects.requireNonNull(getViewBinding().etSurveyCooperation.getText()).toString().trim();
        String ownershipChange = (String) getViewBinding().srSurveyOwnershipChange.getTag();

        presenter.validateData(informedBy,neighbourName,remarks,surveyor,oldProIdRemark,newProIdRemark,cooperation,ownershipChange);
    }

    @Override
    public void showSurveyDetailsFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_INFORMED_BY:
                getViewBinding().layoutSurveyInformedBy.setError(error);
                getViewBinding().layoutSurveyInformedBy.requestFocus();
                break;
            case ERROR_TYPE_NEIGHBOUR_NAME:
                getViewBinding().layoutSurveyNeighbourName.setError(error);
                getViewBinding().layoutSurveyNeighbourName.requestFocus();
                break;
            case ERROR_TYPE_SURVEYOR:
                getViewBinding().layoutSurveySurveyor.setError(error);
                getViewBinding().layoutSurveySurveyor.requestFocus();
                break;
            case ERROR_TYPE_REMARKS:
                getViewBinding().layoutSurveyRemarks.setError(error);
                getViewBinding().layoutSurveyRemarks.requestFocus();
                break;
            case ERROR_TYPE_OLD_PRO_ID_REMARK:
                getViewBinding().layoutSurveyOldPropertyRemarks.setError(error);
                getViewBinding().layoutSurveyOldPropertyRemarks.requestFocus();
                break;
            case ERROR_TYPE_NEW_PRO_ID_REMARK:
                getViewBinding().layoutSurveyNewPropertyRemarks.setError(error);
                getViewBinding().layoutSurveyNewPropertyRemarks.requestFocus();
                break;
            case ERROR_TYPE_COOPERATION:
                getViewBinding().layoutSurveyCooperation.setError(error);
                getViewBinding().layoutSurveyCooperation.requestFocus();
                break;
            case ERROR_TYPE_OWNERSHIP_CHANGE:
                getViewBinding().layoutSurveyOwnershipChange.setError(error);
                getViewBinding().layoutSurveyOwnershipChange.requestFocus();
                break;

            default:
                break;
        }
    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutSurveyInformedBy.setErrorEnabled(false);
        getViewBinding().layoutSurveyNeighbourName.setErrorEnabled(false);
        getViewBinding().layoutSurveySurveyor.setErrorEnabled(false);
        getViewBinding().layoutSurveyRemarks.setErrorEnabled(false);
        getViewBinding().layoutSurveyNewPropertyRemarks.setErrorEnabled(false);
        getViewBinding().layoutSurveyOldPropertyRemarks.setErrorEnabled(false);
        getViewBinding().layoutSurveyCooperation.setErrorEnabled(false);
        getViewBinding().layoutSurveyOwnershipChange.setErrorEnabled(false);
    }

    public void setEditData() {
        if(AppCacheData.getOurInstance().getBuildingAssetData()!=null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getSurveyDetails()!=null) {
            BuildingAssets.SurveyDetails surveyDetails = AppCacheData.getOurInstance().getBuildingAssetData().getSurveyDetails();
            getViewBinding().etSurveyInformedBy.setText(surveyDetails.getInformedBy());
            getViewBinding().etSurveyNeighbourName.setText(surveyDetails.getNeighbourName());
            getViewBinding().etSurveySurveyor.setText(surveyDetails.getSurveyor());
            getViewBinding().etSurveyRemarks.setText(surveyDetails.getRemark());
            getViewBinding().etSurveyOldPropertyRemarks.setText(surveyDetails.getOldPropertyRmk());
            getViewBinding().etSurveyNewPropertyRemarks.setText(surveyDetails.getNewPropertyRmk());
            getViewBinding().etSurveyCooperation.setText(surveyDetails.getCooperation());
            ownershipChangeAdapter.setContent(surveyDetails.getOwnershipChange());
        }
    }

    @Override
    public void onAddOrUpdateSuccess() {
        getBaseActivity().launchBuildAssetHome(false,false);
    }
}
