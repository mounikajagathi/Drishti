package in.ults.ipms.ui.dashboard.gotolocation;
import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.base.BasePresenter;
import in.ults.ipms.utils.common.CommonUtils;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 22/03/17.
 */

public class GoToLocationDialogPresenter<V extends IGoToLocationDialogView,
        I extends IGoToLocationDialogInteractor> extends BasePresenter<V, I>
        implements IGoToLocationDialogPresenter<V, I> {

    private final BaseActivity baseActivity;


    @Inject
    GoToLocationDialogPresenter(I mvpInteractor,
                                SchedulerProvider schedulerProvider,
                                CompositeDisposable compositeDisposable, BaseActivity baseActivity) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
        this.baseActivity = baseActivity;
    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public boolean validateLatLong(String latitude, String longitude) {
        getMvpView().clearErrors();
        if (CommonUtils.isNullString(longitude)) {
            getMvpView().showErrors(GoToLocationDialog.ERROR_TYPE_LONGITUDE,
                    baseActivity.getResources().getString(R.string.err_longitude));
            return false;
        }

        if (CommonUtils.isNullString(latitude)) {
            getMvpView().showErrors(GoToLocationDialog.ERROR_TYPE_LATITUDE,
                    baseActivity.getResources().getString(R.string.err_latitude));
            return false;
        }
        return true;
    }

}
