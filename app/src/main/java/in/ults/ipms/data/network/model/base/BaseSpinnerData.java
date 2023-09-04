package in.ults.ipms.data.network.model.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseSpinnerData {

    @Expose
    @SerializedName("id")
    private String spinnerId;


    public String getSpinnerTitle(){
        return null;
    }

    public String getSpinnerId(){
        return spinnerId;
    }
}
