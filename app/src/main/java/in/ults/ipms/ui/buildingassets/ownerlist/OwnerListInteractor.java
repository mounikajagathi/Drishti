package in.ults.ipms.ui.buildingassets.ownerlist;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class OwnerListInteractor extends BaseInteractor implements IOwnerListInteractor {

    @Inject
    public OwnerListInteractor(DataManager dataManager) {
        super(dataManager);
    }

}
