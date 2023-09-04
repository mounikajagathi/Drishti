package in.ults.ipms.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.BuildingAssetSpinnerResponse;
import in.ults.ipms.data.network.model.response.BuildingAssets;

public class FloorTypeAdapter extends RecyclerView.Adapter<FloorTypeAdapter.ViewHolder> {

    private ArrayList<BuildingAssetSpinnerResponse.FloorTypes> localDataSet;
    private ArrayList<BuildingAssets.FloorType> selectedId;
    private ArrayList<String> checkedID;
    private RemoveListener removeListener;


    public FloorTypeAdapter() {
        checkedID = new ArrayList<>();
    }

    public void setLocalDataSet(ArrayList<BuildingAssetSpinnerResponse.FloorTypes> localDataSet) {
        this.localDataSet = localDataSet;
    }

    public ArrayList<BuildingAssets.FloorType> getSelectedId() {
        if (selectedId == null) {
            selectedId = new ArrayList<>();
        }
        return selectedId;
    }

    public void setRemoveListener(RemoveListener removeListener) {
        this.removeListener = removeListener;
    }

    public void setSelectedId(ArrayList<BuildingAssets.FloorType> selectedId) {
        this.selectedId = selectedId;
        if (checkedID == null) {
            checkedID = new ArrayList<>();
        }

        if (selectedId != null) {
            for (BuildingAssets.FloorType floorType : selectedId) {
                checkedID.add(String.valueOf(floorType.getFloorType()));
            }
        }
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
        viewHolder.multiCheckBox.setChecked(checkedID != null && checkedID.contains(filterID));

        viewHolder.multiCheckBox.setOnClickListener(v -> {
            boolean isChecked = viewHolder.multiCheckBox.isChecked();
            if (selectedId == null) {
                selectedId = new ArrayList<>();
            }
            if (isChecked) {
                BuildingAssets.FloorType floorType = new BuildingAssets.FloorType();
                String floorTypeID = (String) viewHolder.multiCheckBox.getTag();
                floorType.setFloorType(Integer.parseInt(floorTypeID));
                checkedID.add(floorTypeID);
                selectedId.add(floorType);
                notifyDataSetChanged();
            } else {
                int pos = hasFloorType(Integer.parseInt((String) viewHolder.multiCheckBox.getTag()));
                if (pos != -1) {
                    long pk = selectedId.get(pos).getPk();
                    if (pk == -1) {
                        removeListData(pos);
                    } else {
                        if (removeListener != null) {
                            removeListener.onRemove(pos, pk);
                        }
                    }
                }
            }
        });
    }

    public void removeListData(int position) {
        if (selectedId != null && selectedId.size() > position) {
            int floorType = selectedId.get(position).getFloorType();
            checkedID.remove(String.valueOf(floorType));
            selectedId.remove(position);
            notifyDataSetChanged();
        }
    }

    private int hasFloorType(int floorType) {
        int position = -1;
        if (selectedId != null) {
            for (int i = 0; i < selectedId.size(); i++) {
                if (floorType == selectedId.get(i).getFloorType()) {
                    position = i;
                }
            }
        }

        return position;
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }

    public ArrayList<BuildingAssetSpinnerResponse.FloorTypes> getLocalDataSet() {
        return localDataSet == null ? new ArrayList<>() : localDataSet;
    }

    public interface RemoveListener {
        void onRemove(int position, long pk);
    }
}
