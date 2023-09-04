package in.ults.ipms.data.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AmalB on 18/06/21.
 */

public class ForgotPasswordRequest {

    @Expose
    @SerializedName("email")
    private String email;

    public ForgotPasswordRequest(String email){
        this.email = email;
    }
}
