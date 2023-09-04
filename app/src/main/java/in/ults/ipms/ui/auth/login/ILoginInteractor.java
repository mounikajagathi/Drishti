package in.ults.ipms.ui.auth.login;

import in.ults.ipms.data.network.model.request.LoginRequest;
import in.ults.ipms.data.network.model.response.LoginResponse;
import in.ults.ipms.ui.base.IBaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public interface ILoginInteractor extends IBaseInteractor {
    Observable<LoginResponse> loginUser(LoginRequest request);
}
