package in.ults.ipms.ui.buildingassets.building;

import in.ults.ipms.ui.base.IBaseView;

/**
 * Created by AmalB on 6/22/2021.
 */

public interface IBuildingDetailsView extends IBaseView {
    void showBuildingDetailsFieldError(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess();
}
