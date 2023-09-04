
package in.ults.ipms.ui.dashboard.opacity;


import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 20/07/17.
 */

public class OpacityBottomSheetInteractor extends BaseInteractor
        implements IOpacityBottomSheetInteractor {

    @Inject
    public OpacityBottomSheetInteractor(DataManager dataManager) {
        super(dataManager);
    }

}

