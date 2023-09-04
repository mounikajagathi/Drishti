
package in.ults.ipms.ui.dashboard.settings;


import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 20/07/17.
 */

public class SettingsBottomSheetInteractor extends BaseInteractor
        implements ISettingsBottomSheetInteractor {

    @Inject
    public SettingsBottomSheetInteractor(DataManager dataManager) {
        super(dataManager);
    }

}

