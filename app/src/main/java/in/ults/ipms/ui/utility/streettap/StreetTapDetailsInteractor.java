package in.ults.ipms.ui.utility.streettap;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class StreetTapDetailsInteractor extends BaseInteractor implements IStreetTapDetailsInteractor {

    @Inject
    public StreetTapDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
