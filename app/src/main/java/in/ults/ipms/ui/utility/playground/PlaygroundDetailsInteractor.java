package in.ults.ipms.ui.utility.playground;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class PlaygroundDetailsInteractor extends BaseInteractor implements IPlaygroundDetailsInteractor {

    @Inject
    public PlaygroundDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
