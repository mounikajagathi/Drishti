package in.ults.ipms.data.network;


import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Inject;
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
import in.ults.ipms.data.network.model.response.OtherAssetsSpinnerResponse;
import in.ults.ipms.data.network.model.response.SearchDetailsResponse;
import in.ults.ipms.data.network.model.response.SearchResponse;
import in.ults.ipms.data.network.model.response.UploadImageResponse;
import in.ults.ipms.data.network.model.response.UtilitySpinnerResponse;
import in.ults.ipms.data.network.model.response.WaterBodySpinnerResponse;
import in.ults.ipms.utils.network.ProgressRequestBody;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Mohammed Shafeeq on 28/01/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private final ApiInterface mApiInterface;
    private static final MediaType API_BODY_REQUEST_TYPE = MediaType.parse("application/json; charset=utf-8");
    private final Gson gson;

    @Inject
    public AppApiHelper(ApiInterface mApiInterface, Gson gson) {
        this.mApiInterface = mApiInterface;
        this.gson = gson;
    }

    @Override
    public ApiInterface getApiInterface() {
        return mApiInterface;
    }


    @Override
    public Observable<LoginResponse> loginUser(LoginRequest request) {
        Log.v("LOGIN REQUEST :: " , new Gson().toJson(request));
        return mApiInterface.loginUser(RequestBody.create(API_BODY_REQUEST_TYPE,
                gson.toJson(request)));
    }

    @Override
    public Observable<BaseResponse> resetPassword(ForgotPasswordRequest request) {
        return mApiInterface.resetPassword(RequestBody.create(API_BODY_REQUEST_TYPE,
                gson.toJson(request)));
    }

    @Override
    public Observable<DashboardResponse> dashboardDetails(String authorization) {
        return mApiInterface.dashboardDetails(authorization);
    }

    @Override
    public Observable<BuildingAssetSpinnerResponse> getBuildingAssetSpinnerData(String authorization) {
        return mApiInterface.getBuildingAssetSpinnerData(authorization);
    }

    @Override
    public Observable<BuildingNameSpinnerResponse> getBuildingNameSpinnerData(String authorization) {
        return mApiInterface.getBuildingNameSpinnerData(authorization);
    }

    @Override
    public Observable<SearchResponse> searchData(String authorization, String searchFilter, String searchValue) {
        return mApiInterface.searchData(authorization, searchFilter, searchValue);
    }

    @Override
    public Observable<FeatureDataResponse> getFeatureInfoData(String authorization, ArrayList<FeatureDataRequest> request) {
        return mApiInterface.getFeatureInfoData(authorization, RequestBody.create(API_BODY_REQUEST_TYPE,
                gson.toJson(request)));
    }

    @Override
    public Observable<FetchAssetDataResponse> fetchBuildAssetData(String authorization, FetchBuildingAssetsRequest request) {

        System.out.println("@@@--->"+new Gson().toJson(request));
        return mApiInterface.fetchBuildAssetData(authorization, RequestBody.create(API_BODY_REQUEST_TYPE,
                gson.toJson(request)));
    }

    @Override
    public Observable<UploadImageResponse> updateImage(String xAuth, String mainPath, String subPath, String localBodyId, String imageName, ProgressRequestBody reqFile) {
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", imageName, reqFile);
        return mApiInterface.updateImage(xAuth, mainPath, subPath, localBodyId, "attachment; filename="+imageName, body);
    }

    @Override
    public Observable<FetchAssetDataResponse> saveAssetDetails(String authorization, FetchAssetDataResponse.Data data) {

        System.out.println("@@@--->"+authorization);
        System.out.println("@@@--->"+new Gson().toJson(data));
        return mApiInterface.saveAssetDetails(authorization, RequestBody.create(API_BODY_REQUEST_TYPE,
                gson.toJson(data)));
    }

    @Override
    public Observable<UtilitySpinnerResponse> getUtilitySpinnerResponse(String authorization) {
        return mApiInterface.getUtilitySpinnerResponse(authorization);
    }

    @Override
    public Observable<WaterBodySpinnerResponse> getWaterBodySpinnerResponse(String authorization) {
        return mApiInterface.getWaterBodySpinnerResponse(authorization);
    }

    @Override
    public Observable<SearchDetailsResponse> searchDetails(String authorization,  String searchFilter, String searchValue) {
        return mApiInterface.searchDetails(authorization, searchFilter,  searchValue);
    }

    @Override
    public Observable<Boolean> saveRUAddRoad(String authorization) {
        return null;
    }

    @Override
    public Observable<BaseResponse> deleteAssetDetails(String authorization, DeleteAssetDataRequest request) {
        return mApiInterface.deleteAssetDetails(authorization, RequestBody.create(API_BODY_REQUEST_TYPE,
                gson.toJson(request)));
    }

    @Override
    public Observable<OtherAssetsSpinnerResponse> getOtherAssetsSpinnerResponse(String authorization) {
        return mApiInterface.getOtherAssetsSpinnerResponse(authorization);
    }
}

