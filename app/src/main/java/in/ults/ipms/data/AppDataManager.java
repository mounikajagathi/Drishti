package in.ults.ipms.data;

import android.content.Context;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.ults.ipms.data.db.DbHelper;
import in.ults.ipms.data.network.ApiHelper;
import in.ults.ipms.data.network.ApiInterface;
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
import in.ults.ipms.data.prefs.PreferencesHelper;
import in.ults.ipms.di.ApplicationContext;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.network.ProgressRequestBody;
import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    private final DbHelper mDbHelper;

    private final Context mContext;

    private final PreferencesHelper mPreferencesHelper;


    @Inject
    AppDataManager(@ApplicationContext Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public boolean isUserLoggedIn() {
        return mPreferencesHelper.getCurrentUserLoggedInMode() == AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_IN.getType();
    }

    @Override
    public void setUserAsLoggedOut() {
        mPreferencesHelper.setCurrentUserLoggedInMode(AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
    }

    @Override
    public void setUserAsLoggedIn() {
        mPreferencesHelper.setCurrentUserLoggedInMode(AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_IN);
    }

    @Override
    public ApiInterface getApiInterface() {
        return mApiHelper.getApiInterface();
    }

    @Override
    public Observable<LoginResponse> loginUser(LoginRequest request) {
        return mApiHelper.loginUser(request);
    }

    @Override
    public Observable<BaseResponse> resetPassword(ForgotPasswordRequest request) {
        return mApiHelper.resetPassword(request);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(AppConstants.LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public long getUserAccessId() {
        return mPreferencesHelper.getUserAccessId();
    }

    @Override
    public void setUserAccessId(long id) {
        mPreferencesHelper.setUserAccessId(id);
    }

    @Override
    public boolean getUserActiveStatus() {
        return mPreferencesHelper.getUserActiveStatus();
    }

    @Override
    public void setUserActiveStatus(boolean activeStatus) {
        mPreferencesHelper.setUserActiveStatus(activeStatus);
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
    }

    @Override
    public String getLogUserName() {
        return CommonUtils.decryptBase64Data(mPreferencesHelper.getLogUserName());
    }

    @Override
    public void setLogUserName(String userName) {
        mPreferencesHelper.setLogUserName(CommonUtils.encryptDataToBase64(userName));
    }

    @Override
    public String getLogPassword() {
        return CommonUtils.decryptBase64Data(mPreferencesHelper.getLogPassword());
    }

    @Override
    public void setLogPassword(String password) {
        mPreferencesHelper.setLogPassword(CommonUtils.encryptDataToBase64(password));
    }

    @Override
    public Observable<DashboardResponse> dashboardDetails(String authorization) {
        return mApiHelper.dashboardDetails(authorization);
    }

    @Override
    public Observable<BuildingAssetSpinnerResponse> getBuildingAssetSpinnerData(String authorization) {
        return mApiHelper.getBuildingAssetSpinnerData(authorization);
    }

    @Override
    public Observable<BuildingNameSpinnerResponse> getBuildingNameSpinnerData(String authorization) {
        return mApiHelper.getBuildingNameSpinnerData(authorization);
    }

    @Override
    public Observable<SearchResponse> searchData(String authorization, String searchFilter, String searchValue) {
        return mApiHelper.searchData(authorization, searchFilter, searchValue);
    }

    @Override
    public Observable<FeatureDataResponse> getFeatureInfoData(String authorization, ArrayList<FeatureDataRequest> request) {
        return mApiHelper.getFeatureInfoData(authorization, request);
    }

    @Override
    public Observable<FetchAssetDataResponse> fetchBuildAssetData(String authorization, FetchBuildingAssetsRequest request) {
        return mApiHelper.fetchBuildAssetData(authorization, request);
    }

    @Override
    public Observable<UploadImageResponse> updateImage(String xAuth, String mainPath, String subPath, String localBodyId, String imageName, ProgressRequestBody reqFile) {
        return mApiHelper.updateImage(xAuth, mainPath, subPath, localBodyId, imageName, reqFile);
    }

    @Override
    public Observable<FetchAssetDataResponse> saveAssetDetails(String authorization, FetchAssetDataResponse.Data data) {
        return mApiHelper.saveAssetDetails(authorization, data);
    }

    @Override
    public Observable<UtilitySpinnerResponse> getUtilitySpinnerResponse(String authorization) {
        return mApiHelper.getUtilitySpinnerResponse(authorization);
    }

    @Override
    public Observable<WaterBodySpinnerResponse> getWaterBodySpinnerResponse(String authorization) {
        return mApiHelper.getWaterBodySpinnerResponse(authorization);
    }

    @Override
    public Observable<OtherAssetsSpinnerResponse> getOtherAssetsSpinnerResponse(String authorization) {
        return mApiHelper.getOtherAssetsSpinnerResponse(authorization);
    }

    @Override
    public Observable<Boolean> saveRUAddRoad(String authorization) {
        return mApiHelper.saveRUAddRoad(authorization);
    }

    @Override
    public Observable<SearchDetailsResponse> searchDetails(String authorization, String searchFilter,  String searchValue) {
        return mApiHelper.searchDetails(authorization,searchFilter, searchValue);
    }

    @Override
    public Observable<BaseResponse> deleteAssetDetails(String authorization, DeleteAssetDataRequest request) {
        return mApiHelper.deleteAssetDetails(authorization, request);
    }
}
