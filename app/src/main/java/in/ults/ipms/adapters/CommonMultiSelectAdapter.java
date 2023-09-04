package in.ults.ipms.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.base.BaseSpinnerData;

public class CommonMultiSelectAdapter<T extends BaseSpinnerData> extends RecyclerView.Adapter<CommonMultiSelectAdapter.ViewHolder> {

    private ArrayList<T> localDataSet;
    private ArrayList<String> selectedId;


    public void setLocalDataSet(ArrayList<T> localDataSet) {
        this.localDataSet = localDataSet;
    }

    public ArrayList<String> getSelectedId() {
        if(selectedId==null){
            selectedId = new ArrayList<>();
        }
        return selectedId;
    }

    public void setSelectedId(ArrayList<String> selectedId) {
        this.selectedId = selectedId;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox multiCheckBox;

        public ViewHolder(View view) {
            super(view);
            multiCheckBox = view.findViewById(R.id.checkboxMultiSelect);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_common_multiselect, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String filterName = localDataSet.get(position).getSpinnerTitle();
        String filterID = localDataSet.get(position).getSpinnerId();
        viewHolder.multiCheckBox.setText(filterName);
        viewHolder.multiCheckBox.setTag(filterID);
        viewHolder.multiCheckBox.setClickable(true);
        viewHolder.multiCheckBox.setChecked(selectedId != null && selectedId.contains(filterID));

        viewHolder.multiCheckBox.setOnClickListener(v -> {
            boolean isChecked = viewHolder.multiCheckBox.isChecked();
            if (selectedId == null) {
                selectedId = new ArrayList<>();
            }
            if (isChecked) {
                selectedId.add((String) viewHolder.multiCheckBox.getTag());
            } else {
                selectedId.remove((String) viewHolder.multiCheckBox.getTag());
            }
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }

    public ArrayList<T> getLocalDataSet() {
        return localDataSet == null ? new ArrayList<>() : localDataSet;
    }
}
