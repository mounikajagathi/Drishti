package in.ults.ipms.ui.dashboard.measure;


import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 20/07/17.
 */

public class MeasureDialogInteractor extends BaseInteractor
        implements IMeasureDialogInteractor {

    @Inject
    public MeasureDialogInteractor(DataManager dataManager) {
        super(dataManager);
    }

}

