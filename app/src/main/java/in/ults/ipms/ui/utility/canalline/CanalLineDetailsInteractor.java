package in.ults.ipms.ui.utility.canalline;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class CanalLineDetailsInteractor extends BaseInteractor implements ICanalLineDetailsInteractor {

    @Inject
    public CanalLineDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
