package in.ults.ipms.ui.waterbody;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.data.network.model.response.WaterBodySpinnerResponse;
import in.ults.ipms.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class WaterBodyInteractor extends BaseInteractor implements IWaterBodyInteractor {

    @Inject
    public WaterBodyInteractor(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<WaterBodySpinnerResponse> getWaterBodySpinnerResponse() {
        return getDataManager().getWaterBodySpinnerResponse(getDataManager().getAccessToken());
    }
}
