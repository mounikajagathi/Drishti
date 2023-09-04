package in.ults.ipms.ui.buildingassets.home;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.data.network.model.response.BuildingAssetSpinnerResponse;
import in.ults.ipms.ui.base.BaseInteractor;
import io.reactivex.Observable;

public class BuildingAssetHomeInteractor extends BaseInteractor implements IBuildingAssetHomeInteractor {

    @Inject
    public BuildingAssetHomeInteractor(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<BuildingAssetSpinnerResponse> getBuildingAssetSpinnerData() {
        return getDataManager().getBuildingAssetSpinnerData(getDataManager().getAccessToken());
    }
}
