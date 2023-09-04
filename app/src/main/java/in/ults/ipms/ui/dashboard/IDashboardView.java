package in.ults.ipms.ui.dashboard;

import java.util.ArrayList;

import in.ults.ipms.data.network.model.response.FeatureDataResponse;
import in.ults.ipms.ui.base.IBaseView;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public interface IDashboardView extends IBaseView {

    void setBaseMapAndDefaultCentre();
    void onDashboardInternetError();
    void onDashboardAPIError();
    void onFeatureDetailsFetched(ArrayList<FeatureDataResponse.Data> data);

}
