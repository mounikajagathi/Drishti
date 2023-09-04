package in.ults.ipms.ui.buildingassets.survey;

import in.ults.ipms.ui.base.IBasePresenter;

public interface ISurveyDetailsPresenter <V extends ISurveyDetailsView,I extends ISurveyDetailsInteractor> extends IBasePresenter<V,I> {

    void validateData(String informedBy,String neighbourName,String surveyor,String remarks,String oldProIdRemark,String newProIdRemark,String cooperation,String ownershipChange);
}
