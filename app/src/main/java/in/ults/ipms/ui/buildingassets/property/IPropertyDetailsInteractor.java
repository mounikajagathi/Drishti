package in.ults.ipms.ui.buildingassets.property;

import in.ults.ipms.data.network.model.response.BuildingNameSpinnerResponse;
import in.ults.ipms.ui.base.IBaseInteractor;
import io.reactivex.Observable;

/**
 * Created by AmalB on 6/22/2021.
 */

public interface IPropertyDetailsInteractor extends IBaseInteractor {

    Observable<BuildingNameSpinnerResponse> getBuildingNameSpinnerData();

}
