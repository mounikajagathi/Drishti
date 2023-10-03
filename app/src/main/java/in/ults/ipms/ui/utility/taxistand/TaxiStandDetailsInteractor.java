package in.ults.ipms.ui.utility.taxistand;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class TaxiStandDetailsInteractor extends BaseInteractor implements ITaxiStandDetailsInteractor {

    @Inject
    public TaxiStandDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
