package in.ults.ipms.ui.utility.drainage.map;

import in.ults.ipms.data.network.model.response.UtilitySpinnerResponse;
import in.ults.ipms.ui.base.IBaseInteractor;
import io.reactivex.Observable;

public interface IDrainageMapInteractor extends IBaseInteractor {

    Observable<UtilitySpinnerResponse> getUtilitySpinnerResponse();

}
