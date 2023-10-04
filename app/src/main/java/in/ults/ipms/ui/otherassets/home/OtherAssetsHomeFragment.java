package in.ults.ipms.ui.otherassets.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.OtherAssetsHomeAdapter;
import in.ults.ipms.databinding.FragmentOtherAssetsHomeBinding;
import in.ults.ipms.ui.base.BaseFragment;

public class OtherAssetsHomeFragment extends BaseFragment<FragmentOtherAssetsHomeBinding> implements IOtherAssetsHomeView {

    public static final String TAG = OtherAssetsHomeFragment.class.getSimpleName();

    @Inject
    IOtherAssetsHomePresenter<IOtherAssetsHomeView, IOtherAssetsHomeInteractor> presenter;

    @Inject
    OtherAssetsHomeAdapter otherAssetsHomeAdapter;


    public static OtherAssetsHomeFragment newInstance() {
        return new OtherAssetsHomeFragment();
    }

    @Override
    protected FragmentOtherAssetsHomeBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentOtherAssetsHomeBinding.inflate(inflater, container, attachToParent);
    }

    @Override
    protected void initInjector() {
        getFragmentComponent().inject(this);
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
    protected String getToolbarTitle() {
        return getString(R.string.other_assets_title);
    }

    @Override
    protected void init(View view) {
        getViewBinding().rvOtherAssetsHome.setAdapter(otherAssetsHomeAdapter);
        String[] waterBodyArray = getResources().getStringArray(R.array.other_assets);
        otherAssetsHomeAdapter.setLocalDataSet(waterBodyArray);
        otherAssetsHomeAdapter.setClickListener((position, v) -> {
            switch (position) {
                case 0:
                    getBaseActivity().launchHighLowMastLight(true, false);
                    return;
            }
        });
    }


}
