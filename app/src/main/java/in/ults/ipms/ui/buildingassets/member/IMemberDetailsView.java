package in.ults.ipms.ui.buildingassets.member;

import in.ults.ipms.ui.base.IBaseView;

public interface IMemberDetailsView extends IBaseView {

    void showMemberDetailsFieldError(int errorType, String error);
    void clearErrors();
    void onAddOrUpdateSuccess();
}
