package in.ults.ipms.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import in.ults.ipms.R;
import in.ults.ipms.singletons.AppCacheData;
import in.ults.ipms.ui.viewer.imageview.ImageViewerActivity;
import in.ults.ipms.utils.AppConstants;


public class FeatureInfoImageAdapter extends PagerAdapter {


    private final ArrayList<String> images;
    private final LayoutInflater inflater;
    private final Context context;


    public FeatureInfoImageAdapter(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images != null ? images.size() : 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.item_feature_info_image, view, false);
        assert imageLayout != null;
        final ImageView imageView = imageLayout.findViewById(R.id.featureDetailImage);
        Glide.with(context)
                .load(AppCacheData.getOurInstance().getImageBaseURL() +""+images.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .into(imageView);
        view.addView(imageLayout, 0);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImageViewerActivity.class);
                intent.putExtra(AppConstants.FULLSCREEN_IMAGE,AppCacheData.getOurInstance().getImageBaseURL() +""+images.get(position));
                context.startActivity(intent);
            }
        });
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, @NonNull Object object) {
        return view.equals(object);
    }

}
