package in.ults.ipms.utils.exceptions;

import java.io.IOException;

/**
 * Created by Mohammed Shafeeq on 26/08/18.
 */
public class UnAuthorizedException extends IOException {

    @Override
    public String getMessage() {
        return "Your session timeout, Please login again.";
    }
}