package in.ults.ipms.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.FeatureDataResponse;
import in.ults.ipms.utils.AppConstants;
import in.ults.ipms.utils.common.CommonUtils;

public class DetailInfoAdapter extends BaseExpandableListAdapter {

    private final Activity context;
    private List<String> expandableListParent;
    private ArrayList<String> imageList;
    private HashMap<String, ArrayList<FeatureDataResponse.DetailedInfoData>> expandableListChild;

    public DetailInfoAdapter(Activity context) {
        this.context = context;
    }

    public void setMainList(ArrayList<FeatureDataResponse.DetailedInfo> mainList, ArrayList<String> images) {
        expandableListChild = new HashMap<>();
        this.imageList = images;
        Gson gson = new Gson();
        for (FeatureDataResponse.DetailedInfo info : mainList) {
            if (info.getDetailedInfoData() != null && info.getDetailedInfoData().size() > 0) {
                if (info.getDetailedInfoData().get(0) instanceof ArrayList) {
                    ArrayList<ArrayList<FeatureDataResponse.DetailedInfoData>> data = gson.fromJson(gson.toJson(info.getDetailedInfoData()),
                            new TypeToken<ArrayList<ArrayList<FeatureDataResponse.DetailedInfoData>>>() {
                            }.getType());
                    ArrayList<FeatureDataResponse.DetailedInfoData> tempData = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        ArrayList<FeatureDataResponse.DetailedInfoData> innerData = data.get(i);
                        if (data.size() > 1) {
                            innerData.add(0, new FeatureDataResponse.DetailedInfoData(AppConstants.INFO_TYPE_HEADING, info.getHeading() + " " + (i + 1)));
                        }
                        tempData.addAll(innerData);
                    }
                    expandableListChild.put(info.getHeading(), tempData);
                } else {
                    ArrayList<FeatureDataResponse.DetailedInfoData> data = gson.fromJson(gson.toJson(info.getDetailedInfoData()),
                            new TypeToken<ArrayList<FeatureDataResponse.DetailedInfoData>>() {
                            }.getType());
                    expandableListChild.put(info.getHeading(), data);
                }
            }
        }

        expandableListParent = new ArrayList<>(expandableListChild.keySet());
        if (imageList != null && imageList.size() > 0) {
            expandableListParent.add(0, AppConstants.INFO_TYPE_IMAGES);
        }
        notifyDataSetChanged();
    }


    @Override
    public FeatureDataResponse.DetailedInfoData getChild(int listPosition, int expandedListPosition) {
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
        final FeatureDataResponse.DetailedInfoData singleData = getChild(listPosition, expandedListPosition);
        final String titleText = singleData.getInfoTitle();
        final Object valueText = singleData.getInfoValue();
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_detailed_info_child, null);
        }
        TextView heading = convertView.findViewById(R.id.txtDetailedInfoHeading);
        TextView title = convertView.findViewById(R.id.txtDetailedInfoTitle);
        TextView value = convertView.findViewById(R.id.txtDetailedInfoValue);
        ConstraintLayout containerView = convertView.findViewById(R.id.layoutItemDetailedInfo);
        if (titleText.equals(AppConstants.INFO_TYPE_HEADING)) {
            if (valueText instanceof String) {
                heading.setText((String) valueText);
            }
            heading.setVisibility(View.VISIBLE);
        } else {
            heading.setVisibility(View.GONE);
            if (expandedListPosition % 2 == 0) {
                containerView.setBackgroundColor(Color.parseColor("#F9F9F9"));
            } else {
                containerView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            title.setText(titleText);

            if (valueText instanceof String) {
                value.setText((String) valueText);
            } else if (valueText instanceof ArrayList) {
                if (((ArrayList<?>) valueText).size() > 0) {
                    StringBuilder tempValue = new StringBuilder();
                    for (int i = 0; i < ((ArrayList<?>) valueText).size(); i++) {
                        if (((ArrayList<?>) valueText).get(i) instanceof String) {
                            if (i == 0) {
                                tempValue = new StringBuilder((String) ((ArrayList<?>) valueText).get(i));
                            } else {
                                tempValue.append("\n").append((String) ((ArrayList<?>) valueText).get(i));
                            }
                        }
                    }
                    value.setText(tempValue);
                } else {
                    value.setText("-");
                }

            } else {
                value.setText("-");
            }
        }
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
        String listHeading = getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_detailed_info_parent, null);
            RelativeLayout imageContainer = convertView.findViewById(R.id.containerVPFeatureImages);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imageContainer.getLayoutParams();
            params.height = (int) (CommonUtils.getScreenHeight(context) / 3);
            imageContainer.setLayoutParams(params);
        }
        RelativeLayout imageContainer = convertView.findViewById(R.id.containerVPFeatureImages);
        TextView heading = convertView.findViewById(R.id.txtDetailedInfoHeading);

        if (listHeading.equals(AppConstants.INFO_TYPE_IMAGES)) {
            imageContainer.setVisibility(View.VISIBLE);
            heading.setVisibility(View.GONE);
            ViewPager vpFeatureImage = convertView.findViewById(R.id.vpFeatureImage);
            TabLayout tlFeatureImage = convertView.findViewById(R.id.tlFeatureImage);
            PagerAdapter adapter = new FeatureInfoImageAdapter(context, imageList);
            vpFeatureImage.setAdapter(adapter);
            tlFeatureImage.setupWithViewPager(vpFeatureImage, true);
        } else {
            imageContainer.setVisibility(View.GONE);
            heading.setVisibility(View.VISIBLE);
            heading.setText(listHeading);
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


}