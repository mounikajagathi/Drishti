package in.ults.ipms.ui.utility.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.UtilityHomeAdapter;
import in.ults.ipms.databinding.FragmentUtilityHomeBinding;
import in.ults.ipms.ui.base.BaseFragment;

public class UtilityHomeFragment extends BaseFragment<FragmentUtilityHomeBinding> implements IUtilityHomeView {

    public static final String TAG = UtilityHomeFragment.class.getSimpleName();

    @Inject
    IUtilityHomePresenter<IUtilityHomeView, IUtilityHomeInteractor> presenter;

    @Inject
    UtilityHomeAdapter utilityHomeAdapter;

    @Inject
    GridLayoutManager layoutManager;


    public static UtilityHomeFragment newInstance() {
        return new UtilityHomeFragment();
    }

    @Override
    protected FragmentUtilityHomeBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToParent) {
        return FragmentUtilityHomeBinding.inflate(inflater, container, attachToParent);
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
        return getString(R.string.utility_title);
    }

    @Override
    protected void init(View view) {
        getViewBinding().rvUtilityHome.setLayoutManager(layoutManager);
        getViewBinding().rvUtilityHome.setAdapter(utilityHomeAdapter);
        String[] utilityArray = getResources().getStringArray(R.array.utility);
        utilityHomeAdapter.setLocalDataSet(utilityArray);
        utilityHomeAdapter.setClickListener((position, v) -> {
            switch (position) {
                case 0:
                    getBaseActivity().launchRoadMap(true, false);
                    return;
                case 1:
                    getBaseActivity().launchBridgeDetails(true, false);
                    return;
                case 2:
                    getBaseActivity().launchCulvertDetails(true, false);
                    return;
                case 3:
                    getBaseActivity().launchDividerDetails(true, false);
                    return;
                case 4:
                    getBaseActivity().launchDrainageMap(true, false);
                    return;
                case 5:
                    getBaseActivity().launchPlaygroundDetails(true, false);
                    return;
                case 6:
                    getBaseActivity().launchParkingDetails(true, false);
                    return;
                case 7:
                    getBaseActivity().launchRoadHumpMap(true, false);
                    return;
                case 8:
                    getBaseActivity().launchRoadJunctionMap(true, false);
                    return;
                case 9:
                    getBaseActivity().launchRoadSignboardMap(true, false);
                    return;
            }
        });
    }


}
