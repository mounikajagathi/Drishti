package in.ults.ipms.ui.utility.busstop;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class BusStopDetailsInteractor extends BaseInteractor implements IBusStopDetailsInteractor {

    @Inject
    public BusStopDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
