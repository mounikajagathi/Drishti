
package in.ults.ipms.ui.dashboard.basemap;


import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 20/07/17.
 */

public class BaseMapBottomSheetInteractor extends BaseInteractor
        implements IBaseMapBottomSheetInteractor {

    @Inject
    public BaseMapBottomSheetInteractor(DataManager dataManager) {
        super(dataManager);
    }

}

