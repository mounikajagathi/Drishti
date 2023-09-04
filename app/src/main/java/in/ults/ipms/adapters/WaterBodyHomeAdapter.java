package in.ults.ipms.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.ults.ipms.R;
import in.ults.ipms.utils.listeners.RecyclerViewClickListener;

public class WaterBodyHomeAdapter extends RecyclerView.Adapter<WaterBodyHomeAdapter.ViewHolder> {

    private String[] localDataSet;
    private static RecyclerViewClickListener clkListener;


    public void setLocalDataSet(String[] localDataSet) {
        this.localDataSet = localDataSet;
        notifyDataSetChanged();
    }

    public void setClickListener(RecyclerViewClickListener clickListener) {
        clkListener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView featureTitle;

        public ViewHolder(View view) {
            super(view);
            featureTitle = view.findViewById(R.id.buildingAssetsTitle);
            featureTitle.setOnClickListener(view1 -> {
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
                .inflate(R.layout.item_building_asset_home, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        String menu = localDataSet[position];
        viewHolder.featureTitle.setText(menu);
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.length;
    }

    public String[] getLocalDataSet() {
        return localDataSet == null ? new String[0] : localDataSet;
    }
}
