package in.ults.ipms.ui.buildingassets.floorandroof;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by AmalB on 6/22/2021.
 */

public class FloorAndRoofDetailsInteractor extends BaseInteractor implements IFloorAndRoofDetailsInteractor {

    @Inject
    public FloorAndRoofDetailsInteractor(DataManager dataManager) {
        super(dataManager);
    }

}
