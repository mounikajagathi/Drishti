package in.ults.ipms.ui.buildingassets.owner;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class OwnerDetailsInteractor extends BaseInteractor implements IOwnerDetailsInteractor {
    @Inject
    public OwnerDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
