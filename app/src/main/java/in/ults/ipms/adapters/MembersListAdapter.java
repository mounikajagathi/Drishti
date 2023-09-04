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
import in.ults.ipms.utils.listeners.RecyclerViewClickListener;

public class MembersListAdapter extends RecyclerView.Adapter<MembersListAdapter.ViewHolder> {

    private ArrayList<BuildingAssets.MemberDetails> localDataSet;
    private static RecyclerViewClickListener clkListener;
    private static RecyclerViewClickListener deleteListener;


    public void setLocalDataSet(ArrayList<BuildingAssets.MemberDetails> localDataSet) {
        this.localDataSet = localDataSet;
        notifyDataSetChanged();
    }


    public void setDeleteListener(RecyclerViewClickListener delListener) {
        deleteListener = delListener;
    }

    public void setClickListener(RecyclerViewClickListener clickListener) {
        clkListener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView featureTitle;

        public ViewHolder(View view) {
            super(view);
            featureTitle = view.findViewById(R.id.buildingAssetsTitle);
            view.setOnClickListener(view1 -> {
                if(clkListener!=null){
                    clkListener.onItemClick(getAbsoluteAdapterPosition(), view1);
                }
            });
            view.findViewById(R.id.removeItem).setOnClickListener(view12 -> {
                if(deleteListener!=null){
                    deleteListener.onItemClick(getAbsoluteAdapterPosition(), view12);
                }
            });
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_member_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        BuildingAssets.MemberDetails data = localDataSet.get(position);
        viewHolder.featureTitle.setText(String.format("%s %s", data.getFirstname(), data.getLastname()!=null ? data.getLastname() : ""));
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }

    public ArrayList<BuildingAssets.MemberDetails> getLocalDataSet() {
        return localDataSet==null ? new ArrayList<>() : localDataSet;
    }
}
