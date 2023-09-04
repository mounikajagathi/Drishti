package in.ults.ipms.ui.buildingassets.otherproperty;

import in.ults.ipms.ui.base.IBasePresenter;

public interface IPropertyOtherDetailsPresenter <V extends IPropertyOtherDetailsView,I extends IPropertyOtherDetailsInteractor> extends IBasePresenter<V,I> {

    void validateData(String buildingSubCategory,String electricityConnection,String consumerNumber,String wasteManagement,String bathroom,String latrine,String parkingArea,String rainWaterHarvesting,String gasConnection,String waterSource,String waterConnection,String waterConnectionType,String well,String waterInWell,String solarPanel,String deathIn1Year,String deathCount,String deathCause,String rationCardColor,String rationCardNumber,String cattle,String cattleDetails,String poultry,String poultryDetails,String religion,String caste,String governmentHealthCard,String arStatus,String ramp);
}
