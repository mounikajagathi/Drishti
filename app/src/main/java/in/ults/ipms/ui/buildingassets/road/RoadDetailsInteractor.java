package in.ults.ipms.ui.buildingassets.road;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class RoadDetailsInteractor extends BaseInteractor implements IRoadDetailsInteractor {

    @Inject
    public RoadDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }

}
