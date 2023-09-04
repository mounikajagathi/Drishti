package in.ults.ipms.ui.buildingassets.floorandroof;

import java.util.ArrayList;

import in.ults.ipms.adapters.FloorAreaAdapter;
import in.ults.ipms.adapters.RoofTypeAdapter;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by AmalB on 6/22/2021.
 */

@PerFragment
public interface IFloorAndRoofDetailsPresenter<V extends IFloorAndRoofDetailsView, I extends IFloorAndRoofDetailsInteractor> extends IBasePresenter<V, I> {

    void validateData(ArrayList<BuildingAssets.FloorType> floorType, FloorAreaAdapter floorAreaAdapter, RoofTypeAdapter roofTypeAdapter);
}
