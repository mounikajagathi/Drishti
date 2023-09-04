package in.ults.ipms.ui.utility.divider;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class DividerDetailsInteractor extends BaseInteractor implements IDividerDetailsInteractor {

    @Inject
    public DividerDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
