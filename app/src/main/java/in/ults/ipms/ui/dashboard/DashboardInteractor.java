package in.ults.ipms.ui.dashboard;

import java.util.ArrayList;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.data.network.model.request.FeatureDataRequest;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.data.network.model.response.FeatureDataResponse;
import in.ults.ipms.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class DashboardInteractor extends BaseInteractor implements IDashboardInteractor {

    @Inject
    public DashboardInteractor(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<DashboardResponse> getDashboardDetails() {
        return getDataManager().dashboardDetails(getDataManager().getAccessToken());
    }


    @Override
    public Observable<FeatureDataResponse> getFeatureInfoData(ArrayList<FeatureDataRequest> dataRequests) {
        return getDataManager().getFeatureInfoData(getDataManager().getAccessToken(),dataRequests);
    }
}
