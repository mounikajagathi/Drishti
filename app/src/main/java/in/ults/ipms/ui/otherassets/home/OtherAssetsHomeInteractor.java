package in.ults.ipms.ui.otherassets.home;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class OtherAssetsHomeInteractor extends BaseInteractor implements IOtherAssetsHomeInteractor {

    @Inject
    public OtherAssetsHomeInteractor(DataManager dataManager) {
        super(dataManager);
    }

}
