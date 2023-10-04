package in.ults.ipms.ui.buildingassets.tax;

import android.app.DatePickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.databinding.FragmentTaxDetailsBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BaseFragment;
import in.ults.ipms.utils.AppConstants;


public class TaxDetailsFragment extends BaseFragment<FragmentTaxDetailsBinding> implements ITaxDetailsView, BaseActivity.ImagePickListener {

    public static final String TAG = TaxDetailsFragment.class.getSimpleName();

    public static final int ERROR_TYPE_BILL_NUMBER = 1;
    public static final int ERROR_TYPE_PAID_DATE = 2;
    public static final int ERROR_TYPE_PAID_YEAR = 3;
    public static final int ERROR_TYPE_AMOUNT = 4;
    public static final int ERROR_TYPE_ANNUAL = 5;
    public static final int ERROR_TYPE_ASSESSMENT_NUMBER = 6;
    public static final int ERROR_TYPE_IMAGE = 7;

    public static int selectedPosition = -1;
    private String taxPhoto;
    static final int REQUEST_CODE_TAX_IMAGE = 101;


    @Inject
    ITaxDetailsPresenter<ITaxDetailsView, ITaxDetailsInteractor> presenter;

    public static TaxDetailsFragment newInstance() {
        return new TaxDetailsFragment();
    }

