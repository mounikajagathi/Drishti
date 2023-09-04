package in.ults.ipms.ui.buildingassets.tax;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class TaxDetailsInteractor extends BaseInteractor implements ITaxDetailsInteractor {

    @Inject
    public TaxDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
