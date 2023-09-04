package in.ults.ipms.adapters;

import static android.view.ViewTreeObserver.OnGlobalLayoutListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.listeners.RecyclerViewClickListener;

public class DashboardMenuFeatureAdapter extends RecyclerView.Adapter<DashboardMenuFeatureAdapter.ViewHolder> {

    private ArrayList<DashboardResponse.Menu> localDataSet;
    private static RecyclerViewClickListener clkListener;
    private static int radius = 0;


    public void setLocalDataSet(ArrayList<DashboardResponse.Menu> localDataSet) {
        this.localDataSet = localDataSet;
    }

    public void setClickListener(RecyclerViewClickListener clickListener) {
        clkListener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView featureIcon;
        private final TextView featureTitle;
        private final CardView cdView;

        public ViewHolder(View view) {
            super(view);
            featureIcon = view.findViewById(R.id.dashboardMenuIcon);
            featureTitle = view.findViewById(R.id.dashboardMenuTitle);
            cdView = view.findViewById(R.id.dashboardMenuRootLayout);
            if (radius == 0) {
                cdView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (cdView.getHeight() > 0) {
                            radius = cdView.getHeight() / 2;
                            cdView.setRadius((float) radius);
                        }
                        cdView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            } else {
                cdView.setRadius((float) radius);
            }
            view.setOnClickListener(view1 -> {
                if (clkListener != null) {
                    clkListener.onItemClick(getAbsoluteAdapterPosition(), view1);
                }
            });
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_dashboard_menu_features, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String menu = localDataSet.get(position).getMenuName();
        viewHolder.featureTitle.setText(menu);
        switch (menu) {
            case AppConstants.MENU_TYPE_LAYERS:
                viewHolder.featureIcon.setImageResource(R.drawable.ic_layers);
                break;
            case AppConstants.MENU_TYPE_LEGENDS:
                viewHolder.featureIcon.setImageResource(R.drawable.ic_legends);
                break;
            case AppConstants.MENU_TYPE_OPACITY:
                viewHolder.featureIcon.setImageResource(R.drawable.ic_opacity);
                break;
            case AppConstants.MENU_TYPE_BASEMAP:
                viewHolder.featureIcon.setImageResource(R.drawable.ic_basemap);
                break;
            case AppConstants.MENU_TYPE_SETTINGS:
                viewHolder.featureIcon.setImageResource(R.drawable.ic_settings);
                break;
            case AppConstants.MENU_TYPE_COUNT:
                viewHolder.featureIcon.setImageResource(R.drawable.ic_count);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }

    public ArrayList<DashboardResponse.Menu> getLocalDataSet() {
        return localDataSet == null ? new ArrayList<>() : localDataSet;
    }
}
