package in.ults.ipms.ui.buildingassets.tax;

import in.ults.ipms.ui.base.IBasePresenter;

public interface ITaxDetailsPresenter <V extends ITaxDetailsView,I extends ITaxDetailsInteractor> extends IBasePresenter<V,I> {

    void validateData(String billNumber,String paidDate,String paidYear,String amount,String annualTax,String assessmentNumber,String taxPhoto);
}
