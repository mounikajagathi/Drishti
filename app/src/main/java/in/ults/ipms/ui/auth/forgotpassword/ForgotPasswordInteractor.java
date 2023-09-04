package in.ults.ipms.ui.auth.forgotpassword;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.data.network.model.base.BaseResponse;
import in.ults.ipms.data.network.model.request.ForgotPasswordRequest;
import in.ults.ipms.ui.base.BaseInteractor;
import io.reactivex.Observable;

public class ForgotPasswordInteractor extends BaseInteractor implements IForgotPasswordInteractor{

    @Inject
    public ForgotPasswordInteractor(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<BaseResponse> resetPassword(ForgotPasswordRequest request) {
        return getDataManager().resetPassword(request);
    }
}
