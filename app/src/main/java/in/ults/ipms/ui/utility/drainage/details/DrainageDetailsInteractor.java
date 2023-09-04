package in.ults.ipms.ui.utility.drainage.details;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class DrainageDetailsInteractor extends BaseInteractor implements IDrainageDetailsInteractor {

    @Inject
    public DrainageDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
