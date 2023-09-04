package in.ults.ipms.ui.buildingassets.otherproperty;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class PropertyOtherDetailsInteractor extends BaseInteractor implements IPropertyOtherDetailsInteractor {

    @Inject
    public PropertyOtherDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
