package in.ults.ipms.ui.dashboard.search;

import in.ults.ipms.di.PerActivity;
import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

@PerActivity
public interface ISearchPresenter<V extends ISearchView, I extends ISearchInteractor> extends IBasePresenter<V, I> {

    void searchData(String searchFilter, String searchValue);

    void searchDetails(String searchFilter, String searchValue);

}
