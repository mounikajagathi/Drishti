package in.ults.ipms.ui.viewer.imageview;

import javax.inject.Inject;

import in.ults.ipms.data.DataManager;
import in.ults.ipms.ui.base.BaseInteractor;

/**
 * Created by Mohammed Shafeeq on 22/03/18.
 */

public class ImageViewerInteractor extends BaseInteractor implements IImageViewerInteractor {

    @Inject
    public ImageViewerInteractor(DataManager dataManager) {
        super(dataManager);
    }
}
