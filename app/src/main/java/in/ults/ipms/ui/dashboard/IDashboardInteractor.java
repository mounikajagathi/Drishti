package in.ults.ipms.ui.dashboard;

import java.util.ArrayList;

import in.ults.ipms.data.network.model.request.FeatureDataRequest;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.data.network.model.response.FeatureDataResponse;
import in.ults.ipms.ui.base.IBaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public interface IDashboardInteractor extends IBaseInteractor {
    Observable<DashboardResponse> getDashboardDetails();
    Observable<FeatureDataResponse> getFeatureInfoData(ArrayList<FeatureDataRequest> dataRequests);
}
