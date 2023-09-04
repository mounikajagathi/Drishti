package in.ults.ipms.utils.network;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.IOException;

import in.ults.ipms.utils.connection.ConnectionUtils;
import in.ults.ipms.utils.exceptions.NoInternetException;
import in.ults.ipms.utils.exceptions.UnAuthorizedException;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Mohammed Shafeeq on 26/08/18.
 */
public class NetworkInterceptor implements Interceptor {


    private final Context context;

    public NetworkInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (!ConnectionUtils.isNetworkConnected(context)) {
            throw new NoInternetException();
        } else {
            Response response = chain.proceed(chain.request());
            if (response.code() == 401) {
                throw new UnAuthorizedException();
            }
            return response;
        }
    }
}