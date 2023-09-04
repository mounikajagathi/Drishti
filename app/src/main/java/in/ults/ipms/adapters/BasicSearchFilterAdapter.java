package in.ults.ipms.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.DashboardResponse;

public class BasicSearchFilterAdapter extends RecyclerView.Adapter<BasicSearchFilterAdapter.ViewHolder> {

    private ArrayList<DashboardResponse.SearchAttributes> localDataSet;
    private String selectedFilter;
    private static SearchFilterSelectListener searchFilterListener;


    public void setLocalDataSet(ArrayList<DashboardResponse.SearchAttributes> localDataSet, String selectedFilter) {
        this.localDataSet = localDataSet;
        this.selectedFilter = selectedFilter;
    }

    public void setFilterListener(SearchFilterSelectListener searchFilterSelectListener) {
        searchFilterListener = searchFilterSelectListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final RadioButton filterCheckbox;

        public ViewHolder(View view) {
            super(view);
            filterCheckbox = view.findViewById(R.id.checkboxBasicSearchFilter);
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_basic_search_filter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String filterName = localDataSet.get(position).getDisplayName();
        String filterID = localDataSet.get(position).getColumnName();
        viewHolder.filterCheckbox.setText(filterName);
        viewHolder.filterCheckbox.setTag(filterID);
        viewHolder.filterCheckbox.setClickable(true);
        viewHolder.filterCheckbox.setChecked(selectedFilter != null && selectedFilter.equalsIgnoreCase(filterID));

        viewHolder.filterCheckbox.setOnClickListener(v -> {
            if(searchFilterListener!=null){
                boolean isChecked = viewHolder.filterCheckbox.isChecked();
                if(isChecked){
                    selectedFilter = (String) viewHolder.filterCheckbox.getTag();
                    searchFilterListener.onFilterClick(selectedFilter);
                } else {
                    selectedFilter = null;
                    searchFilterListener.onFilterClick(selectedFilter);
                }
            notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }

    public ArrayList<DashboardResponse.SearchAttributes> getLocalDataSet() {
        return localDataSet==null ? new ArrayList<>() : localDataSet;
    }


    public static interface SearchFilterSelectListener {
        void onFilterClick(String selectedFilter);
    }
}
