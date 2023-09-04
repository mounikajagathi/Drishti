package in.ults.ipms.utils.exceptions;

import java.io.IOException;

/**
 * Created by Mohammed Shafeeq on 26/08/18.
 */
public class NoInternetException extends IOException {

    @Override
    public String getMessage() {
        return "No network available, Please check your WiFi or Data connection";
    }
}