package in.ults.ipms.ui.utility.transformer;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class TransformerDetailsInteractor extends BaseInteractor implements ITransformerDetailsInteractor {

    @Inject
    public TransformerDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
