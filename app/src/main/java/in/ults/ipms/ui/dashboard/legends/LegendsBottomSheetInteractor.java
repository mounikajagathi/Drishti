
package in.ults.ipms.ui.dashboard.legends;


import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 20/07/17.
 */

public class LegendsBottomSheetInteractor extends BaseInteractor
        implements ILegendsBottomSheetInteractor {

    @Inject
    public LegendsBottomSheetInteractor(DataManager dataManager) {
        super(dataManager);
    }

}

