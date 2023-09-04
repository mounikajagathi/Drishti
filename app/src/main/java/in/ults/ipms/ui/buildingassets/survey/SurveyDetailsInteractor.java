package in.ults.ipms.ui.buildingassets.survey;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

public class SurveyDetailsInteractor extends BaseInteractor implements ISurveyDetailsInteractor {

    @Inject
    public SurveyDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
