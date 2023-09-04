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

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.base.BaseSpinnerData;
import in.ults.ipms.views.IPMSSpinner;

public class CommonSpinnerAdapter<T extends BaseSpinnerData> extends ArrayAdapter<T> {
    Context context;
    private final List<T> list;
    HashMap<String, String> idList;
    final IPMSSpinner spinner;
    private CommonSpinnerClickListener commonSpinnerClickListener;

    public CommonSpinnerAdapter(@NonNull Context context, int resource, List<T> list, IPMSSpinner spinner) {
        super(context, resource, list);
        this.context = context;
        this.list = list;
        this.spinner = spinner;
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
        list.addAll(repoList);
    }

    public void clearItems() {
        list.clear();
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


    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_spinner, parent, false);
        }
        BaseSpinnerData commonItem = list.get(position);
        if (commonItem != null) {
            TextView lblName = view.findViewById(R.id.txtSpinner);
            if (lblName != null)
                lblName.setText(commonItem.getSpinnerTitle());
        }
        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((BaseSpinnerData) resultValue).getSpinnerTitle();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                return new FilterResults();
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            }
        };
    }

    interface CommonSpinnerClickListener{
        void onClick(String id);
    }

    public static <T extends BaseSpinnerData> CommonSpinnerAdapter<T> setAdapter(Context content, IPMSSpinner spinner, List<T> data) {
        CommonSpinnerAdapter<T> adapter = new CommonSpinnerAdapter<>(content, 0, new ArrayList<>(), spinner);
        if (data != null) {
            adapter.addItems(data);
            spinner.setAdapter(adapter);
            spinner.setOnItemClickListener((adapterView, view, i, l) -> {
                String clickID = data.get(i).getSpinnerId();
                spinner.setTag(clickID);
                if(adapter.getCommonSpinnerClickListener()!=null){
                    adapter.getCommonSpinnerClickListener().onClick(clickID);
                }
            });
        }
        return adapter;

    }
}

