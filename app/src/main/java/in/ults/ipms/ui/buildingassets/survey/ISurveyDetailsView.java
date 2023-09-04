package in.ults.ipms.ui.buildingassets.survey;

import in.ults.ipms.ui.base.IBaseView;

public interface ISurveyDetailsView extends IBaseView {

    void showSurveyDetailsFieldError(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess();
}
