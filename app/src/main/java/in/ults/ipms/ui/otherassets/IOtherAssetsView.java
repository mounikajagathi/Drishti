package in.ults.ipms.ui.otherassets;

import in.ults.ipms.ui.base.IBaseView;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public interface IOtherAssetsView extends IBaseView {

    void onOtherAssetsInternetError();
    void onOtherAssetsAPIError();
    void onOtherAssetsDataSuccess();

}
