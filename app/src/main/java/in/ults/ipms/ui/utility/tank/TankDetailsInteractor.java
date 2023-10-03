package in.ults.ipms.ui.utility.tank;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class TankDetailsInteractor extends BaseInteractor implements ITankDetailsInteractor {

    @Inject
    public TankDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
