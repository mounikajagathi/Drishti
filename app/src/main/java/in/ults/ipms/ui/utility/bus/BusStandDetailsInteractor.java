package in.ults.ipms.ui.utility.bus;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class BusStandDetailsInteractor extends BaseInteractor implements IBusStandDetailsInteractor {

    @Inject
    public BusStandDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
