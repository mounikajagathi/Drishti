package in.ults.ipms.ui.buildingassets.property;

import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by AmalB on 6/22/2021.
 */

@PerFragment
public interface IPropertyDetailsPresenter <V extends IPropertyDetailsView, I extends IPropertyDetailsInteractor> extends IBasePresenter<V, I> {

    void getBuildingNameSpinnerData();
    void validateData(String buildingName, String buildingCondition, String doorStatus, String whichFloor, String buildingStatus, String buildingUsage, String newProId, String oldProId, String yearOfConstruction, String NumberOfYears, String anyInstitute, String centralisedAc, String normalAc, String anyStructuralChange, String higherFloorTypeGt250Sqmts, String anyOtherLowerFloorType, String photo1, String photo2, String plan);
    void uploadImage(String imagePath, String mainPath, String subPath, String localBodyId);
}
