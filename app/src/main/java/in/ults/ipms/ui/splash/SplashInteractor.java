package in.ults.ipms.ui.splash;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class SplashInteractor extends BaseInteractor implements ISplashInteractor {

    @Inject
    public SplashInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
