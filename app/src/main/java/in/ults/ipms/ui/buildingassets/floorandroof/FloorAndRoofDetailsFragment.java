package in.ults.ipms.ui.buildingassets.floorandroof;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.FloorAreaAdapter;
import in.ults.ipms.adapters.FloorTypeAdapter;
import in.ults.ipms.adapters.RoofTypeAdapter;
import in.ults.ipms.data.network.model.request.DeleteAssetDataRequest;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.databinding.FragmentFloorRoofDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseFragment;

/**
 * Created by AmalB on 6/22/2021.
 */

public class FloorAndRoofDetailsFragment extends BaseFragment<FragmentFloorRoofDetailsBinding> implements IFloorAndRoofDetailsView {

    public static final String TAG = FloorAndRoofDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_FLOOR_TYPE = 1;
    public static final int REMOVE_TYPE_FLOOR_TYPE = 1;
    public static final int REMOVE_TYPE_FLOOR_AREA = 2;
    public static final int REMOVE_TYPE_ROOF_TYPE = 3;


    @Inject
    IFloorAndRoofDetailsPresenter<IFloorAndRoofDetailsView, IFloorAndRoofDetailsInteractor> presenter;

    @Inject
    FloorTypeAdapter floorTypesAdapter;

    @Inject
    FloorAreaAdapter floorAreaAdapter;

    @Inject
    RoofTypeAdapter roofTypeAdapter;


    public static FloorAndRoofDetailsFragment newInstance() {
        return new FloorAndRoofDetailsFragment();
    }

    @Override
    protected FragmentFloorRoofDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentFloorRoofDetailsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.floor_roof_details_title);
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
        getViewBinding().btnSubmit.setOnClickListener(v -> submitOnClick());
        floorTypesAdapter.setLocalDataSet(AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getFloorTypes());
        floorTypesAdapter.setRemoveListener((position, pk) -> {
            DeleteAssetDataRequest request = new DeleteAssetDataRequest();
            request.setFloorTypes(new DeleteAssetDataRequest.AssetDeleteDetails(pk));
            presenter.deleteAssetDetails(REMOVE_TYPE_FLOOR_TYPE, request, position);
        });
        getViewBinding().rvFloorType.setAdapter(floorTypesAdapter);
        floorAreaAdapter.setFloorCategoryList(AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getFloorNumbers());
        floorAreaAdapter.setRemoveListener((position, pk) -> {
            DeleteAssetDataRequest request = new DeleteAssetDataRequest();
            request.setFloorArea(new DeleteAssetDataRequest.AssetDeleteDetails(pk));
            presenter.deleteAssetDetails(REMOVE_TYPE_FLOOR_AREA, request, position);
        });
        getViewBinding().rvFloorArea.setAdapter(floorAreaAdapter);
        roofTypeAdapter.setRoofCategoryList(AppCacheData.getOurInstance().getBuildingAssetSpinnerData().getRoofCategories());
        roofTypeAdapter.setRemoveListener((position, pk) -> {
            DeleteAssetDataRequest request = new DeleteAssetDataRequest();
            request.setRoofTypes(new DeleteAssetDataRequest.AssetDeleteDetails(pk));
            presenter.deleteAssetDetails(REMOVE_TYPE_ROOF_TYPE, request, position);
        });
        getViewBinding().rvRoofType.setAdapter(roofTypeAdapter);
        setEditData();
    }


    void submitOnClick() {
        ArrayList<BuildingAssets.FloorType> floorType = floorTypesAdapter.getSelectedId();
        presenter.validateData(floorType, floorAreaAdapter, roofTypeAdapter);

    }


    @Override
    public void showFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_FLOOR_TYPE:
                showToast(error);
                break;
            default:
                break;
        }

    }

    @Override
    public void clearErrors() {

    }

    public void setEditData() {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
            ArrayList<BuildingAssets.FloorType> floorTypes = AppCacheData.getOurInstance().getBuildingAssetData().getFloorTypes();
            if (floorTypes != null) {
                floorTypesAdapter.setSelectedId(floorTypes);
            }

            ArrayList<BuildingAssets.FloorArea> floorArea = AppCacheData.getOurInstance().getBuildingAssetData().getFloorArea();
            if (floorArea != null) {
                floorAreaAdapter.setLocalDataSet(floorArea);
            }
            ArrayList<BuildingAssets.RoofType> roofType = AppCacheData.getOurInstance().getBuildingAssetData().getRoofTypes();
            if (roofType != null) {
                roofTypeAdapter.setLocalDataSet(roofType);
            }
        }

    }

    @Override
    public void onAddOrUpdateSuccess() {
        getBaseActivity().launchBuildAssetHome(false, false);
    }

    @Override
    public void removeDataFromList(int type, int position) {
        if (type == REMOVE_TYPE_FLOOR_TYPE) {
            floorTypesAdapter.removeListData(position);
        } else if (type == REMOVE_TYPE_FLOOR_AREA) {
            floorAreaAdapter.removeListData(position);
        } else if (type == REMOVE_TYPE_ROOF_TYPE) {
            roofTypeAdapter.removeListData(position);
        }
    }
}
