package in.ults.ipms.ui.otherassets;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.data.network.model.response.OtherAssetsSpinnerResponse;
import in.ults.ipms.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class OtherAssetsInteractor extends BaseInteractor implements IOtherAssetsInteractor {

    @Inject
    public OtherAssetsInteractor(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<OtherAssetsSpinnerResponse> getOtherAssetsSpinnerResponse() {
        return getDataManager().getOtherAssetsSpinnerResponse(getDataManager().getAccessToken());
    }
}
