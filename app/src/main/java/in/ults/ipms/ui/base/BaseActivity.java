package in.ults.ipms.ui.base;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.WindowCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import in.ults.ipms.BuildConfig;
import in.ults.ipms.R;
import in.ults.ipms.application.AppClass;
import in.ults.ipms.data.network.model.response.GeomPolyLine;
import in.ults.ipms.di.component.ActivityComponent;
import in.ults.ipms.di.component.ApplicationComponent;
import in.ults.ipms.di.component.DaggerActivityComponent;
import in.ults.ipms.di.module.ActivityModule;
import in.ults.ipms.ui.auth.AuthActivity;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.connection.ConnectionUtils;
import in.ults.ipms.utils.file.FileUtils;
import in.ults.ipms.utils.path.PathUtils;
import in.ults.ipms.utils.view.ViewUtils;
import in.ults.ipms.views.AppProgressBar;
import in.ults.ipms.views.adaptivestate.AdaptiveStateConstants;
import in.ults.ipms.views.adaptivestate.AdaptiveStateLayout;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by Mohammed Shafeeq on 19/03/18.
 */

@SuppressWarnings("rawtypes")
public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity implements IBaseView, IFragmentCallback, AdaptiveStateLayout.AdaptiveStateLayoutListener {

    private ActivityComponent activityComponent;
    private BaseFragment baseFragment;
    private ProgressDialog commonProgressDialog;
    private AdaptiveStateLayout adaptiveStateLayout;
    private Dialog alertDialog;
    private Dialog customDialog;
    private T viewBinding;
    private Uri imageUri;
    private int reqTypeCode;
    private ImagePickListener imagePickListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setFullScreenActivity()) {
            getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
        if (setTransparentStatusBar()) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (adjustLayoutWithKeyboard()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
        if (getStatusBarColor() != -999) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(getStatusBarColor());
        }
        viewBinding = initViewBinding();
        adaptiveStateLayout = new AdaptiveStateLayout(this);
        adaptiveStateLayout.setActualLayoutView(viewBinding.getRoot());
        adaptiveStateLayout.addFullStates(AdaptiveStateConstants.getFullStates());
        adaptiveStateLayout.setOnClickListener(this);
        setContentView(adaptiveStateLayout);
        activityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule(this))
                .applicationComponent(getApplicationComponent()).build();
        initInjector();
        attachPresenter();
        RxJavaPlugins.setErrorHandler(e -> Log.d("RxException", e.getLocalizedMessage()));
        init();
        configureToolbar();
        setUp();
        hideNavOnKeyboard();

    }

    private void hideNavOnKeyboard() {
        if (hideNavigationIcons()) {
            getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(() -> {
                Rect r = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                int screenHeight = getWindow().getDecorView().getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;
                if (keypadHeight > screenHeight * 0.15) {
                    //Keyboard is opened
                    setNavigationOnKeyboard();
                } else {
                    setUiFlags(true, true);

                }
            });
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        setUiFlags(hasFocus, false);
    }

    void setUiFlags(boolean hasFocus, boolean hasKeyboard) {
        View decorView = getWindow().getDecorView();
        int visibility = -5;
        if (setDarkStatusBarIcon() && hideNavigationIcons()) {
            visibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        } else if (setDarkStatusBarIcon() & !hasKeyboard) {
            visibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        } else if (hideNavigationIcons()) {
            visibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        if (hasFocus) {
            if (visibility != -5) {
                decorView.setSystemUiVisibility(visibility);
                if (adjustLayoutWithKeyboard()) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                }
            }
        }
    }


    void setNavigationOnKeyboard() {
        View decorView = getWindow().getDecorView();
        int visibility = -5;
        if (setDarkStatusBarIcon() && hideNavigationIcons()) {
            visibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        } else if (hideNavigationIcons()) {
            visibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        }
        if (visibility != -5) {
            decorView.setSystemUiVisibility(visibility);
            if (adjustLayoutWithKeyboard()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        }
    }


    /**
     * Activity component for dependency injection
     *
     * @return activity component
     */
    protected ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public T getViewBinding() {
        return viewBinding;
    }

    /**
     * Application component for dependency injection
     *
     * @return application component
     */
    private ApplicationComponent getApplicationComponent() {
        return AppClass.getApplicationComponent();
    }

    @Override
    protected void onDestroy() {
        detachPresenter();
        viewBinding = null;
        super.onDestroy();
    }


    /*Abstract Methods*/

    protected boolean setFullScreenActivity() {
        return false;
    }

    protected int getStatusBarColor() {
        return -999;
    }

    protected boolean adjustLayoutWithKeyboard() {
        return false;
    }

    protected boolean setTransparentStatusBar() {
        return false;
    }

    protected boolean setDarkStatusBarIcon() {
        return false;
    }

    protected boolean hideNavigationIcons() {
        return false;
    }

    protected abstract T initViewBinding();


    protected abstract void initInjector();

    protected abstract void attachPresenter();

    protected abstract void detachPresenter();


    protected void init() {

    }

    protected void setUp() {

    }

    protected void configureToolbar() {

    }

    public void setToolbarTitle(String toolbarTitle) {

    }


    public void showToolbar() {

    }

    public void hideToolbar() {

    }

    protected void lockMenuDrawer() {

    }

    protected void unLockMenuDrawer() {

    }

    public TabLayout getToolbarTabLayout() {
        return null;
    }


    public DrawerLayout getMenuDrawer() {
        return null;
    }

    public BaseFragment getBaseFragment() {
        return baseFragment;
    }

    public boolean isBaseFragmentAttached() {
        return baseFragment != null;
    }

    public void setBaseFragment(BaseFragment baseFragment) {
        this.baseFragment = baseFragment;
    }

    @Override
    public boolean isNetworkConnected() {
        return ConnectionUtils.isNetworkConnected(this);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public void onInternetConnectionFailure() {
        showToast(R.string.common_error_no_internet_description);
    }

    @Override
    public void onApiFailure() {
        showToast(R.string.api_error_unable_process_request);
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showToast(String message) {
        if (message != null && message.length() > 0) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showToast(@StringRes int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void showDialog(String titleRes, String messageRes, String positiveButtonRes, View.OnClickListener positiveListener) {
        showDialog(titleRes, messageRes, positiveButtonRes, null, positiveListener, null);

    }

    @Override
    public void showDialog(@StringRes int titleRes, @StringRes int messageRes, @StringRes int positiveButtonRes, View.OnClickListener positiveListener) {
        showDialog(getResources().getString(titleRes), getResources().getString(messageRes), getResources().getString(positiveButtonRes), null, positiveListener, null);
    }

    @Override
    public void showDialog(@StringRes int titleRes, @StringRes int messageRes, @StringRes int positiveButtonRes, @StringRes int negativeButtonRes, View.OnClickListener positiveListener, View.OnClickListener negativeListener) {
        showDialog(getResources().getString(titleRes), getResources().getString(messageRes), getResources().getString(positiveButtonRes), getResources().getString(negativeButtonRes), positiveListener, negativeListener);
    }

    @Override
    public void showDialog(String titleRes, String messageRes, String positiveButtonRes, String negativeButtonRes, View.OnClickListener positiveListener, View.OnClickListener negativeListener) {
        alertDialog = new Dialog(this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        FrameLayout fl = new FrameLayout(this);
        View v = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.alert_dialog, null, false);
        fl.addView(v);
        fl.setPadding(getResources().getDimensionPixelOffset(R.dimen.standard_spacing_medium),
                getResources().getDimensionPixelOffset(R.dimen.standard_spacing_medium),
                getResources().getDimensionPixelOffset(R.dimen.standard_spacing_medium),
                getResources().getDimensionPixelOffset(R.dimen.standard_spacing_medium));
        alertDialog.setContentView(fl);
        ((TextView) v.findViewById(R.id.txtDialogTitle)).setText(titleRes);
        ((TextView) v.findViewById(R.id.txtAlertMessage)).setText(messageRes);
        ((ImageView) v.findViewById(R.id.btnCloseDialog)).setVisibility(View.GONE);
        if (positiveListener != null) {
            ((Button) v.findViewById(R.id.btnPositive)).setText(positiveButtonRes);
            ((Button) v.findViewById(R.id.btnPositive)).setOnClickListener(positiveListener);
            ((Button) v.findViewById(R.id.btnPositive)).setVisibility(View.VISIBLE);
        } else {
            ((Button) v.findViewById(R.id.btnPositive)).setVisibility(View.GONE);
        }

        if (negativeListener != null) {
            ((Button) v.findViewById(R.id.btnNegative)).setText(negativeButtonRes);
            ((Button) v.findViewById(R.id.btnNegative)).setOnClickListener(positiveListener);
            ((Button) v.findViewById(R.id.btnNegative)).setVisibility(View.VISIBLE);
        } else {
            ((Button) v.findViewById(R.id.btnNegative)).setVisibility(View.GONE);
        }

        if (positiveListener == null && negativeListener == null) {
            ((ImageView) v.findViewById(R.id.btnCloseDialog)).setVisibility(View.VISIBLE);
            ((ImageView) v.findViewById(R.id.btnCloseDialog)).setOnClickListener(view -> alertDialog.dismiss());
        }
        final Window window = alertDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawableResource(R.color.colorTransparent);
        window.setGravity(Gravity.CENTER);
        alertDialog.show();
    }


    @Override
    public void showSnackBar(String message) {

    }

    @Override
    public void showSnackBar(int resId) {

    }

    @Override
    public void createCustomDialog(@LayoutRes int view) {
        customDialog = new Dialog(this);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setCancelable(false);
        customDialog.setCanceledOnTouchOutside(false);
        FrameLayout fl = new FrameLayout(this);
        fl.addView(((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(view, null, false));
        fl.setPadding(getResources().getDimensionPixelOffset(R.dimen.standard_spacing_medium),
                getResources().getDimensionPixelOffset(R.dimen.standard_spacing_medium),
                getResources().getDimensionPixelOffset(R.dimen.standard_spacing_medium),
                getResources().getDimensionPixelOffset(R.dimen.standard_spacing_medium));
        customDialog.setContentView(fl);
        final Window window = customDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawableResource(R.color.colorTransparent);
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public Dialog getCustomDialog() {
        return customDialog;
    }

    @Override
    public void showCustomDialog() {
        if (customDialog != null) {
            customDialog.show();
        }
    }

    @Override
    public void hideCustomDialog() {
        if (customDialog != null && customDialog.isShowing()) {
            customDialog.dismiss();
        }
    }

    @Override
    public void hideDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (commonProgressDialog != null && commonProgressDialog.isShowing()) {
            commonProgressDialog.dismiss();
        }

    }

    @Override
    public void showProgressDialog() {
        commonProgressDialog = new ProgressDialog(this);
        try {
            View progressView = LayoutInflater.from(this).inflate(R.layout.common_progress_dialog, ViewUtils.getNullParent());
            Objects.requireNonNull(commonProgressDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            commonProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            commonProgressDialog.setCanceledOnTouchOutside(false);
            commonProgressDialog.setCancelable(false);
            commonProgressDialog.show();
            commonProgressDialog.setContentView(progressView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProgressPercent(int percent) {
        if (commonProgressDialog != null && commonProgressDialog.isShowing()) {
            if (percent > -1 && percent < 101) {
                ((AppProgressBar) commonProgressDialog.findViewById(R.id.common_progress_dialog_progressbar)).setVisibility(View.GONE);
                commonProgressDialog.findViewById(R.id.common_progress_dialog_progress_card_view).setVisibility(View.VISIBLE);
                ((TextView) commonProgressDialog.findViewById(R.id.common_progress_dialog_progress_counter)).setText(String.format(Locale.getDefault(), "Uploading...\n%d%% / 100", percent));
            } else {
                commonProgressDialog.findViewById(R.id.common_progress_dialog_progressbar).setVisibility(View.VISIBLE);
                commonProgressDialog.findViewById(R.id.common_progress_dialog_progress_card_view).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void logoutUser() {
        startActivity(new Intent(this, AuthActivity.class));
    }

    public AdaptiveStateLayout getAdaptiveStateLayout() {
        return adaptiveStateLayout;
    }

    @Override
    public void onAdaptiveStateClick(int stateType, int buttonId) {

    }

    @Override
    public void launchLoginPage(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchForgotPassword(boolean isBackStack, boolean isAnimate) {

    }

    public void launchPropertyDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchBuildingDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchLocationDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchFloorAndRoofDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchRoadDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchEstablishmentDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchSurveyDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchPropertyOtherDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchTaxDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchTenantDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchOwnerDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchMemberDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchBuildAssetHome(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchMemberList(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchRoadMap(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchRoadDetails(boolean isBackStack, boolean isAnimate, String length, GeomPolyLine geom) {
    }

    @Override
    public void launchTaxList(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchOwnerList(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchUtilityHome(boolean isBackStack, boolean isAnimate) {

    }


    @Override
    public void launchBridgeDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchCulvertDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchDividerDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchParkingDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchPlaygroundDetails(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchRoadHumpMap(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchRoadJunctionMap(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchRoadSignboardMap(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchDrainageMap(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchDrainageDetails(boolean isBackStack, boolean isAnimate, GeomPolyLine geom) {

    }

    @Override
    public void launchWaterBodyHome(boolean isBackStack, boolean isAnimate) {

    }

    @Override
    public void launchPondDetails(boolean isBackStack, boolean isAnimate) {

    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }


    /**
     * Override for save or remove image
     */
    public interface ImagePickListener {
        void onImageCaptured(String path, int reqTypeCode);

        void onImageRemoved(int reqTypeCode);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstants.REQUEST_CODE_OPEN_CAMERA) {
                if (imageUri != null && FileUtils.isValidImageFile(imageUri.getPath())) {
                    if (imagePickListener != null) {
                        imagePickListener.onImageCaptured(imageUri.getPath(), reqTypeCode);
                    }
                }
            } else if (requestCode == AppConstants.REQUEST_CODE_OPEN_GALLERY) {
                if (data != null && data.getData() != null && FileUtils.isValidImageFile(PathUtils.getPath(this, data.getData()))) {
                    if (imagePickListener != null) {
                        imagePickListener.onImageCaptured(PathUtils.getPath(this, data.getData()), reqTypeCode);
                    }
                }
            }
        }
    }

    public void openImagePicker(ImagePickListener imagePickListener, int reqTypeCode) {
        this.reqTypeCode = reqTypeCode;
        this.imagePickListener = imagePickListener;
        View sortingView = getLayoutInflater().inflate(R.layout.bottom_sheet_image_picker, null);
        BottomSheetDialog pickerBottomSheet = new BottomSheetDialog(this);
        pickerBottomSheet.setContentView(sortingView);
        pickerBottomSheet.setCanceledOnTouchOutside(true);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(((View) sortingView.getParent()));
        bottomSheetBehavior.setPeekHeight(1000);
        bottomSheetBehavior.setHideable(false);
        pickerBottomSheet.show();
        sortingView.findViewById(R.id.imgCamera).setOnClickListener(v -> {
            pickerBottomSheet.dismiss();
            openCamera(this, AppConstants.REQUEST_CODE_OPEN_CAMERA);
        });

        sortingView.findViewById(R.id.imgGallery).setOnClickListener(v -> {
            pickerBottomSheet.dismiss();
            openGallery(this, AppConstants.REQUEST_CODE_OPEN_GALLERY);
        });

        sortingView.findViewById(R.id.imgRemove).setOnClickListener(v -> {
            if (imagePickListener != null) {
                imagePickListener.onImageRemoved(reqTypeCode);
            }
            pickerBottomSheet.dismiss();
        });

    }


    public void openCamera(BaseActivity activity, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        File photo = new File(FileUtils.getCaptureImageDirectory(), "drishti_image_captured_" + new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new Date()) + ".jpg");
        imageUri = Uri.fromFile(photo);
        Uri intentUri = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".provider", photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, intentUri);
        List<ResolveInfo> resInfoList = activity.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            activity.grantUriPermission(packageName, imageUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        ActivityCompat.startActivityForResult(activity, Intent.createChooser(intent, activity.getResources().getString(R.string.open_camera_using)), requestCode, null);
    }

    /**
     * to open for open gallery
     */
    public void openGallery(BaseActivity activity, int requestCode) {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, FileUtils.MIME_TYPE_IMAGE);
        ActivityCompat.startActivityForResult(activity, i, requestCode, null);
    }

    @Override
    public void onImageUploadSuccess(String imagePath, String imageType, String response) {

    }

    @Override
    public void onFetchAssetSuccess(String id, String app, String layer) {

    }
}
