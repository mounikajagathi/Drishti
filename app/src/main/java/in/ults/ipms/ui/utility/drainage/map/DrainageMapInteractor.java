package in.ults.ipms.ui.utility.drainage.map;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.data.network.model.response.UtilitySpinnerResponse;
import in.ults.ipms.ui.base.BaseInteractor;
import io.reactivex.Observable;

public class DrainageMapInteractor extends BaseInteractor implements IDrainageMapInteractor {

    @Inject
    public DrainageMapInteractor(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<UtilitySpinnerResponse> getUtilitySpinnerResponse() {
        return getDataManager().getUtilitySpinnerResponse(getDataManager().getAccessToken());
    }
}
