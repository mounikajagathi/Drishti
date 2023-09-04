package in.ults.ipms.ui.buildingassets.building;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by AmalB on 6/22/2021.
 */

public class BuildingDetailsInteractor extends BaseInteractor implements IBuildingDetailsInteractor {

    @Inject
    public BuildingDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
