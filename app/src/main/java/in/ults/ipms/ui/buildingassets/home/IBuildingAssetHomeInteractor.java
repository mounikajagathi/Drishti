package in.ults.ipms.ui.buildingassets.home;

import in.ults.ipms.data.network.model.response.BuildingAssetSpinnerResponse;
import in.ults.ipms.ui.base.IBaseInteractor;
import io.reactivex.Observable;

public interface IBuildingAssetHomeInteractor extends IBaseInteractor {
    Observable<BuildingAssetSpinnerResponse> getBuildingAssetSpinnerData();
}
