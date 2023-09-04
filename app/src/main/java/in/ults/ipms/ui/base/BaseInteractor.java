package in.ults.ipms.ui.base;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.data.network.model.base.BaseResponse;
import in.ults.ipms.data.network.model.request.DeleteAssetDataRequest;
import in.ults.ipms.data.network.model.request.FetchBuildingAssetsRequest;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.data.network.model.response.UploadImageResponse;
import in.ults.ipms.utils.network.ProgressRequestBody;
import io.reactivex.Observable;

/**
 * Created by Mohammed Shafeeq on 3/20/2018.
 */

public class BaseInteractor implements IBaseInteractor {

    private final DataManager appDataManager;

    @Inject
    public BaseInteractor(DataManager dataManager) {
        appDataManager = dataManager;
    }

    @Override
    public DataManager getDataManager() {
        return appDataManager;
    }

    @Override
    public Observable<UploadImageResponse> updateImage(String mainPath, String subPath, String localBodyId, String imageName, ProgressRequestBody reqFile) {
        return getDataManager().updateImage(getDataManager().getAccessToken(), mainPath, subPath, localBodyId, imageName, reqFile);
    }

    @Override
    public Observable<FetchAssetDataResponse> fetchBuildAssetData(FetchBuildingAssetsRequest request) {
        return getDataManager().fetchBuildAssetData(getDataManager().getAccessToken(),request);
    }

    @Override
    public Observable<FetchAssetDataResponse> saveAssetDetails(FetchAssetDataResponse.Data data) {
        return getDataManager().saveAssetDetails(getDataManager().getAccessToken(),data);
    }

    @Override
    public Observable<BaseResponse> deleteAssetDetails(DeleteAssetDataRequest request) {
        return getDataManager().deleteAssetDetails(getDataManager().getAccessToken(), request);
    }
}
