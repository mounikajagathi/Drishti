package in.ults.ipms.ui.dashboard.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.databinding.BottomSheetSettingsBinding;
import in.ults.ipms.ui.base.BaseBottomSheet;

/**
 * Created by Mohammed Shafeeq on 21/03/17.
 */

public class SettingsBottomSheet extends BaseBottomSheet<BottomSheetSettingsBinding> implements ISettingsBottomSheetView {

    public static final String TAG = SettingsBottomSheet.class.getSimpleName();

    @Inject
    ISettingsBottomSheetPresenter<ISettingsBottomSheetView, ISettingsBottomSheetInteractor> presenter;


    @Override
    protected BottomSheetSettingsBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return BottomSheetSettingsBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected void initInjector() {
        getBottomSheetComponent().inject(this);
    }

    @Override
    protected void attachPresenter() {
        presenter.onAttach(this);
    }

    @Override
    protected void detachPresenter() {
        presenter.onDetach();
    }

    public static SettingsBottomSheet newInstance() {
        SettingsBottomSheet bottomSheet = new SettingsBottomSheet();
        bottomSheet.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomSheetDialogStyle);
        return bottomSheet;
    }


    @Override
    protected void init(View view) {
        getViewBinding().includeDial0gHeader.txtDialogTitle.setText("Settings");
        getViewBinding().includeDial0gHeader.btnCloseDialog.setOnClickListener(view1 -> dismiss());
        getViewBinding().btnLogout.setOnClickListener(view12 -> presenter.logoutUser());
    }


}