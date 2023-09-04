package in.ults.ipms.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.DashboardResponse;

public class MapOverlayAdapter extends RecyclerView.Adapter<MapOverlayAdapter.ViewHolder> {

    private ArrayList<DashboardResponse.LayerCategoryChild> localDataSet;
    private Set<String> selectedMapOverlay;
    private MapOverlayAddRemoveClickListener mapOverlayAddRemoveClickListener;

    public void setLocalDataSet(ArrayList<DashboardResponse.LayerCategoryChild> localDataSet) {
        this.localDataSet = localDataSet;
    }

    public void setMapOverlayAddRemoveClickListener(MapOverlayAddRemoveClickListener mapOverlayAddRemoveClickListener) {
        this.mapOverlayAddRemoveClickListener = mapOverlayAddRemoveClickListener;
    }

    public void setSelectedMapOverlay(Set<String> selectedMapOverlay) {
        this.selectedMapOverlay = selectedMapOverlay;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox multiCheckBox;

        public ViewHolder(View view) {
            super(view);
            multiCheckBox = view.findViewById(R.id.checkboxMapOverlay);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_map_overlay, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final DashboardResponse.LayerCategoryChild layerCategory = localDataSet.get(position);
        final String filterName = layerCategory.getLabel();
        final String KEY_CURRENT_SELECTED_ITEM = filterName + "_" + position;

        viewHolder.multiCheckBox.setText(filterName);
        viewHolder.multiCheckBox.setClickable(true);
        viewHolder.multiCheckBox.setChecked(selectedMapOverlay != null && selectedMapOverlay.contains(KEY_CURRENT_SELECTED_ITEM));

        viewHolder.multiCheckBox.setOnClickListener(v -> {
            boolean isChecked = viewHolder.multiCheckBox.isChecked();
            if (selectedMapOverlay == null) {
                selectedMapOverlay = new HashSet<>();
            }
            if (isChecked) {
                selectedMapOverlay.add(KEY_CURRENT_SELECTED_ITEM);
                if (mapOverlayAddRemoveClickListener != null) {
                    mapOverlayAddRemoveClickListener.onAddSingleLayer(KEY_CURRENT_SELECTED_ITEM, layerCategory);
                }
            } else {
                if (mapOverlayAddRemoveClickListener != null) {
                    mapOverlayAddRemoveClickListener.onRemoveSingleLayer(KEY_CURRENT_SELECTED_ITEM, layerCategory);
                }
            }
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }

    public ArrayList<DashboardResponse.LayerCategoryChild> getLocalDataSet() {
        return localDataSet == null ? new ArrayList<>() : localDataSet;
    }


    public interface MapOverlayAddRemoveClickListener {


        void onAddSingleLayer(String keySelected, DashboardResponse.LayerCategoryChild selectedLayer);

        void onRemoveSingleLayer(String keySelected, DashboardResponse.LayerCategoryChild selectedLayer);
    }
}
