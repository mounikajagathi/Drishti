package in.ults.ipms.ui.utility.park;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class ParkDetailsInteractor extends BaseInteractor implements IParkDetailsInteractor {

    @Inject
    public ParkDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
