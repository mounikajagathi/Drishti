package in.ults.ipms.ui.waterbody.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.WaterBodyHomeAdapter;
import in.ults.ipms.databinding.FragmentWaterbodyHomeBinding;
import in.ults.ipms.ui.base.BaseFragment;

public class WaterBodyHomeFragment extends BaseFragment<FragmentWaterbodyHomeBinding> implements IWaterBodyHomeView {

    public static final String TAG = WaterBodyHomeFragment.class.getSimpleName();

    @Inject
    IWaterBodyHomePresenter<IWaterBodyHomeView, IWaterBodyHomeInteractor> presenter;

    @Inject
    WaterBodyHomeAdapter waterBodyHomeAdapter;


    public static WaterBodyHomeFragment newInstance() {
        return new WaterBodyHomeFragment();
    }

    @Override
    protected FragmentWaterbodyHomeBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentWaterbodyHomeBinding.inflate(inflater, container, attachToParent);
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
        return getString(R.string.water_body_title);
    }

    @Override
    protected void init(View view) {
        getViewBinding().rvWaterBodyHome.setAdapter(waterBodyHomeAdapter);
        String[] waterBodyArray = getResources().getStringArray(R.array.water_body);
        waterBodyHomeAdapter.setLocalDataSet(waterBodyArray);
        waterBodyHomeAdapter.setClickListener((position, v) -> {
            switch (position) {
                case 0:
                    getBaseActivity().launchPondDetails(true, false);
                    return;
            }
        });
    }


}
