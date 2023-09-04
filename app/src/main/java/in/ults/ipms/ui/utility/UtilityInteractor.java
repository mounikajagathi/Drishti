package in.ults.ipms.ui.utility;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.data.network.model.response.UtilitySpinnerResponse;
import in.ults.ipms.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class UtilityInteractor extends BaseInteractor implements IUtilityInteractor {

    @Inject
    public UtilityInteractor(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<UtilitySpinnerResponse> getUtilitySpinnerResponse() {
        return getDataManager().getUtilitySpinnerResponse(getDataManager().getAccessToken());
    }
}
