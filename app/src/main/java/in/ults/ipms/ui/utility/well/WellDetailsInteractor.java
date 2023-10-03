package in.ults.ipms.ui.utility.well;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class WellDetailsInteractor extends BaseInteractor implements IWellDetailsInteractor {

    @Inject
    public WellDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
