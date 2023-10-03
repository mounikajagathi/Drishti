package in.ults.ipms.ui.utility.canal;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class CanalDetailsInteractor extends BaseInteractor implements ICanalDetailsInteractor {

    @Inject
    public CanalDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
