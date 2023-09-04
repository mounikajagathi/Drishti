package in.ults.ipms.ui.buildingassets;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class BuildingAssetsInteractor extends BaseInteractor implements IBuildingAssetsInteractor {

    @Inject
    public BuildingAssetsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
