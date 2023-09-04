package in.ults.ipms.ui.buildingassets.tenant;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class TenantDetailsInteractor extends BaseInteractor implements ITenantDetailsInteractor {

    @Inject
    public TenantDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
