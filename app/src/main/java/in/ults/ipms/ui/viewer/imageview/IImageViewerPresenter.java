package in.ults.ipms.ui.viewer.imageview;

import in.ults.ipms.di.PerActivity;
import in.ults.ipms.ui.base.IBasePresenter;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

@PerActivity
public interface IImageViewerPresenter<V extends IImageViewerView, I extends IImageViewerInteractor> extends IBasePresenter<V,I> {

}
