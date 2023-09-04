package in.ults.ipms.ui.waterbody.home;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class WaterBodyHomeInteractor extends BaseInteractor implements IWaterBodyHomeInteractor {

    @Inject
    public WaterBodyHomeInteractor(DataManager dataManager) {
        super(dataManager);
    }

}
