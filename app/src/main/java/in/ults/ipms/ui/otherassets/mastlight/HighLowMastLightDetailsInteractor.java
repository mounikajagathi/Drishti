package in.ults.ipms.ui.otherassets.mastlight;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class HighLowMastLightDetailsInteractor extends BaseInteractor implements IHighLowMastLightDetailsInteractor {

    @Inject
    public HighLowMastLightDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
