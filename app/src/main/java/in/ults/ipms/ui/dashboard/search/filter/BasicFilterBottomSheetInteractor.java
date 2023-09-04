
package in.ults.ipms.ui.dashboard.search.filter;


import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 20/07/17.
 */

public class BasicFilterBottomSheetInteractor extends BaseInteractor
        implements IBasicFilterBottomSheetInteractor {

    @Inject
    public BasicFilterBottomSheetInteractor(DataManager dataManager) {
        super(dataManager);
    }

}

