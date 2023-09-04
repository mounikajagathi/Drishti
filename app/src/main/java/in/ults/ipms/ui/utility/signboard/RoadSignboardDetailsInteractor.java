package in.ults.ipms.ui.utility.signboard;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class RoadSignboardDetailsInteractor extends BaseInteractor implements IRoadSignboardDetailsInteractor {

    @Inject
    public RoadSignboardDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
