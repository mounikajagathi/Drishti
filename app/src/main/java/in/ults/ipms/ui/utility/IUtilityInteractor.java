package in.ults.ipms.ui.utility;

import in.ults.ipms.data.network.model.response.UtilitySpinnerResponse;
import in.ults.ipms.ui.base.IBaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public interface IUtilityInteractor extends IBaseInteractor {

    Observable<UtilitySpinnerResponse> getUtilitySpinnerResponse();

}
