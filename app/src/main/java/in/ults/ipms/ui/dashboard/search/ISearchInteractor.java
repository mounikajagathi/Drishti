package in.ults.ipms.ui.dashboard.search;

import in.ults.ipms.data.network.model.response.SearchDetailsResponse;
import in.ults.ipms.data.network.model.response.SearchResponse;
import in.ults.ipms.ui.base.IBaseInteractor;
import io.reactivex.Observable;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public interface ISearchInteractor extends IBaseInteractor {

    Observable<SearchResponse> searchData(String searchFilter, String searchValue);
    Observable<SearchDetailsResponse> searchDetails(String searchFilter, String searchValue);

}
