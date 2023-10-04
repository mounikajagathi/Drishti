package in.ults.ipms.ui.buildingassets.tax;

import static in.ults.ipms.ui.buildingassets.tax.TaxDetailsFragment.selectedPosition;

import java.util.ArrayList;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class TaxDetailsPresenter<V extends ITaxDetailsView, I extends ITaxDetailsInteractor>
        extends BasePresenter<V, I> implements ITaxDetailsPresenter<V, I> {

    private final BaseActivity baseActivity;

    @Inject
    public TaxDetailsPresenter(I mMvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mMvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }

    public void validateData(String billNumber, String paidDate, String paidYear, String amount,String annualTax, String assessmentNumber, String taxPhoto) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(billNumber)) {
            getMvpView().showTaxDetailsFieldError(TaxDetailsFragment.ERROR_TYPE_BILL_NUMBER, baseActivity.getResources().getString(R.string.err_bill_number));
            return;
        }
        if (CommonUtils.isNullString(paidDate)) {
            getMvpView().showTaxDetailsFieldError(TaxDetailsFragment.ERROR_TYPE_PAID_DATE, baseActivity.getResources().getString(R.string.err_paid_date));
            return;
        }
        if (CommonUtils.isNullString(paidYear)) {
            getMvpView().showTaxDetailsFieldError(TaxDetailsFragment.ERROR_TYPE_PAID_YEAR, baseActivity.getResources().getString(R.string.err_paid_year));
            return;
        }
        if (CommonUtils.isNullString(amount)) {
            getMvpView().showTaxDetailsFieldError(TaxDetailsFragment.ERROR_TYPE_AMOUNT, baseActivity.getResources().getString(R.string.err_tax_amount));
            return;
        }
        if (CommonUtils.isNullString(annualTax)) {
            getMvpView().showTaxDetailsFieldError(TaxDetailsFragment.ERROR_TYPE_ANNUAL, baseActivity.getResources().getString(R.string.err_tax_annual));
            return;
        }
        if (CommonUtils.isNullString(assessmentNumber)) {
            getMvpView().showTaxDetailsFieldError(TaxDetailsFragment.ERROR_TYPE_ASSESSMENT_NUMBER, baseActivity.getResources().getString(R.string.err_assessment_number));
            return;
        }
        if (CommonUtils.isNullString(taxPhoto)) {
            getMvpView().showTaxDetailsFieldError(TaxDetailsFragment.ERROR_TYPE_IMAGE, baseActivity.getResources().getString(R.string.err_tax_image));
            return;
        }

        savingTaxDetails(billNumber, paidDate, paidYear, amount,annualTax, assessmentNumber, taxPhoto);
    }

    public void savingTaxDetails(String billNumber, String paidDate, String paidYear, String amount,String annualTax, String assessmentNumber, String photo) {
            if (AppCacheData.getOurInstance().isAssetUpdate()) {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = new FetchAssetDataResponse.Data();
                ArrayList<BuildingAssets.TaxDetails> taxList = AppCacheData.getOurInstance().getBuildingAssetData().getTaxDetails();
                data.setTaxDetails(taxList == null ? new ArrayList<>() : new ArrayList<>(taxList));
                BuildingAssets.TaxDetails taxDetails;
                if (selectedPosition == -1) {
                    taxDetails = new BuildingAssets.TaxDetails();
                    taxDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                    BuildingAssets.PropertyDetails propertyDetails = AppCacheData.getOurInstance().getBuildingAssetData().getPropertyDetails();
                    if (propertyDetails != null) {
                        taxDetails.setProperty(propertyDetails.getPk());
                    }
                } else {
                    taxDetails = data.getTaxDetails().get(selectedPosition);
                    taxDetails.setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                }
                taxDetails.setTaxBillNo(billNumber);
                taxDetails.setTaxPaidDate(paidDate);
                taxDetails.setTaxPaidYear(Integer.parseInt(paidYear));
                taxDetails.setTaxAmount(amount);
                taxDetails.setTaxAnnual(annualTax);
                taxDetails.setAssessmentNo(assessmentNumber);
                taxDetails.setTaxPhoto(photo);

                if (selectedPosition == -1) {
                    data.getTaxDetails().add(taxDetails);
                } else {
                    data.getTaxDetails().set(selectedPosition, taxDetails);
                }
                saveAssetDetails(data);
            }
        } else {
            if (AppCacheData.getOurInstance().getBuildingAssetData() != null) {
                FetchAssetDataResponse.Data data = AppCacheData.getOurInstance().getBuildingAssetData();
                ArrayList<BuildingAssets.TaxDetails> taxDetails;
                if(data.getTaxDetails()==null){
                    taxDetails = new ArrayList<>();
                }else{
                    taxDetails = new ArrayList<>(data.getTaxDetails());
                }
                BuildingAssets.TaxDetails tax;
                if(selectedPosition != -1 && taxDetails.size()>selectedPosition) {
                    tax = taxDetails.get(selectedPosition);
                    tax.setUpdationStatus(AppConstants.UPDATION_STATUS_UPDATE);
                }else{
                    tax = new BuildingAssets.TaxDetails();
                    tax.setUpdationStatus(AppConstants.UPDATION_STATUS_ADD);
                    if (data.getPropertyDetails() != null) {
                        tax.setProperty(data.getPropertyDetails().getPk());
                    }
                }
                tax.setTaxBillNo(billNumber);
                tax.setTaxPaidDate(paidDate);
                tax.setTaxPaidYear(Integer.parseInt(paidYear));
                tax.setTaxAmount(amount);
                tax.setTaxAnnual(annualTax);
                tax.setAssessmentNo(assessmentNumber);
                tax.setTaxPhoto(photo);
                if (selectedPosition == -1) {
                    taxDetails.add(tax);
                } else {
                    taxDetails.set(selectedPosition, tax);
                }
                FetchAssetDataResponse.Data newData = new FetchAssetDataResponse.Data();
                newData.setTaxDetails(taxDetails);
                saveAssetDetails(newData);
            }
        }
    }

    @Override
    public void onSaveAssetSuccess(FetchAssetDataResponse response) {
        if (!isViewAttached()) {
            return;
        }
        if (AppCacheData.getOurInstance().getBuildingAssetData() != null
                && response.getData() != null && response.getData().getTaxDetails() != null) {
            ArrayList<BuildingAssets.TaxDetails> taxDetails = AppCacheData.getOurInstance().getBuildingAssetData().getTaxDetails();
            if(taxDetails==null){
                taxDetails = new ArrayList<>();
            }
            if(selectedPosition == -1 && response.getData().getTaxDetails().size() > 0){
                taxDetails.add(response.getData().getTaxDetails().get(0));
                AppCacheData.getOurInstance().getBuildingAssetData().setTaxDetails(taxDetails);
            }else if(taxDetails.size() > selectedPosition  && response.getData().getTaxDetails().size() > 0){
                taxDetails.remove(selectedPosition);
                taxDetails.add(selectedPosition,response.getData().getTaxDetails().get(0));
                AppCacheData.getOurInstance().getBuildingAssetData().setTaxDetails(taxDetails);
            }
        }
        getMvpView().showToast(response.getMessage());
        getMvpView().hideProgressDialog();
        getMvpView().onAddOrUpdateSuccess();
    }
}
