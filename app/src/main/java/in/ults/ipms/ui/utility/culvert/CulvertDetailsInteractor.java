package in.ults.ipms.ui.utility.culvert;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class CulvertDetailsInteractor extends BaseInteractor implements ICulvertDetailsInteractor {

    @Inject
    public CulvertDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
