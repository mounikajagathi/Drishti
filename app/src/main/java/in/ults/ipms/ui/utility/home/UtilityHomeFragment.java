package in.ults.ipms.ui.utility.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
                case 10:
                    getBaseActivity().launchBusBayMap(true, false);
                    return;
                case 11:
                    getBaseActivity().launchBusStandMap(true, false);
                    return;
                case 12:
                    getBaseActivity().launchBusStopMap(true, false);
                    return;
                case 13:
                    getBaseActivity().launchCanalDetails(true, false);
                    return;
                case 14:
                    getBaseActivity().launchCanalLineDetails(true, false);
                    return;
                case 15:
                    getBaseActivity().launchGarbageDetails(true, false);
                    return;
                case 16:
                    getBaseActivity().launchMobileTowerDetails(true, false);
                    return;
                case 17:
                    getBaseActivity().launchParkDetails(true, false);
                    return;
                case 18:
                    getBaseActivity().launchStadiumDetails(true, false);
                    return;
                case 19:
                    getBaseActivity().launchStatueDetails(true, false);
                    return;
                case 20:
                    getBaseActivity().launchStreetTapDetails(true, false);
                    return;
                case 21:
                    getBaseActivity().launchTankDetails(true, false);
                    return;
                case 22:
                    getBaseActivity().launchTaxiStandDetails(true, false);
                    return;
                case 23:
                    getBaseActivity().launchTransformerDetails(true, false);
                    return;
                case 24:
                    getBaseActivity().launchWellDetails(true, false);
                    return;

            }
        });
    }


}
