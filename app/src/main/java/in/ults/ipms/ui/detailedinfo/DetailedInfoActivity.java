package in.ults.ipms.ui.detailedinfo;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;

import javax.inject.Inject;

import in.ults.ipms.R;
import in.ults.ipms.adapters.DetailInfoAdapter;
import in.ults.ipms.data.network.model.response.FeatureDataResponse;
import in.ults.ipms.databinding.ActivityDetailedInfoBinding;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.buildingassets.BuildingAssetsActivity;
import in.ults.ipms.ui.otherassets.OtherAssetsActivity;
import in.ults.ipms.ui.utility.UtilityActivity;
import in.ults.ipms.ui.waterbody.WaterBodyActivity;
import in.ults.ipms.utils.AppConstants;


public class DetailedInfoActivity extends BaseActivity<ActivityDetailedInfoBinding> implements IDetailedInfoView {
    public static final String TAG = DetailedInfoActivity.class.getSimpleName();


    @Inject
    IDetailedInfoPresenter<IDetailedInfoView, IDetailedInfoInteractor> presenter;

    @Inject
    DetailInfoAdapter detailInfoAdapter;

    private FeatureDataResponse.Data data;

    @Override
    protected int getStatusBarColor() {
        return ContextCompat.getColor(this, R.color.colorRed);
    }


    @Override
    protected ActivityDetailedInfoBinding initViewBinding() {
        return ActivityDetailedInfoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initInjector() {
        getActivityComponent().inject(this);
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
    protected void configureToolbar() {
        setSupportActionBar(getViewBinding().mainPageToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        getViewBinding().mainPageToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detailed_info, menu);
        FeatureDataResponse.Data data = AppCacheData.getOurInstance().getSelectedFeatureInfoDetails();
        return data != null && data.isCanEdit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_edit) {
            if (data != null) {
                presenter.fetchBuildAssetData(data.getId(), data.getApp(), data.getLayer());
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void init() {
        data = AppCacheData.getOurInstance().getSelectedFeatureInfoDetails();
        if (data != null && data.getDetailedInfo() != null && data.getDetailedInfo().size() > 0) {
            getViewBinding().elvFeatureDetails.setAdapter(detailInfoAdapter);
            detailInfoAdapter.setMainList(data.getDetailedInfo(), data.getImages());
        }

    }

    @Override
    public void onFetchAssetSuccess(String id, String app, String layer) {
        if (app.equalsIgnoreCase(AppConstants.APP_TYPE_BUILDING_ASSET)) {
            AppCacheData.getOurInstance().setAssetUpdate(true);
            startActivity(new Intent(DetailedInfoActivity.this, BuildingAssetsActivity.class));
        } else if (app.equalsIgnoreCase(AppConstants.APP_TYPE_UTILITY)) {
            AppCacheData.getOurInstance().setAssetUpdate(true);
            Intent intent = new Intent(DetailedInfoActivity.this, UtilityActivity.class);
            intent.putExtra(AppConstants.KEY_LAYER_TYPE, layer);
            startActivity(intent);
        } else if (app.equalsIgnoreCase(AppConstants.APP_TYPE_WATER_BODY)) {
            AppCacheData.getOurInstance().setAssetUpdate(true);
            Intent intent = new Intent(DetailedInfoActivity.this, WaterBodyActivity.class);
            intent.putExtra(AppConstants.KEY_LAYER_TYPE, layer);
            startActivity(intent);
        } else if (app.equalsIgnoreCase(AppConstants.APP_TYPE_OTHER_ASSET)) {
            AppCacheData.getOurInstance().setAssetUpdate(true);
            Intent intent = new Intent(DetailedInfoActivity.this, OtherAssetsActivity.class);
            intent.putExtra(AppConstants.KEY_LAYER_TYPE, layer);
            startActivity(intent);
        }
    }
}
