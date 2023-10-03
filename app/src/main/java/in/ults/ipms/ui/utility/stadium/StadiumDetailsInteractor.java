package in.ults.ipms.ui.utility.stadium;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class StadiumDetailsInteractor extends BaseInteractor implements IStadiumDetailsInteractor {

    @Inject
    public StadiumDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
