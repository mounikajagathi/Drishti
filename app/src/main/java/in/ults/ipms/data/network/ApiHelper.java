package in.ults.ipms.data.network;


import java.util.ArrayList;

import javax.inject.Singleton;

import in.ults.ipms.data.network.model.base.BaseResponse;
import in.ults.ipms.data.network.model.request.DeleteAssetDataRequest;
import in.ults.ipms.data.network.model.request.FeatureDataRequest;
import in.ults.ipms.data.network.model.request.FetchBuildingAssetsRequest;
import in.ults.ipms.data.network.model.request.ForgotPasswordRequest;
import in.ults.ipms.data.network.model.request.LoginRequest;
import in.ults.ipms.data.network.model.response.BuildingAssetSpinnerResponse;
import in.ults.ipms.data.network.model.response.BuildingNameSpinnerResponse;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.data.network.model.response.FeatureDataResponse;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.data.network.model.response.LoginResponse;
import in.ults.ipms.data.network.model.response.SearchDetailsResponse;
import in.ults.ipms.data.network.model.response.SearchResponse;
import in.ults.ipms.data.network.model.response.UploadImageResponse;
import in.ults.ipms.data.network.model.response.UtilitySpinnerResponse;
import in.ults.ipms.data.network.model.response.WaterBodySpinnerResponse;
import in.ults.ipms.utils.network.ProgressRequestBody;
import io.reactivex.Observable;

/**
 * Created by Mohammed Shafeeq on 27/01/17.
 */

@Singleton
public interface ApiHelper {

    ApiInterface getApiInterface();
    Observable<LoginResponse> loginUser(LoginRequest request);
    Observable<BaseResponse> resetPassword(ForgotPasswordRequest request);
    Observable<DashboardResponse> dashboardDetails(String authorization);
    Observable<BuildingAssetSpinnerResponse> getBuildingAssetSpinnerData(String authorization);
    Observable<BuildingNameSpinnerResponse> getBuildingNameSpinnerData(String authorization);
    Observable<SearchResponse> searchData(String authorization, String searchFilter, String searchValue);
    Observable<SearchDetailsResponse> searchDetails(String authorization, String searchFilter, String searchValue);
    Observable<FeatureDataResponse> getFeatureInfoData(String authorization, ArrayList<FeatureDataRequest> request);
    Observable<FetchAssetDataResponse> fetchBuildAssetData(String authorization, FetchBuildingAssetsRequest request);
    Observable<UploadImageResponse> updateImage(String xAuth, String mainPath, String subPath, String localBodyId,
                                                String imageName, ProgressRequestBody reqFile);
    Observable<FetchAssetDataResponse> saveAssetDetails(String authorization, FetchAssetDataResponse.Data data);
    Observable<BaseResponse> deleteAssetDetails(String authorization, DeleteAssetDataRequest request);
    Observable<UtilitySpinnerResponse> getUtilitySpinnerResponse(String authorization);
    Observable<WaterBodySpinnerResponse> getWaterBodySpinnerResponse(String authorization);
    Observable<Boolean> saveRUAddRoad(String authorization);


}
