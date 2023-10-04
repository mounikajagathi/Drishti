package in.ults.ipms.ui.buildingassets.owner;

import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

@PerFragment
public interface IOwnerDetailsPresenter <V extends IOwnerDetailsView,I extends IOwnerDetailsInteractor> extends IBasePresenter<V,I> {

    void validateData(String firstName, String lastName,String nameSanjaya, String email, String mobile, String landphone, String gender, String placeName, String village, String street, String ownerHouse, String ownerOccupation, String ownerState,String ownerPostoffice,String ownerPincode, String ownerSurveyNumber);
}
