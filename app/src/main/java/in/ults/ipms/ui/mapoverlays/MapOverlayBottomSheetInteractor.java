
package in.ults.ipms.ui.mapoverlays;


import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 20/07/17.
 */

public class MapOverlayBottomSheetInteractor extends BaseInteractor
        implements IMapOverlayBottomSheetInteractor {

    @Inject
    public MapOverlayBottomSheetInteractor(DataManager dataManager) {
        super(dataManager);
    }

}

