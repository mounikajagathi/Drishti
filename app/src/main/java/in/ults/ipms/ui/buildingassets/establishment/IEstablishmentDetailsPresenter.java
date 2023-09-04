package in.ults.ipms.ui.buildingassets.establishment;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IEstablishmentDetailsPresenter<V extends IEstablishmentDetailsView,I extends IEstablishmentDetailsInteractor> extends IBasePresenter<V,I> {

    void validateData(String establishmentName,String establishmentPost,String establishmentType,String establishmentYear,String mobile,String landphone,String email,String inchargeName,String inchargeRole,String inchargePost,String professionalTaxPaymentStatus,String numberOfEmployees,String license,String licenseNumber,String gst,String gstStatus);
}
