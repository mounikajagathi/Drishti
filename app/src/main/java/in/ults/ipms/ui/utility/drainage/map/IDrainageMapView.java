package in.ults.ipms.ui.utility.drainage.map;

import in.ults.ipms.ui.base.IBaseView;

/**
 * Created by AmalB on 17/06/21.
 */

public interface IDrainageMapView extends IBaseView {

    void setBaseMapAndDefaultCentre();
    void onRoadInternetError();
    void onRoadAPIError();

}
