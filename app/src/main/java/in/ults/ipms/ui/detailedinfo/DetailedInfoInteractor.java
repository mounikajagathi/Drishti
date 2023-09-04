package in.ults.ipms.ui.detailedinfo;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class DetailedInfoInteractor extends BaseInteractor implements IDetailedInfoInteractor {

    @Inject
    public DetailedInfoInteractor(DataManager dataManager) {
        super(dataManager);
    }


}
