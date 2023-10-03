package in.ults.ipms.ui.buildingassets.building;

import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by AmalB on 6/22/2021.
 */

@PerFragment
public interface IBuildingDetailsPresenter<V extends IBuildingDetailsView,I extends IBuildingDetailsInteractor> extends IBasePresenter<V,I> {
    void validateData(String buildingName,String permitNumber,String buildingUnder,String buildingRefId,String wardNumber,String structureType, boolean isLandmark, double locationLatitude, double locationLongitude);
}
