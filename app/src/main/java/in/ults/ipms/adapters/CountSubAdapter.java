package in.ults.ipms.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.utils.listeners.RecyclerViewClickListener;

public class CountSubAdapter extends RecyclerView.Adapter<CountSubAdapter.ViewHolder> {

    private ArrayList<DashboardResponse.LayerCategoryChild> localDataSet;

    public CountSubAdapter(ArrayList<DashboardResponse.LayerCategoryChild> localDataSet) {
        this.localDataSet = localDataSet;
    }

    public void setLocalDataSet(ArrayList<DashboardResponse.LayerCategoryChild> localDataSet) {
        this.localDataSet = localDataSet;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView countSubTitle;

        public ViewHolder(View view) {
            super(view);
            countSubTitle = view.findViewById(R.id.txtCountSubTitle);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_count_sub, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        DashboardResponse.LayerCategoryChild data = localDataSet.get(position);
        viewHolder.countSubTitle.setText(String.format("%s - %s", data.getLabel(), data.getCount()));
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }

    public ArrayList<DashboardResponse.LayerCategoryChild> getLocalDataSet() {
        return localDataSet==null ? new ArrayList<>() : localDataSet;
    }
}
