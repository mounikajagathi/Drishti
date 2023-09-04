package in.ults.ipms.ui.buildingassets.property;

import java.util.ArrayList;

import in.ults.ipms.data.network.model.response.BuildingNameSpinnerResponse;
import in.ults.ipms.ui.base.IBaseView;

/**
 * Created by AmalB on 6/22/2021.
 */

public interface IPropertyDetailsView extends IBaseView {
    void onBuildingNameLoaded(ArrayList<BuildingNameSpinnerResponse.BuildingName> buildingNames);
    void showPropertyDetailsFieldError(int errorType, String error);
    void clearErrors();
    void goToNext();
    void onAddOrUpdateSuccess();
}
