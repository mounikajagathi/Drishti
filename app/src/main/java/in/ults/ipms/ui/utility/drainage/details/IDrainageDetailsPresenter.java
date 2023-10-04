package in.ults.ipms.ui.utility.drainage.details;

import in.ults.ipms.data.network.model.response.GeomPolyLine;
import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

@PerFragment
public interface IDrainageDetailsPresenter<V extends IDrainageDetailsView,I extends IDrainageDetailsInteractor> extends IBasePresenter<V,I> {
    void validateData(String name, String place, String material, String type,String length,String drainageLength, String wardNumber, String remarks, String photo, GeomPolyLine geom);
}
