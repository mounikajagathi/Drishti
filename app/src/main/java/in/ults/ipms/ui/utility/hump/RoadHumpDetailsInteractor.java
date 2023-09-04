package in.ults.ipms.ui.utility.hump;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class RoadHumpDetailsInteractor extends BaseInteractor implements IRoadHumpDetailsInteractor {

    @Inject
    public RoadHumpDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
