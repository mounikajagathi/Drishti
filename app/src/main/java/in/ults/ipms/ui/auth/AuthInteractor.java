package in.ults.ipms.ui.auth;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class AuthInteractor extends BaseInteractor implements IAuthInteractor {

    @Inject
    public AuthInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
