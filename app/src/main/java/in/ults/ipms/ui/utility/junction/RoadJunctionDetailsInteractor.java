package in.ults.ipms.ui.utility.junction;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class RoadJunctionDetailsInteractor extends BaseInteractor implements IRoadJunctionDetailsInteractor {

    @Inject
    public RoadJunctionDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
