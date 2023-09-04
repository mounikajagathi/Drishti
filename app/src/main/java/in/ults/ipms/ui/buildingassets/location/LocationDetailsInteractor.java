package in.ults.ipms.ui.buildingassets.location;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class LocationDetailsInteractor extends BaseInteractor implements ILocationDetailsInteractor{

    @Inject
    public LocationDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
