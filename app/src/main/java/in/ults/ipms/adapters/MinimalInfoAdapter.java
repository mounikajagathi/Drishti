package in.ults.ipms.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.FeatureDataResponse;

public class MinimalInfoAdapter extends RecyclerView.Adapter<MinimalInfoAdapter.ViewHolder> {

    private ArrayList<FeatureDataResponse.MinimalInfo> localDataSet;

    public void setLocalDataSet(ArrayList<FeatureDataResponse.MinimalInfo> localDataSet) {
        this.localDataSet = localDataSet;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ConstraintLayout infoItemLayout;
        private final TextView infoTitle;
        private final TextView infoValue;

        public ViewHolder(View view) {
            super(view);
            infoItemLayout = view.findViewById(R.id.layoutItemMinimalInfo);
            infoTitle = view.findViewById(R.id.txtMinimalInfoTitle);
            infoValue = view.findViewById(R.id.txtMinimalInfoValue);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_minimal_info, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String filterName = localDataSet.get(position).getInfoTitle();
        String filterID = localDataSet.get(position).getInfoValue();
        viewHolder.infoTitle.setText(filterName);
        viewHolder.infoValue.setText(filterID);
        if(position % 2 == 0){
            viewHolder.infoItemLayout.setBackgroundColor(Color.parseColor("#F9F9F9"));
        }else{
            viewHolder.infoItemLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }

    public ArrayList<FeatureDataResponse.MinimalInfo> getLocalDataSet() {
        return localDataSet==null ? new ArrayList<>() : localDataSet;
    }
}
