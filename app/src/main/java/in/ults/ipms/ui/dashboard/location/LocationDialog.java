package in.ults.ipms.ui.dashboard.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.databinding.DialogLocationBinding;
import in.ults.ipms.databinding.DialogMeasurementBinding;
import in.ults.ipms.ui.base.BaseDialog;

/**
 * Created by Mohammed Shafeeq on 21/03/17.
 */

public class LocationDialog extends BaseDialog<DialogLocationBinding> implements ILocationDialogView {

    public static final String TAG = LocationDialog.class.getSimpleName();

    private LocationListener locationListener;

    public static final int LOCATION_TYPE_GO_TO_XY = 1;
    public static final int LOCATION_TYPE_CURRENT = 2;

    @Inject
    ILocationDialogPresenter<ILocationDialogView, ILocationDialogInteractor> presenter;

    public static LocationDialog newInstance(LocationListener locationListener) {
        LocationDialog dialog = new LocationDialog();
        dialog.setLocationListener(locationListener);
        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected DialogLocationBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return DialogLocationBinding .inflate(inflater, container, attachToParent);
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
        getViewBinding().includeDial0gHeader.txtDialogTitle.setText(getResources().getString(R.string.dashboard_location));
        getViewBinding().includeDial0gHeader.btnCloseDialog.setOnClickListener(view1 -> dismissDialog(TAG));
        getViewBinding().fabGoToXY.setOnClickListener(view1 -> {
            dismissDialog(TAG);
            if(locationListener!=null){
                locationListener.onLocationClick(LOCATION_TYPE_GO_TO_XY);
            }
        });

        getViewBinding().fabCurrentLocation.setOnClickListener(view1 -> {
            dismissDialog(TAG);
            if(locationListener!=null){
                locationListener.onLocationClick(LOCATION_TYPE_CURRENT);
            }
        });
    }

    public void setLocationListener(LocationListener locationListener) {
        this.locationListener = locationListener;
    }

    public interface LocationListener {
        void onLocationClick(int type);
    }
}