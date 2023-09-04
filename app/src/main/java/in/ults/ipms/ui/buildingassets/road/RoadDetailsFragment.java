package in.ults.ipms.ui.buildingassets.road;

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
import in.ults.ipms.databinding.FragmentRoadDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseFragment;


public class RoadDetailsFragment extends BaseFragment<FragmentRoadDetailsBinding> implements IRoadDetailsView{

    public static final String TAG = RoadDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_NEAR_ROAD = 1;
    public static final int ERROR_TYPE_ROAD_TYPE = 2;
    public static final int ERROR_TYPE_ROAD_WIDTH = 3;

    @Inject
    IRoadDetailsPresenter<IRoadDetailsView, IRoadDetailsInteractor> presenter;

    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.RoadTypes> roadTypesAdapter;
    CommonSpinnerAdapter<BuildingAssetSpinnerResponse.RoadWidths> roadWidthsAdapter;

    public static RoadDetailsFragment newInstance() {
        return new RoadDetailsFragment();
    }

    @Override
    protected FragmentRoadDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, boolean attachToParent) {
        return FragmentRoadDetailsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.road_details_title);
    }

    @Override
    protected void initInjector() { getFragmentComponent().inject(this);

    }

    @Override
    protected void attachPresenter() { presenter.onAttach(this);

    }

    @Override
    protected void detachPresenter() { presenter.onDetach();

    }

    @Override
    protected void init(View view){
        getViewBinding().btnRoadDetails.setOnClickListener(v -> submitOnClick());
        roadTypesAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srRoadType, AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getRoadTypes());
        roadWidthsAdapter = CommonSpinnerAdapter.setAdapter(getBaseActivity(), getViewBinding().srRoadWidth,AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getRoadWidths());

//        if(AppCacheData.getOurInstance().isAssetUpdate()){
            setEditData();//edit 
//        }
    }

    void submitOnClick() {
        String nearRoad = Objects.requireNonNull(getViewBinding().etRoadNearRoad.getText()).toString().trim();
        String roadType = (String) getViewBinding().srRoadType.getTag();
        String roadWidth = (String) getViewBinding().srRoadWidth.getTag();
        presenter.validateData(nearRoad,roadType,roadWidth);
    }

    public void showRoadDetailsFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_NEAR_ROAD:
                getViewBinding().layoutRoadNearRoad.setError(error);
                getViewBinding().layoutRoadNearRoad.requestFocus();
                break;
            case ERROR_TYPE_ROAD_TYPE:
                getViewBinding().layoutRoadType.setError(error);
                getViewBinding().layoutRoadType.requestFocus();
                break;
            case ERROR_TYPE_ROAD_WIDTH:
                getViewBinding().layoutRoadWidth.setError(error);
                getViewBinding().layoutRoadWidth.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutRoadNearRoad.setErrorEnabled(false);
        getViewBinding().layoutRoadType.setErrorEnabled(false);
        getViewBinding().layoutRoadWidth.setErrorEnabled(false);
    }

    public void setEditData() {
        if(AppCacheData.getOurInstance().getBuildingAssetData()!=null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails()!=null) {
            BuildingAssets.PropertyDetails propertyDetails = AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails();
            getViewBinding().etRoadNearRoad.setText(propertyDetails.getNearRoad());
            roadTypesAdapter.setContent(String.valueOf(propertyDetails.getRoadType()));
            roadWidthsAdapter.setContent(String.valueOf(propertyDetails.getRoadWidth()));
        }
    }

    @Override
    public void onAddOrUpdateSuccess() {
        AppCacheData.getOurInstance().setBuildingAssetPropertyAdded(true);
        getBaseActivity().launchBuildAssetHome(false,false);
    }

}
