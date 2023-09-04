package in.ults.ipms.ui.dashboard.search;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.data.network.model.response.SearchDetailsResponse;
import in.ults.ipms.data.network.model.response.SearchResponse;
import in.ults.ipms.ui.base.BaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class SearchInteractor extends BaseInteractor implements ISearchInteractor {

    @Inject
    public SearchInteractor(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Observable<SearchResponse> searchData(String searchFilter, String searchValue) {
        return getDataManager().searchData(getDataManager().getAccessToken(), searchFilter, searchValue);
    }

    @Override
    public Observable<SearchDetailsResponse> searchDetails(String searchFilter, String searchValue) {
        return getDataManager().searchDetails(getDataManager().getAccessToken(), searchFilter, searchValue);
    }
}
