package in.ults.ipms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.SearchDetailsResponse;

public class SearchDetailsAdapter extends RecyclerView.Adapter<SearchDetailsAdapter.ViewHolder> {

    private ArrayList<SearchDetailsResponse.Features> localDataSet;
    private SearchResultClickListener searchResultClickListener;
    private String featureType = "";
    private Context context;

    public SearchDetailsAdapter(Context context) {
        this.context = context;
    }

    public void setLocalDataSet(ArrayList<SearchDetailsResponse.Features> localDataSet, String featureType) {
        this.localDataSet = localDataSet;
        this.featureType = featureType;
    }


    public void setSearchResultClickListener(SearchResultClickListener searchResultClickListener) {
        this.searchResultClickListener = searchResultClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView searchItem;

        public ViewHolder(View view) {
            super(view);
            searchItem = view.findViewById(R.id.txtSearchItem);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_search_details, viewGroup, false);
        return new ViewHolder(view);
    }

    public  void clearData(){
        if(localDataSet!=null){
            localDataSet.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        SearchDetailsResponse.Features searchData = localDataSet.get(position);
        viewHolder.searchItem.setText(String.format("%s : %s", context.getString(R.string.search_property_id), searchData.getProperty().getNewPropertyId()));
        viewHolder.searchItem.setOnClickListener(view1 -> {
            if(searchResultClickListener!=null){
                searchResultClickListener.onResultDetailsClick(featureType +"."+localDataSet.get(position).getProperty().getPk());
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }

    public ArrayList<SearchDetailsResponse.Features> getLocalDataSet() {
        return localDataSet == null ? new ArrayList<>() : localDataSet;
    }


    public interface SearchResultClickListener {
        void onResultDetailsClick(String resultId);
    }
}
