package in.ults.ipms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.base.BaseSpinnerData;
import in.ults.ipms.views.IPMSAutoComplete;

public class CommonAutoCompleteAdapter<T extends BaseSpinnerData> extends ArrayAdapter<T> {
    Context context;
    private final List<T> actualList;
    HashMap<String, String> idList;
    private final List<T> filteredList;
    final IPMSAutoComplete spinner;
    private CommonSpinnerClickListener commonSpinnerClickListener;

    public CommonAutoCompleteAdapter(@NonNull Context context, int resource, List<T> actualList, IPMSAutoComplete spinner) {
        super(context, resource, actualList);
        this.context = context;
        this.actualList = actualList;
        this.spinner = spinner;
        this.filteredList = new ArrayList<>();
        idList = new HashMap<>();
    }

    public void addItems(List<T> repoList) {
        if (repoList != null) {
            for (T item : repoList) {
                if (item != null && item.getSpinnerId() != null && item.getSpinnerTitle() != null) {
                    idList.put(item.getSpinnerId(), item.getSpinnerTitle());
                }
            }
        }
        actualList.addAll(repoList);
    }

    public void clearItems() {
        filteredList.clear();
    }

    public void setCommonSpinnerClickListener(CommonSpinnerClickListener commonSpinnerClickListener) {
        this.commonSpinnerClickListener = commonSpinnerClickListener;
    }

    public CommonSpinnerClickListener getCommonSpinnerClickListener() {
        return commonSpinnerClickListener;
    }

    public void setContent(String id) {
        String title = idList.get(id);
        if (spinner != null && id != null && title != null) {
            spinner.setText(title);
            spinner.setTag(id);
        }
    }

    @Nullable
    @Override
    public T getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public int getCount() {
        return filteredList != null ? filteredList.size() : 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_spinner, parent, false);
        }
        BaseSpinnerData commonItem = filteredList.get(position);
        if (commonItem != null) {
            TextView lblName = view.findViewById(R.id.txtSpinner);
            if (lblName != null)
                lblName.setText(commonItem.getSpinnerTitle());
        }
        return view;
    }


    interface CommonSpinnerClickListener {
        void onClick(String id);
    }

    public static <T extends BaseSpinnerData> CommonAutoCompleteAdapter<T> setAdapter(Context content, IPMSAutoComplete spinner, List<T> data) {
        CommonAutoCompleteAdapter<T> adapter = new CommonAutoCompleteAdapter<>(content, 0, new ArrayList<>(), spinner);
        if (data != null) {
            adapter.addItems(data);
            spinner.setAdapter(adapter);
            spinner.setOnItemClickListener((adapterView, view, i, l) -> {
                String clickID = Objects.requireNonNull(adapter.getItem(i)).getSpinnerId();
                spinner.setTag(clickID);
                if (adapter.getCommonSpinnerClickListener() != null) {
                    adapter.getCommonSpinnerClickListener().onClick(clickID);
                }
            });
        }
        return adapter;

    }


    @Override
    public Filter getFilter() {
        return new TitleFilter(this, actualList);
    }

    private class TitleFilter extends Filter {

        CommonAutoCompleteAdapter ownerListAdapter;
        List<T> originalList;
        List<T> filteredList;

        public TitleFilter(CommonAutoCompleteAdapter ownerListAdapter, List<T> originalList) {
            super();
            this.ownerListAdapter = ownerListAdapter;
            this.originalList = originalList;
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(originalList);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final T item : originalList) {
                    if (item.getSpinnerTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                ownerListAdapter.filteredList.clear();
                ownerListAdapter.filteredList.addAll((List) results.values);
                ownerListAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((BaseSpinnerData) resultValue).getSpinnerTitle();
        }
    }
}

