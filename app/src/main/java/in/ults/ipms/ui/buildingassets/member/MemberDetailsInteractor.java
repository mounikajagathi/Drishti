package in.ults.ipms.ui.buildingassets.member;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class MemberDetailsInteractor extends BaseInteractor implements IMemberDetailsInteractor {

    @Inject
    public MemberDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
