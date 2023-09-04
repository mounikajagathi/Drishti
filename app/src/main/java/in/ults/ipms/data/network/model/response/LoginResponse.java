package in.ults.ipms.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.ults.ipms.data.network.model.base.BaseResponse;

/**
 * Created by Mohammed Shafeeq on 31/07/18.
 */
public class LoginResponse extends BaseResponse {

    @Expose
    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    //Inner Data Class
    public static class Data{

        @Expose
        @SerializedName("basicDetails")
        private BasicDetails basicDetails;

        @Expose
        @SerializedName("token")
        private String token;

        @Expose
        @SerializedName("refreshtoken")
        private String refreshToken;

        public BasicDetails getBasicDetails() {
            return basicDetails;
        }

        public String getToken() {
            return token;
        }

        public String getRefreshToken() {
            return refreshToken;
        }
    }


    public static class BasicDetails {

        @Expose
        @SerializedName("user_access_id")
        private long userAccessId;

        @Expose
        @SerializedName("user_name")
        private String userName;

        @Expose
        @SerializedName("active")
        private boolean userActiveStatus;

        public long getUserAccessId() {
            return userAccessId;
        }

        public String getUserName() {
            return userName;
        }

        public boolean getUserActiveStatus() {
            return userActiveStatus;
        }
    }
}
