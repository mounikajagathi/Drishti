package in.ults.ipms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.DashboardResponse;
import in.ults.ipms.singletons.AppCacheData;

public class DashboardLayersExpandableListAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private List<String> expandableListParent;
    private HashMap<String, ArrayList<DashboardResponse.LayerCategoryChild>> expandableListChild;
    private LayerAddRemoveClickListener layerAddRemoveClickListener;
    private ExpandableListView expandableListView;

    public DashboardLayersExpandableListAdapter(Context context) {
        this.context = context;
    }

    public void setExpandableListView(ExpandableListView expandableListView) {
        this.expandableListView = expandableListView;
    }

    public void setMainList(ArrayList<DashboardResponse.LayerCategory> mainList) {
        expandableListChild = new HashMap<>();
        for (DashboardResponse.LayerCategory layers : mainList) {
            if (layers != null && layers.getChildren() != null && layers.getChildren().size() > 0) {
                expandableListChild.put(layers.getLabel(), layers.getChildren());
            }
        }
        expandableListParent = new ArrayList<>(expandableListChild.keySet());
        notifyDataSetChanged();
    }


    public void setLayerAddRemoveClickListener(LayerAddRemoveClickListener layerAddRemoveClickListener) {
        this.layerAddRemoveClickListener = layerAddRemoveClickListener;
    }

    @Override
    public DashboardResponse.LayerCategoryChild getChild(int listPosition, int expandedListPosition) {
        return Objects.requireNonNull(this.expandableListChild.get(this.expandableListParent.get(listPosition)))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(final int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final DashboardResponse.LayerCategoryChild singleLayerData = getChild(listPosition, expandedListPosition);
        final String expandedListText = singleLayerData.getLabel();
        final String KEY_SELECTED_ITEM = expandedListText + "_" + expandedListPosition;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_dashboard_layers_child, null);
        }
        CheckBox checkboxChild = convertView
                .findViewById(R.id.checkboxLayerChild);
        checkboxChild.setText(expandedListText);
        checkboxChild.setChecked(AppCacheData.getOurInstance().getSelectedDashboardLayerChild().contains(KEY_SELECTED_ITEM));
        checkboxChild.setClickable(true);
        checkboxChild.setOnClickListener(view -> {
            String parentTitle = getGroup(listPosition);
            ArrayList<DashboardResponse.LayerCategoryChild> child = expandableListChild.get(parentTitle);

            if (checkboxChild.isChecked()) {
                AppCacheData.getOurInstance().getSelectedDashboardLayerChild().add(KEY_SELECTED_ITEM);
                if (layerAddRemoveClickListener != null) {
                    layerAddRemoveClickListener.onAddSingleLayer(KEY_SELECTED_ITEM, parentTitle, singleLayerData);
                }
            } else {
                AppCacheData.getOurInstance().getSelectedDashboardLayerChild().remove(KEY_SELECTED_ITEM);
                if (layerAddRemoveClickListener != null) {
                    layerAddRemoveClickListener.onRemoveSingleLayer(parentTitle, singleLayerData);
                }
            }
            notifyDataSetChanged();

        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return (this.expandableListChild != null && this.expandableListParent != null) ? Objects.requireNonNull(this.expandableListChild.get(this.expandableListParent.get(listPosition))).size() : 0;
    }

    @Override
    public String getGroup(int listPosition) {
        return this.expandableListParent.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListParent != null ? this.expandableListParent.size() : 0;
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = getGroup(listPosition);
        final String KEY_SELECTED_ITEM = listTitle + "_" + listPosition;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_dashboard_layers_parent, null);
        }

        ArrayList<DashboardResponse.LayerCategoryChild> child = expandableListChild.get(listTitle);
        if (child != null && child.size() > 0) {
            int numberOfItemsFound = 0;
            for (int i = 0; i < child.size(); i++) {
                String KEY_CURRENT_SELECTED_ITEM = child.get(i).getLabel() + "_" + i;
                if (AppCacheData.getOurInstance().getSelectedDashboardLayerChild().contains(KEY_CURRENT_SELECTED_ITEM)) {
                    numberOfItemsFound = numberOfItemsFound + 1;
                }
            }
            String KEY_CURRENT_SELECTED_ITEM = listTitle + "_" + listPosition;
            if (numberOfItemsFound == 0) {
                AppCacheData.getOurInstance().getSelectedDashboardLayerParent().remove(KEY_CURRENT_SELECTED_ITEM);
            } else {
                AppCacheData.getOurInstance().getSelectedDashboardLayerParent().add(KEY_CURRENT_SELECTED_ITEM);
            }
        }

        TextView txtParent = convertView
                .findViewById(R.id.txtLayerParent);
        txtParent.setText(listTitle);


        ImageView clearLayers = convertView
                .findViewById(R.id.layerClearAll);
        clearLayers.setEnabled(AppCacheData.getOurInstance().getSelectedDashboardLayerParent().contains(KEY_SELECTED_ITEM));
        clearLayers.setOnClickListener(view -> {
            AppCacheData.getOurInstance().getSelectedDashboardLayerParent().remove(KEY_SELECTED_ITEM);
            if (layerAddRemoveClickListener != null) {
                layerAddRemoveClickListener.onRemoveAllLayers(listTitle, child);
            }
            if (child != null) {
                for (int i = 0; i < child.size(); i++) {
                    String KEY_CURRENT_SELECTED_ITEM = child.get(i).getLabel() + "_" + i;
                    AppCacheData.getOurInstance().getSelectedDashboardLayerChild().remove(KEY_CURRENT_SELECTED_ITEM);
                }
                notifyDataSetChanged();
            }
        });

        ImageView expandCollapseIndicator = convertView
                .findViewById(R.id.layerListExpandCollapseIndicator);
        if (getChildrenCount(listPosition) > 0) {
            expandCollapseIndicator.setVisibility(View.VISIBLE);
            if (isExpanded) {
                expandCollapseIndicator.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_layers_expand_minus));
            } else {
                expandCollapseIndicator.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_layers_expand_plus));
            }
            expandCollapseIndicator.setOnClickListener(view -> {
                if (expandableListView != null) {
                    if (isExpanded) {
                        expandableListView.collapseGroup(listPosition);
                    } else {
                        expandableListView.expandGroup(listPosition);
                    }
                }
            });
        } else {
            expandCollapseIndicator.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }


    public interface LayerAddRemoveClickListener {

        void onRemoveAllLayers(String layerCategory, ArrayList<DashboardResponse.LayerCategoryChild> allLayers);

        void onAddSingleLayer(String keySelected, String layerCategory, DashboardResponse.LayerCategoryChild selectedLayer);

        void onRemoveSingleLayer(String layerCategory, DashboardResponse.LayerCategoryChild selectedLayer);
    }

}