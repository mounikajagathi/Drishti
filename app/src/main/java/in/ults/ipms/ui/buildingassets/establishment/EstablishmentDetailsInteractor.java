package in.ults.ipms.ui.buildingassets.establishment;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class EstablishmentDetailsInteractor extends BaseInteractor implements IEstablishmentDetailsInteractor{

    @Inject
    public EstablishmentDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
