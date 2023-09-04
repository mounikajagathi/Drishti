package in.ults.ipms.ui.utility.road.details;

import in.ults.ipms.data.network.model.response.GeomPolyLine;
import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

@PerFragment
public interface IRoadDetailsPresenter<V extends IRoadDetailsView,I extends IRoadDetailsInteractor> extends IBasePresenter<V,I> {
    void saveRoadDetails(String roadName, String startPoint, String endPoint, String roadMaterial, String roadCategory, String maintainedBy, String length, String carriageWidth, String roadwayWidth, String footpathWidth, String rightWayWidth, String remarks, String photo, GeomPolyLine geom);
}
