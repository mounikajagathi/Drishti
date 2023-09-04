
package in.ults.ipms.ui.dashboard.layers;


import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 20/07/17.
 */

public class LayersBottomSheetInteractor extends BaseInteractor
        implements ILayersBottomSheetInteractor {

    @Inject
    public LayersBottomSheetInteractor(DataManager dataManager) {
        super(dataManager);
    }

}

