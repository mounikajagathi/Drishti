package in.ults.ipms.ui.waterbody.pond;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class PondDetailsInteractor extends BaseInteractor implements IPondDetailsInteractor {

    @Inject
    public PondDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
