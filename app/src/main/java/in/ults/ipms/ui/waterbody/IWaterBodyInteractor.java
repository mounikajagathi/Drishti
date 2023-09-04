package in.ults.ipms.ui.waterbody;

import in.ults.ipms.data.network.model.response.WaterBodySpinnerResponse;
import in.ults.ipms.ui.base.IBaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public interface IWaterBodyInteractor extends IBaseInteractor {
    
    Observable<WaterBodySpinnerResponse> getWaterBodySpinnerResponse();

}
