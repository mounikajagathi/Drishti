package in.ults.ipms.ui.utility.busbay;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class BusBayDetailsInteractor extends BaseInteractor implements IBusBayDetailsInteractor {

    @Inject
    public BusBayDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
