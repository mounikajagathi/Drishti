package in.ults.ipms.ui.base;

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

public interface IBaseInteractor {

    DataManager getDataManager();
    Observable<UploadImageResponse> updateImage(String mainPath, String subPath, String localBodyId, String imageName, ProgressRequestBody reqFile);
    Observable<FetchAssetDataResponse> fetchBuildAssetData(FetchBuildingAssetsRequest request);

    Observable<FetchAssetDataResponse> saveAssetDetails(FetchAssetDataResponse.Data data);
    Observable<BaseResponse> deleteAssetDetails(DeleteAssetDataRequest request);


}
