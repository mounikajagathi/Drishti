package in.ults.ipms.ui.utility.mobiletower;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class MobileTowerDetailsInteractor extends BaseInteractor implements IMobileTowerDetailsInteractor {

    @Inject
    public MobileTowerDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
