package in.ults.ipms.ui.utility.garbage;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class GarbageDetailsInteractor extends BaseInteractor implements IGarbageDetailsInteractor {

    @Inject
    public GarbageDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
