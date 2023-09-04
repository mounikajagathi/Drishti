package in.ults.ipms.ui.utility;

import in.ults.ipms.ui.base.IBaseView;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public interface IUtilityView extends IBaseView {

    void onUtilityInternetError();
    void onUtilityAPIError();
    void onUtilityDataSuccess();

}
