package in.ults.ipms.ui.utility.home;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class UtilityHomeInteractor extends BaseInteractor implements IUtilityHomeInteractor {

    @Inject
    public UtilityHomeInteractor(DataManager dataManager) {
        super(dataManager);
    }

}
