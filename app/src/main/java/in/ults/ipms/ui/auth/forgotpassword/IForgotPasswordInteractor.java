package in.ults.ipms.ui.auth.forgotpassword;

import in.ults.ipms.data.network.model.base.BaseResponse;
import in.ults.ipms.data.network.model.request.ForgotPasswordRequest;
import in.ults.ipms.ui.base.IBaseInteractor;
import io.reactivex.Observable;

public interface IForgotPasswordInteractor  extends IBaseInteractor {
    Observable<BaseResponse> resetPassword(ForgotPasswordRequest request);
}
