package in.ults.ipms.ui.buildingassets.taxlist;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class TaxListInteractor extends BaseInteractor implements ITaxListInteractor {

    @Inject
    public TaxListInteractor(DataManager dataManager) {
        super(dataManager);
    }

}
