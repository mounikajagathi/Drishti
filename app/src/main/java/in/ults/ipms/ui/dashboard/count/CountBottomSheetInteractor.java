
package in.ults.ipms.ui.dashboard.count;


import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 20/07/17.
 */

public class CountBottomSheetInteractor extends BaseInteractor
        implements ICountBottomSheetInteractor {

    @Inject
    public CountBottomSheetInteractor(DataManager dataManager) {
        super(dataManager);
    }

}

