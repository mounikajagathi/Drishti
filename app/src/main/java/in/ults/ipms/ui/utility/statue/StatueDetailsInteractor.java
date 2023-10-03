package in.ults.ipms.ui.utility.statue;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class StatueDetailsInteractor extends BaseInteractor implements IStatueDetailsInteractor {

    @Inject
    public StatueDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
