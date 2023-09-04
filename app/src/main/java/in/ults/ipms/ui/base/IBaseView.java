package in.ults.ipms.ui.base;

import android.app.Dialog;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;

/**
 * Created by Mohammed Shafeeq on 19/03/18.
 */

public interface IBaseView {

    boolean isNetworkConnected();

   /* void showToast(String message);

    void showDialog(String message);*/

    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void hideKeyboard();

    void createCustomDialog(@LayoutRes int view);

    Dialog getCustomDialog();

    void showCustomDialog();

    void hideCustomDialog();

    void showToast(String message);

    void showToast(@StringRes int resId);

    void showDialog(String titleRes, String messageRes, String positiveButtonRes, String negativeButtonRes, View.OnClickListener positiveListener, View.OnClickListener negativeListener);

    void showDialog(String titleRes, String messageRes, String positiveButtonRes, View.OnClickListener positiveListener);

    void showDialog(@StringRes int titleRes, @StringRes int messageRes, @StringRes int positiveButtonRes, @StringRes int negativeButtonRes, View.OnClickListener positiveListener, View.OnClickListener negativeListener);

    void showDialog(@StringRes int titleRes, @StringRes int messageRes, @StringRes int positiveButtonRes, View.OnClickListener positiveListener);

    void showSnackBar(String message);

    void showSnackBar(@StringRes int resId);

    void showProgressDialog();

    void updateProgressPercent(int percent);

    void hideDialog();

    void hideProgressDialog();

    void onInternetConnectionFailure();

    void onApiFailure();

    void logoutUser();

    void showProgressBar();

    void hideProgressBar();

    void onImageUploadSuccess(String imagePath, String imageType, String response);

    void onFetchAssetSuccess(String id, String app, String layer);


}
