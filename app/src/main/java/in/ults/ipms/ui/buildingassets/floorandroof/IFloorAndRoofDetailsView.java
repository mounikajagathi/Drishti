package in.ults.ipms.ui.buildingassets.floorandroof;

import in.ults.ipms.ui.base.IBaseView;

/**
 * Created by AmalB on 6/22/2021.
 */

public interface IFloorAndRoofDetailsView extends IBaseView {
    void showFieldError(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess();
    void removeDataFromList (int type, int position);

}
