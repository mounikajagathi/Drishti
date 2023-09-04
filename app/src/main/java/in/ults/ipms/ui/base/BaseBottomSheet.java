package in.ults.ipms.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.viewbinding.ViewBinding;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import in.ults.ipms.R;
import in.ults.ipms.application.AppClass;
import in.ults.ipms.di.component.ActivityComponent;
import in.ults.ipms.di.component.ApplicationComponent;
import in.ults.ipms.di.component.BottomSheetComponent;
import in.ults.ipms.di.component.DaggerBottomSheetComponent;
import in.ults.ipms.di.module.BottomSheetModule;
import in.ults.ipms.utils.common.CommonUtils;

/**
 * Created by Mohammed Shafeeq on 24/05/17.
 */

public abstract class BaseBottomSheet<T extends ViewBinding> extends BottomSheetDialogFragment implements IBaseView {


    private BaseActivity mActivity;
    private View mRootView;
    private BottomSheetComponent bottomSheetComponent;
    private T viewBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            viewBinding = initViewBinding(inflater, container, false);
            mRootView = viewBinding.getRoot();
            bottomSheetComponent = DaggerBottomSheetComponent.builder()
                    .bottomSheetModule(new BottomSheetModule(mActivity))
                    .applicationComponent(getApplicationComponent())
                    .build();
            initInjector();
            attachPresenter();
            init(mRootView);
            setUp(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View mRootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(mRootView, savedInstanceState);
        mRootView.setFocusableInTouchMode(true);
        mRootView.requestFocus();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }
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
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }


    public BottomSheetComponent getBottomSheetComponent() {
        return bottomSheetComponent;
    }

    private ApplicationComponent getApplicationComponent() {
        return AppClass.getApplicationComponent();
    }

    protected View getRootView() {
        return mRootView;
    }

    protected BaseActivity getBaseActivity() {
        return mActivity;
    }

    /*Abstract Methods*/

    public T getViewBinding() {
        return viewBinding;
    }

    protected abstract T initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent);

    protected abstract void initInjector();

    protected abstract void attachPresenter();

    protected abstract void detachPresenter();

    protected void init(View view) {

    }

    protected void setUp(View view) {

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
    public void createCustomDialog(@LayoutRes int view) {
        if (mActivity != null) {
            mActivity.createCustomDialog(view);
        }
    }

    @Override
    public Dialog getCustomDialog() {
        if (mActivity != null) {
            return mActivity.getCustomDialog();
        } else {
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
        if (mActivity != null) {
            mActivity.logoutUser();
        }
    }

    @Override
    public void updateProgressPercent(int percent) {
        if (mActivity != null) {
            mActivity.updateProgressPercent(percent);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        CommonUtils.setDialogNavigationBarColor(getBaseActivity(), dialog, R.color.colorHintGrey);
        return dialog;
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