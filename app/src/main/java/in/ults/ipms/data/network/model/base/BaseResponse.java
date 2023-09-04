package in.ults.ipms.data.network.model.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.ults.ipms.utils.common.CommonUtils;

/**
 * Created by Mohammed Shafeeq on 12/04/18.
 */
public class BaseResponse {

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("message")
    private String message;

    public String getStatus() {
        return CommonUtils.removeNullContent(status);
    }

    public String getMessage() {
        return CommonUtils.removeNullContent(message);
    }

    public boolean isSuccess() {
        return getStatus().equalsIgnoreCase("true");
    }
}
