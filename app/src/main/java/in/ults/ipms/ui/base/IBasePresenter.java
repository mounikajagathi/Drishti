package in.ults.ipms.ui.base;

import in.ults.ipms.data.network.model.request.DeleteAssetDataRequest;
import in.ults.ipms.data.network.model.response.FetchAssetDataResponse;

/**
 * Created by Mohammed Shafeeq on 3/20/2018.
 */

public interface IBasePresenter<V extends IBaseView, I extends IBaseInteractor>{


    void onAttach(V mvpView);

    void onDetach();

    V getMvpView();

    I getInteractor();

    boolean isViewAttached();

    void checkViewAttached() throws BasePresenter.MvpViewNotAttachedException;

    boolean isUserLoggedIn();

    void logoutUser();

    void uploadImage(String imagePath, String mainPath, String subPath, String localBodyId);

    void fetchBuildAssetData(String id,String app, String layer);

    void saveAssetDetails(FetchAssetDataResponse.Data data);

    void deleteAssetDetails(int type, DeleteAssetDataRequest request, int position);



}
