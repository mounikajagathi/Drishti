package in.ults.ipms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.utils.listeners.RecyclerViewClickListener;

public class CountMainAdapter extends RecyclerView.Adapter<CountMainAdapter.ViewHolder> {

    private ArrayList<DashboardResponse.LayerCategory> localDataSet;
    private Context context;

    public CountMainAdapter(Context context) {
        this.context = context;
    }

    public void setLocalDataSet(ArrayList<DashboardResponse.LayerCategory> localDataSet) {
        this.localDataSet = localDataSet;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView countTitle;
        private final RecyclerView rvCount;

        public ViewHolder(View view) {
            super(view);
            countTitle = view.findViewById(R.id.txtCountTitle);
            rvCount = view.findViewById(R.id.rvCount);
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_count_main, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        DashboardResponse.LayerCategory data = localDataSet.get(position);
        viewHolder.countTitle.setText(data.getLabel());
        viewHolder.rvCount.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        viewHolder.rvCount.setAdapter(new CountSubAdapter(data.getChildren()));
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }

    public ArrayList<DashboardResponse.LayerCategory> getLocalDataSet() {
        return localDataSet==null ? new ArrayList<>() : localDataSet;
    }
}
