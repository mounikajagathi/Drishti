package in.ults.ipms.ui.utility.road.map;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class RoadMapInteractor extends BaseInteractor implements IRoadMapInteractor {

    @Inject
    public RoadMapInteractor(DataManager dataManager) {
        super(dataManager);
    }

}
