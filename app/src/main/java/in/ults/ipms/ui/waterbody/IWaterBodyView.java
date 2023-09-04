package in.ults.ipms.ui.waterbody;

import in.ults.ipms.ui.base.IBaseView;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public interface IWaterBodyView extends IBaseView {

    void onWaterBodyInternetError();
    void onWaterBodyAPIError();
    void onWaterBodyDataSuccess();

}
