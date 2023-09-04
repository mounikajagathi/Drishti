package in.ults.ipms.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ults.ipms.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private ArrayList<String> localDataSet;
    private SearchResultClickListener searchResultClickListener;


    public void setLocalDataSet(ArrayList<String> localDataSet) {
        this.localDataSet = localDataSet;
    }

    public  void clearData(){
        if(localDataSet!=null){
            localDataSet.clear();
            notifyDataSetChanged();
        }
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
                .inflate(R.layout.item_search, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final String searchData = localDataSet.get(position);
        viewHolder.searchItem.setText(searchData);
        viewHolder.searchItem.setOnClickListener(view1 -> {
            if (searchResultClickListener != null) {
                searchResultClickListener.onResultClick(searchData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }

    public ArrayList<String> getLocalDataSet() {
        return localDataSet == null ? new ArrayList<>() : localDataSet;
    }


    public interface SearchResultClickListener {
        void onResultClick(String value);
    }
}
