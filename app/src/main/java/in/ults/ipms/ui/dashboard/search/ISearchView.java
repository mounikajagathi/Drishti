package in.ults.ipms.ui.dashboard.search;

import java.util.ArrayList;

import in.ults.ipms.data.network.model.response.SearchDetailsResponse;
import in.ults.ipms.ui.base.IBaseView;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public interface ISearchView extends IBaseView {

   void onSearchSuccess(ArrayList<String> data);
   void onSearchDetailsSuccess(String featureType, ArrayList<SearchDetailsResponse.Features> properties);

}
