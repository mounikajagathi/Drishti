package in.ults.ipms.ui.buildingassets.property;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.data.network.model.response.BuildingNameSpinnerResponse;
import in.ults.ipms.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by AmalB on 6/22/2021.
 */

public class PropertyDetailsInteractor extends BaseInteractor implements IPropertyDetailsInteractor {

    @Inject
    public PropertyDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<BuildingNameSpinnerResponse> getBuildingNameSpinnerData() {
        return getDataManager().getBuildingNameSpinnerData(getDataManager().getAccessToken());
    }


}
