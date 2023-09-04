package in.ults.ipms.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import in.ults.ipms.R;
import in.ults.ipms.data.network.model.response.BuildingAssetSpinnerResponse;
import in.ults.ipms.data.network.model.response.BuildingAssets;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.views.IPMSSpinner;

public class FloorAreaAdapter extends RecyclerView.Adapter<FloorAreaAdapter.ViewHolder> {

    private ArrayList<BuildingAssets.FloorArea> localDataSet;
    private final Set<String> selectedId;
    private final BaseActivity context;
    private RemoveListener removeListener;
    private ArrayList<BuildingAssetSpinnerResponse.FloorNumbers> floorCategoryList;
    private boolean validateData = false;

    public void setFloorCategoryList(ArrayList<BuildingAssetSpinnerResponse.FloorNumbers> floorCategoryList) {
        this.floorCategoryList = floorCategoryList;
        notifyDataSetChanged();
    }

    public boolean validateData() {
        boolean isValidation = true;
        if (localDataSet != null) {
            for (BuildingAssets.FloorArea item : localDataSet) {
                if (item.getFloorCategory() == -999) {
                    isValidation = false;
                }
                if (!(item.getFloorArea() != null && item.getFloorArea().length() > 0)) {
                    isValidation = false;
                }
            }
            validateData = !isValidation;
            notifyDataSetChanged();
            return isValidation;
        } else {
            return false;
        }
    }

    public void setRemoveListener(RemoveListener removeListener) {
        this.removeListener = removeListener;
    }

    public FloorAreaAdapter(BaseActivity context) {
        this.context = context;
        localDataSet = new ArrayList<>();
        selectedId = new HashSet<>();
        localDataSet.add(new BuildingAssets.FloorArea("", -999));
    }

    public void setLocalDataSet(ArrayList<BuildingAssets.FloorArea> localDataSet) {
        if (localDataSet == null || localDataSet.size() == 0) {
            localDataSet = new ArrayList<>();
            localDataSet.add(new BuildingAssets.FloorArea("", -999));
        }
        this.localDataSet = localDataSet;
            for(BuildingAssets.FloorArea item : localDataSet){
                    selectedId.add(String.valueOf(item.getFloorCategory()));
            }
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView addItem;
        private final ImageView removeItem;
        private final TextInputLayout layoutFloorCategory;
        private final IPMSSpinner spinnerFloorCategory;
        private final TextInputLayout layoutFloorArea;
        CommonSpinnerAdapter<BuildingAssetSpinnerResponse.FloorNumbers> floorCategoryAdapter;

        public ViewHolder(View view) {
            super(view);
            addItem = view.findViewById(R.id.addItem);
            removeItem = view.findViewById(R.id.removeItem);
            spinnerFloorCategory = view.findViewById(R.id.srFloorCategory);
            layoutFloorCategory = view.findViewById(R.id.layoutFloorCategory);
            layoutFloorArea = view.findViewById(R.id.layoutFloorArea);
            floorCategoryAdapter = CommonSpinnerAdapter.setAdapter(context, spinnerFloorCategory, floorCategoryList);
            spinnerFloorCategory.setAdapter(floorCategoryAdapter);

            addItem.setOnClickListener(v -> {
                localDataSet.add(new BuildingAssets.FloorArea("", -999));
                notifyDataSetChanged();
            });

            removeItem.setOnClickListener(v -> {
                if (localDataSet.size() > getAbsoluteAdapterPosition() &&
                        localDataSet.get(getAbsoluteAdapterPosition()).getPk()!=-1) {
                    if (removeListener != null) {
                        removeListener.onRemove(getAbsoluteAdapterPosition(), localDataSet.get(getAbsoluteAdapterPosition()).getPk());
                    }
                }else {
                    removeListData(getAbsoluteAdapterPosition());
                }
            });

            Objects.requireNonNull(layoutFloorArea.getEditText()).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (localDataSet.size() > getAbsoluteAdapterPosition()) {
                        localDataSet.get(getAbsoluteAdapterPosition()).setFloorArea(Objects.requireNonNull(layoutFloorArea.getEditText()).getText().toString());
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


        }
    }

    public void removeListData(int position){
        if (localDataSet.size() > position) {
            String categoryID = String.valueOf(localDataSet.get(position).getFloorCategory());
            if (selectedId != null) {
                selectedId.remove(categoryID);
                localDataSet.remove(position);
                notifyDataSetChanged();


            }
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_floor_area, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        BuildingAssets.FloorArea data = localDataSet.get(position);
        if (position == 0) {
            viewHolder.addItem.setVisibility(View.VISIBLE);
            viewHolder.removeItem.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.addItem.setVisibility(View.INVISIBLE);
            viewHolder.removeItem.setVisibility(View.VISIBLE);
        }


        viewHolder.floorCategoryAdapter.setCommonSpinnerClickListener(id -> {
            if (selectedId != null) {
                if (selectedId.contains(id)) {
                    if (context != null) {
                        viewHolder.spinnerFloorCategory.setText("");
                        viewHolder.spinnerFloorCategory.setTag(null);
                        context.showToast(context.getString(R.string.err_spinner_type_already_selected));
                        viewHolder.floorCategoryAdapter.setContent(String.valueOf(localDataSet.get(position).getFloorCategory()));
                    }
                } else {
                    selectedId.add(id);
                    int tempId = localDataSet.get(position).getFloorCategory();
                    selectedId.remove(String.valueOf(tempId));
                    localDataSet.get(position).setFloorCategory(Integer.parseInt(id));
                }
            }
        });
        final String floorArea = data.getFloorArea();
        final int floorCategory = data.getFloorCategory();
        Objects.requireNonNull(viewHolder.layoutFloorArea.getEditText()).setText(floorArea);
        if (floorCategory == -999) {
            viewHolder.spinnerFloorCategory.setText("");
            viewHolder.spinnerFloorCategory.setTag(null);
        } else {
            viewHolder.floorCategoryAdapter.setContent(String.valueOf(floorCategory));
        }

        if (validateData) {
            if (viewHolder.spinnerFloorCategory.getTag() == null) {
                viewHolder.layoutFloorCategory.setError(context.getString(R.string.err_floor_category));
            } else {
                viewHolder.layoutFloorCategory.setErrorEnabled(false);
            }
            if (Objects.requireNonNull(viewHolder.layoutFloorArea.getEditText()).getText().length() == 0) {
                viewHolder.layoutFloorArea.setError(context.getString(R.string.err_floor_area));
            } else {
                viewHolder.layoutFloorArea.setErrorEnabled(false);
            }
        } else {
            viewHolder.layoutFloorCategory.setErrorEnabled(false);
            viewHolder.layoutFloorArea.setErrorEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }

    public ArrayList<BuildingAssets.FloorArea> getLocalDataSet() {
        return localDataSet == null ? new ArrayList<>() : localDataSet;
    }

    public interface RemoveListener {
        void onRemove(int position, long pk);
    }
}
