package in.ults.ipms.ui.viewer.imageview;

import static in.ults.ipms.utils.AppConstants.FULLSCREEN_IMAGE;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import javax.inject.Inject;

import in.ults.ipms.databinding.ActivityImageViewerBinding;
import in.ults.ipms.ui.base.BaseActivity;


public class ImageViewerActivity extends BaseActivity<ActivityImageViewerBinding> implements IImageViewerView {
    public static final String TAG = ImageViewerActivity.class.getSimpleName();


    @Inject
    IImageViewerPresenter<IImageViewerView, IImageViewerInteractor> presenter;

    @Override
    protected boolean setFullScreenActivity() {
        return true;
    }

    @Override
    protected boolean hideNavigationIcons() {
        return true;
    }

    @Override
    protected ActivityImageViewerBinding initViewBinding() {
        return ActivityImageViewerBinding.inflate(getLayoutInflater());
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
    protected void init() {
        getViewBinding().btnBack.setOnClickListener(view -> onBackPressed());
        if (getIntent().getExtras() != null) {
            String imageUrl = getIntent().getStringExtra(FULLSCREEN_IMAGE);
            Glide.with(this)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(false)
                    .into(getViewBinding().fullScreenImage);
        }
    }
}
