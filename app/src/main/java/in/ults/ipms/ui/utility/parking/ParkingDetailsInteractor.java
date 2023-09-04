package in.ults.ipms.ui.utility.parking;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class ParkingDetailsInteractor extends BaseInteractor implements IParkingDetailsInteractor {

    @Inject
    public ParkingDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
