package in.ults.ipms.ui.utility.bridge;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class BridgeDetailsInteractor extends BaseInteractor implements IBridgeDetailsInteractor {

    @Inject
    public BridgeDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
