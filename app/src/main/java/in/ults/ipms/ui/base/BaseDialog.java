

package in.ults.ipms.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import in.ults.ipms.application.AppClass;
import in.ults.ipms.di.component.ActivityComponent;
import in.ults.ipms.di.component.ApplicationComponent;
import in.ults.ipms.di.component.DaggerDialogComponent;
import in.ults.ipms.di.component.DialogComponent;
import in.ults.ipms.di.module.DialogModule;

/**
 * Created by Mohammed Shafeeq on 24/05/17.
 */

public abstract class BaseDialog<T extends ViewBinding>  extends DialogFragment implements DialogBaseView {

    private BaseActivity mActivity;
    private View mRootView;
    private DialogComponent dialogComponent;
    private T viewBinding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }
    }


    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // the content
        final FrameLayout root = new FrameLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getBaseActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void show(FragmentManager fragmentManager, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment prevFragment = fragmentManager.findFragmentByTag(tag);
        if (prevFragment != null) {
            transaction.remove(prevFragment);
        }
        transaction.addToBackStack(null);
        show(transaction, tag);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            viewBinding = initViewBinding(inflater,container,false);
            mRootView = viewBinding.getRoot();
            dialogComponent = DaggerDialogComponent.builder()
                    .dialogModule(new DialogModule(mActivity))
                    .applicationComponent(getApplicationComponent())
                    .build();
            initInjector();
            attachPresenter();
            init(mRootView);
            setUp(mRootView);
        }
        return mRootView;
    }

    /*Abstract Methods*/
    public T getViewBinding() {
        return viewBinding;
    }

    protected abstract T initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent);



    protected abstract void initInjector();

    protected void init(View view) {

    }


    protected void setUp(View view) {

    }


    protected abstract void attachPresenter();

    protected abstract void detachPresenter();


    public DialogComponent getDialogComponent() {
        return dialogComponent;
    }

    private ApplicationComponent getApplicationComponent() {
        return AppClass.getApplicationComponent();
    }

    protected View getRootView() {
        return mRootView;
    }

    @Override
    public void dismissDialog(String tag) {
        dismiss();
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        detachPresenter();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewBinding = null;
    }


    @Override
    public void showLoading() {
        if (mActivity != null) {
            mActivity.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (mActivity != null) {
            mActivity.hideLoading();
        }
    }

    @Override
    public void onError(String message) {
        if (mActivity != null) {
            mActivity.onError(message);
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.onError(resId);
        }
    }

    @Override
    public void showMessage(String message) {
        if (mActivity != null) {
            mActivity.showMessage(message);
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.showMessage(resId);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (mActivity != null) {
            return mActivity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void showToast(String message) {
        if (mActivity != null) {
            mActivity.showToast(message);
        }
    }

    @Override
    public void showToast(int resId) {
        if (mActivity != null) {
            mActivity.showToast(resId);
        }
    }

    @Override
    public void showDialog(String titleRes, String messageRes, String positiveButtonRes, String negativeButtonRes, View.OnClickListener positiveListener, View.OnClickListener negativeListener) {
        if (mActivity != null) {
            mActivity.showDialog(titleRes, messageRes, positiveButtonRes, negativeButtonRes, positiveListener, negativeListener);
        }
    }

    @Override
    public void showDialog(String titleRes, String messageRes, String positiveButtonRes, View.OnClickListener positiveListener) {
        if (mActivity != null) {
            mActivity.showDialog(titleRes, messageRes, positiveButtonRes, positiveListener);
        }
    }

    @Override
    public void showDialog(int titleRes, int messageRes, int positiveButtonRes, int negativeButtonRes, View.OnClickListener positiveListener, View.OnClickListener negativeListener) {
        if (mActivity != null) {
            mActivity.showDialog(titleRes, messageRes, positiveButtonRes, negativeButtonRes, positiveListener, negativeListener);
        }
    }

    @Override
    public void showDialog(int titleRes, int messageRes, int positiveButtonRes, View.OnClickListener positiveListener) {
        if (mActivity != null) {
            mActivity.showDialog(titleRes, messageRes, positiveButtonRes, positiveListener);
        }
    }

    @Override
    public void showSnackBar(String message) {
        if (mActivity != null) {
            mActivity.showSnackBar(message);
        }
    }

    @Override
    public void showSnackBar(int resId) {
        if (mActivity != null) {
            mActivity.showSnackBar(resId);
        }
    }

    @Override
    public void createCustomDialog(@LayoutRes int view) {
        if (mActivity != null) {
            mActivity.createCustomDialog(view);
        }
    }

    @Override
    public Dialog getCustomDialog() {
        if (mActivity != null) {
            return mActivity.getCustomDialog();
        }else{
            return null;
        }
    }

    @Override
    public void showCustomDialog() {
        if (mActivity != null) {
            mActivity.showCustomDialog();
        }
    }

    @Override
    public void hideCustomDialog() {
        if (mActivity != null) {
            mActivity.hideCustomDialog();
        }
    }

    @Override
    public void showProgressDialog() {
        if (mActivity != null) {
            mActivity.showProgressDialog();
        }
    }


    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    @Override
    public void openActivityOnTokenExpire() {
        if (mActivity != null) {
            mActivity.openActivityOnTokenExpire();
        }
    }

    @Override
    public void hideDialog() {
        if (mActivity != null) {
            mActivity.hideDialog();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (mActivity != null) {
            mActivity.hideProgressDialog();
        }
    }

    @Override
    public void showProgressBar() {
        if (mActivity != null) {
            mActivity.showProgressBar();
        }
    }

    @Override
    public void hideProgressBar() {
        if (mActivity != null) {
            mActivity.hideProgressBar();
        }
    }


    @Override
    public void onInternetConnectionFailure() {
        if (mActivity != null) {
            mActivity.onInternetConnectionFailure();
        }
    }

    @Override
    public void onApiFailure() {
        if (mActivity != null) {
            mActivity.onApiFailure();
        }
    }

    @Override
    public void logoutUser() {
        if(mActivity!=null){
            mActivity.logoutUser();
        }
    }

    @Override
    public void updateProgressPercent(int percent) {
        if (mActivity != null) {
            mActivity.updateProgressPercent(percent);
        }
    }

    @Override
    public void onImageUploadSuccess(String imagePath, String imageType, String response) {
        if(mActivity!=null){
            mActivity.onImageUploadSuccess(imagePath,imageType,response);
        }
    }

    @Override
    public void onFetchAssetSuccess(String id, String app, String layer) {
        if(mActivity!=null){
            mActivity.onFetchAssetSuccess( id,  app,  layer);
        }
    }

}