package in.ults.ipms.ui.dashboard.measure;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.databinding.DialogMeasurementBinding;
import in.ults.ipms.ui.base.BaseDialog;

/**
 * Created by Mohammed Shafeeq on 21/03/17.
 */

public class MeasureDialog extends BaseDialog<DialogMeasurementBinding> implements IMeasureDialogView {

    public static final String TAG = MeasureDialog.class.getSimpleName();

    private GeometryListener geometryListener;

    public static final int GEOMETRY_TYPE_POLYLINE = 1;
    public static final int GEOMETRY_TYPE_POLYGON = 2;
    public static final int GEOMETRY_TYPE_CIRCLE = 3;

    @Inject
    IMeasureDialogPresenter<IMeasureDialogView, IMeasureDialogInteractor> presenter;

    public static MeasureDialog newInstance(GeometryListener geometryListener) {
        MeasureDialog dialog = new MeasureDialog();
        dialog.setGeometryListener(geometryListener);
        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected DialogMeasurementBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return DialogMeasurementBinding .inflate(inflater, container, attachToParent);
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
        getViewBinding().includeDial0gHeader.txtDialogTitle.setText(getResources().getString(R.string.dashboard_measurement));
        getViewBinding().includeDial0gHeader.btnCloseDialog.setOnClickListener(view1 -> dismissDialog(TAG));
        getViewBinding().fabPolygon.setOnClickListener(view1 -> {
            if(geometryListener!=null){
                geometryListener.onGeometryClick(GEOMETRY_TYPE_POLYGON);
            }
            dismissDialog(TAG);
        });

        getViewBinding().fabPolyline.setOnClickListener(view1 -> {
            if(geometryListener!=null){
                geometryListener.onGeometryClick(GEOMETRY_TYPE_POLYLINE);
            }
            dismissDialog(TAG);
        });

        getViewBinding().fabCircle.setOnClickListener(view1 -> {
            if(geometryListener!=null){
                geometryListener.onGeometryClick(GEOMETRY_TYPE_CIRCLE);
            }
            dismissDialog(TAG);
        });
    }

    public void setGeometryListener(GeometryListener geometryListener) {
        this.geometryListener = geometryListener;
    }

    public interface GeometryListener {
        void onGeometryClick(int type);
    }
}