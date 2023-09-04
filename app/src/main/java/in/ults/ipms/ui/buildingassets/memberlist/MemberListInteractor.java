package in.ults.ipms.ui.buildingassets.memberlist;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class MemberListInteractor extends BaseInteractor implements IMemberListInteractor {

    @Inject
    public MemberListInteractor(DataManager dataManager) {
        super(dataManager);
    }

}