    @Override
    protected FragmentTaxDetailsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentTaxDetailsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.tax_details_title);
    }

    @Override
    protected void initInjector() {
        getFragmentComponent().inject(this);

    }

    @Override
    protected void attachPresenter() {
        presenter.onAttach(this);

    }

    @Override
    protected void detachPresenter() {
        presenter.onDetach();

    }

    @Override
    protected void init(View view) {
        getViewBinding().btnTaxDetails.setOnClickListener(v -> submitOnClick());
        getViewBinding().imgTax.setOnClickListener(v -> captureImage(REQUEST_CODE_TAX_IMAGE));
        final Calendar newCalendar = Calendar.getInstance();
        final DatePickerDialog datePicker = new DatePickerDialog(getBaseActivity(), (view1, year, monthOfYear, dayOfMonth) -> {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            getViewBinding().etTaxPaidDate.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(newDate.getTime()));
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePicker.getDatePicker().setMaxDate(newCalendar.getTimeInMillis());
        getViewBinding().etTaxPaidDate.setOnClickListener(v -> datePicker.show());

        if (/*AppCacheData.getOurInstance().isAssetUpdate() && */selectedPosition != -1) {
            setEditData();
        }
    }

    public void captureImage(int reqTypeCode) {
        if (getBaseActivity().hasPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) && getBaseActivity().hasPermission(android.Manifest.permission.CAMERA)) {
            getBaseActivity().openImagePicker(this, reqTypeCode);
        } else {
            getBaseActivity().requestPermissionsSafely(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA}, AppConstants.REQUEST_CODE_STORAGE_CAMERA_PERMISSION);
        }
    }


    @Override
    public void onImageCaptured(String path, int reqTypeCode) {
        if (AppCacheData.getOurInstance().getDashboardDetails() != null &&
                AppCacheData.getOurInstance().getDashboardDetails().getLocalBody() != null) {
            presenter.uploadImage(path, AppConstants.IMAGE_PATH_BUILDING_ASSET, AppConstants.IMAGE_PATH_TAX,
                    String.valueOf(AppCacheData.getOurInstance().getDashboardDetails().getLocalBody().getLocalBodyId()));
        }
    }

    @Override
    public void onImageUploadSuccess(String imagePath, String imageType, String response) {
        taxPhoto = response;
        Glide.with(this)
                .load(imagePath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_property_image_place_holder)
                .error(R.mipmap.ic_property_image_place_holder)
                .skipMemoryCache(false)
                .into(getViewBinding().imgTax);
    }


    @Override
    public void onImageRemoved(int reqTypeCode) {
        taxPhoto = null;
        getViewBinding().imgTax.setImageResource(R.mipmap.ic_property_image_place_holder);
    }

    void submitOnClick() {
        String billNumber = Objects.requireNonNull(getViewBinding().etTaxBillNumber.getText()).toString().trim();
        String paidDate = Objects.requireNonNull(getViewBinding().etTaxPaidDate.getText()).toString().trim();
        String paidYear = Objects.requireNonNull(getViewBinding().etTaxPaidYear.getText()).toString().trim();
        String amount = Objects.requireNonNull(getViewBinding().etTaxAmount.getText()).toString().trim();
        String annualTax = Objects.requireNonNull(getViewBinding().etTaxAnnual.getText()).toString().trim();
        String assessmentNumber = Objects.requireNonNull(getViewBinding().etTaxAssessmentNumber.getText()).toString().trim();

        presenter.validateData(billNumber, paidDate, paidYear, amount, annualTax, assessmentNumber, taxPhoto);
    }

    @Override
    public void showTaxDetailsFieldError(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_BILL_NUMBER:
                getViewBinding().layoutTaxBillNumber.setError(error);
                getViewBinding().layoutTaxBillNumber.requestFocus();
                break;
            case ERROR_TYPE_PAID_DATE:
                getViewBinding().layoutTaxPaidDate.setError(error);
                getViewBinding().layoutTaxPaidDate.requestFocus();
                break;
            case ERROR_TYPE_PAID_YEAR:
                getViewBinding().layoutTaxPaidYear.setError(error);
                getViewBinding().layoutTaxPaidYear.requestFocus();
                break;
            case ERROR_TYPE_AMOUNT:
                getViewBinding().layoutTaxAmount.setError(error);
                getViewBinding().layoutTaxAmount.requestFocus();
                break;
            case ERROR_TYPE_ANNUAL:
                getViewBinding().layoutTaxAnnual.setError(error);
                getViewBinding().layoutTaxAnnual.requestFocus();
                break;
            case ERROR_TYPE_ASSESSMENT_NUMBER:
                getViewBinding().layoutTaxAssessmentNumber.setError(error);
                getViewBinding().layoutTaxAssessmentNumber.requestFocus();
                break;
            case ERROR_TYPE_IMAGE:
                showToast(error);
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {
        getViewBinding().layoutTaxBillNumber.setErrorEnabled(false);
        getViewBinding().layoutTaxPaidDate.setErrorEnabled(false);
        getViewBinding().layoutTaxPaidYear.setErrorEnabled(false);
        getViewBinding().layoutTaxAmount.setErrorEnabled(false);
        getViewBinding().layoutTaxAnnual.setErrorEnabled(false);
        getViewBinding().layoutTaxAssessmentNumber.setErrorEnabled(false);
    }

    public void setEditData() {
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getTaxDetails() != null &&
                AppCacheData.getOurInstance().getBuildingAssetData().getTaxDetails().size() > selectedPosition) {
            ArrayList<BuildingAssets.TaxDetails> taxDetails = AppCacheData.getOurInstance().getBuildingAssetData().getTaxDetails();
            BuildingAssets.TaxDetails taxDetail = taxDetails.get(selectedPosition);
            getViewBinding().etTaxBillNumber.setText(taxDetail.getTaxBillNo());
            getViewBinding().etTaxPaidDate.setText(taxDetail.getTaxPaidDate());
            getViewBinding().etTaxPaidYear.setText(String.valueOf(taxDetail.getTaxPaidYear()));
            getViewBinding().etTaxAmount.setText(taxDetail.getTaxAmount());
            getViewBinding().etTaxAnnual.setText(taxDetail.getTaxAnnual());
            getViewBinding().etTaxAssessmentNumber.setText(taxDetail.getAssessmentNo());
            String photo = taxDetail.getTaxPhoto();
            onImageUploadSuccess(AppCacheData.getOurInstance().getImageBaseURL() + "" + photo, null, photo);
        }
    }

    @Override
    public void onAddOrUpdateSuccess() {
        onFragmentBackPressed();
    }
}
