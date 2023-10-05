package in.ults.ipms.data.network;


import in.ults.ipms.data.network.model.base.BaseResponse;
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
import in.ults.ipms.data.network.model.response.WFSModel;
import in.ults.ipms.data.network.model.response.WaterBodySpinnerResponse;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Allan on 27-07-2017.
 */

public interface ApiInterface {

    //Login Api
    @POST("mobileapi/token/")
    Observable<LoginResponse> loginUser(@Body RequestBody stu);

    @POST("map/request-reset-email/")
    Observable<BaseResponse> resetPassword(@Body RequestBody stu);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("mobileapi/user-details/")
    Observable<DashboardResponse> dashboardDetails(@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("mobileapi/basicsearch/{field_name}/{field_value}/")
    Observable<SearchResponse> searchData(@Header("Authorization") String auth, @Path("field_name") String searchFilter, @Path("field_value") String searchValue);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("mobileapi/basicsearchresult/{search_filter}/{search_value}/")
    Observable<SearchDetailsResponse> searchDetails(@Header("Authorization") String auth, @Path("search_filter") String searchFilter, @Path("search_value") String searchValue);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("mobileapi/get-base-tables/")
    Observable<BuildingAssetSpinnerResponse> getBuildingAssetSpinnerData(@Header("Authorization") String auth);

    @POST("mobileapi/getrelateddata/")
    Observable<FeatureDataResponse> getFeatureInfoData(@Header("Authorization") String auth, @Body RequestBody stu);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("mobileapi/get-buildings/")
    Observable<BuildingNameSpinnerResponse> getBuildingNameSpinnerData(@Header("Authorization") String auth);


    @POST("mobileapi/get-asset-data/")
    Observable<FetchAssetDataResponse> fetchBuildAssetData(@Header("Authorization") String auth, @Body RequestBody stu);

    //Upload Api
    @Multipart
    @PUT("map/upload/{main_path}/{sub_path}/{local_body_id}/")
    Observable<UploadImageResponse> updateImage(@Header("Authorization") String auth, @Path("main_path") String mainPath, @Path("sub_path") String subPath, @Path("local_body_id") String localBodyId,@Header("Content-Disposition") String imageName,
                                                @Part MultipartBody.Part image);

    @POST("mobileapi/save-asset-data/")
    Observable<FetchAssetDataResponse> saveAssetDetails(@Header("Authorization") String auth, @Body RequestBody stu);

    @POST("mobileapi/delete-asset-data/")
    Observable<BaseResponse> deleteAssetDetails(@Header("Authorization") String auth, @Body RequestBody stu);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("mobileapi/get-utility-dropdown-and-layers/")
    Observable<UtilitySpinnerResponse> getUtilitySpinnerResponse(@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("mobileapi/get-pond-utility-dropdown/")
    Observable<WaterBodySpinnerResponse> getWaterBodySpinnerResponse(@Header("Authorization") String auth);


     @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("mobileapi/get_otherasset_dropdown/")
    Observable<OtherAssetsSpinnerResponse> getOtherAssetsSpinnerResponse(@Header("Authorization") String auth);


    @GET("geoserver/wfs?version=1.3.0&request=GetFeature&outputFormat=application%2Fjson&service=WFS&typeName=drishti:ipms_localbody&srsname=EPSG:3857&cql_filter=localbody_id%3D2&authkey=a1213a5b-5131-41e4-97bb-ebcf80de3923")
    Call<WFSModel> getWFS();

}
