package in.ults.ipms.ui.auth.login;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.data.network.model.request.LoginRequest;
import in.ults.ipms.data.network.model.response.LoginResponse;
import in.ults.ipms.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class LoginInteractor extends BaseInteractor implements ILoginInteractor {

    @Inject
    public LoginInteractor(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<LoginResponse> loginUser(LoginRequest request) {
        return getDataManager().loginUser(request);
    }


}
