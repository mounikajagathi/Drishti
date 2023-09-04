package in.ults.ipms.ui.dashboard.gotolocation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.databinding.DialogGoToLocationBinding;
import in.ults.ipms.ui.base.BaseDialog;

/**
 * Created by Mohammed Shafeeq on 21/03/17.
 */

public class GoToLocationDialog extends BaseDialog<DialogGoToLocationBinding> implements IGoToLocationDialogView {

    public static final String TAG = GoToLocationDialog.class.getSimpleName();


    private OnLatLonListener onLatLonListener;

    public static final int ERROR_TYPE_LATITUDE = 1;
    public static final int ERROR_TYPE_LONGITUDE = 2;

    @Inject
    IGoToLocationDialogPresenter<IGoToLocationDialogView, IGoToLocationDialogInteractor> presenter;

    public static GoToLocationDialog newInstance(OnLatLonListener onLatLonListener) {
        GoToLocationDialog dialog = new GoToLocationDialog();
        dialog.setOnLatLonListener(onLatLonListener);
        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected DialogGoToLocationBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return DialogGoToLocationBinding .inflate(inflater, container, attachToParent);
    }

    @Override
    protected void initInjector() {
        getDialogComponent().inject(this);
    }

    @Override
    protected void attachPresenter() {
        presenter.onAttach(this);
    }

    @Override
    protected void detachPresenter() {
        presenter.onDetach();
    }

    @Override
    protected void init(View view) {
        super.init(view);
        getViewBinding().includeDial0gHeader.txtDialogTitle.setText(getResources().getString(R.string.dashboard_go_to_location));
        getViewBinding().includeDial0gHeader.btnCloseDialog.setOnClickListener(view1 -> dismissDialog(TAG));
        getViewBinding().btnSubmit.setOnClickListener(view1 -> {
            hideKeyboard();
            String latitude = getViewBinding().etLatitude.getText().toString();
            String longitude = getViewBinding().etLongitude.getText().toString();
            if (presenter.validateLatLong(latitude, longitude) &&
                    onLatLonListener != null) {
                onLatLonListener.onGoToLocationSuccess(Double.parseDouble(latitude), Double.parseDouble(longitude));
                dismissDialog(TAG);
            }
        });



    }

    public void setOnLatLonListener(OnLatLonListener onLatLonListener) {
        this.onLatLonListener = onLatLonListener;
    }

    public interface OnLatLonListener {
        void onGoToLocationSuccess(double latitude, double longitude);
    }

    @Override
    public void showErrors(int errorType, String error) {
        switch (errorType) {
            case ERROR_TYPE_LATITUDE:
                getViewBinding().etLatitude.setError(error);
                getViewBinding().etLatitude.requestFocus();
                break;
            case ERROR_TYPE_LONGITUDE:
                getViewBinding().etLongitude.setError(error);
                getViewBinding().etLongitude.requestFocus();
                break;
            default:
                break;
        }
    }

    @Override
    public void clearErrors() {
        getViewBinding().etLatitude.setError(null);
        getViewBinding().etLongitude.setError(null);
    }
}