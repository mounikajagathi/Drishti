package in.ults.ipms.ui.dashboard.gotolocation;


import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 20/07/17.
 */

public class GoToLocationDialogInteractor extends BaseInteractor
        implements IGoToLocationDialogInteractor {

    @Inject
    public GoToLocationDialogInteractor(DataManager dataManager) {
        super(dataManager);
    }

}

