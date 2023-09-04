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

public class RoofTypeAdapter extends RecyclerView.Adapter<RoofTypeAdapter.ViewHolder> {

    private ArrayList<BuildingAssets.RoofType> localDataSet;
    private final Set<String> selectedId;
    private final BaseActivity context;
    private RemoveListener removeListener;
    private ArrayList<BuildingAssetSpinnerResponse.RoofCategories> roofCategoryList;
    private boolean validateData = false;

    public void setRoofCategoryList(ArrayList<BuildingAssetSpinnerResponse.RoofCategories> roofCategoryList) {
        this.roofCategoryList = roofCategoryList;
        notifyDataSetChanged();
    }

    public void setRemoveListener(RemoveListener removeListener) {
        this.removeListener = removeListener;
    }

    public boolean validateData() {
        boolean isValidation = true;
        if (localDataSet != null) {
            for (BuildingAssets.RoofType item : localDataSet) {
                if (item.getRoofType() == -999) {
                    isValidation = false;
                }
                if (!(item.getRoofPer() > 0)) {
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

    public RoofTypeAdapter(BaseActivity context) {
        this.context = context;
        localDataSet = new ArrayList<>();
        selectedId = new HashSet<>();
        localDataSet.add(new BuildingAssets.RoofType(0, -999));
    }

    public void setLocalDataSet(ArrayList<BuildingAssets.RoofType> localDataSet) {
        if (localDataSet == null || localDataSet.size() == 0) {
            localDataSet = new ArrayList<>();
            localDataSet.add(new BuildingAssets.RoofType(0, -999));
        }
        this.localDataSet = localDataSet;
        for (BuildingAssets.RoofType item : localDataSet) {
            selectedId.add(String.valueOf(item.getRoofType()));
        }
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView addItem;
        private final ImageView removeItem;
        private final TextInputLayout layoutRoofCategory;
        private final IPMSSpinner spinnerRoofCategory;
        private final TextInputLayout layoutRoofPercent;
        private final CommonSpinnerAdapter<BuildingAssetSpinnerResponse.RoofCategories> roofCategoryAdapter;

        public ViewHolder(View view) {
            super(view);
            addItem = view.findViewById(R.id.addItem);
            removeItem = view.findViewById(R.id.removeItem);
            spinnerRoofCategory = view.findViewById(R.id.srRoofCategory);
            layoutRoofCategory = view.findViewById(R.id.layoutRoofCategory);
            layoutRoofPercent = view.findViewById(R.id.layoutRoofPercent);
            roofCategoryAdapter = CommonSpinnerAdapter.setAdapter(context, spinnerRoofCategory, roofCategoryList);
            spinnerRoofCategory.setAdapter(roofCategoryAdapter);

            addItem.setOnClickListener(v -> {
                localDataSet.add(new BuildingAssets.RoofType(0, -999));
                notifyDataSetChanged();
            });

            removeItem.setOnClickListener(v -> {
                if (localDataSet.size() > getAbsoluteAdapterPosition() &&
                        localDataSet.get(getAbsoluteAdapterPosition()).getPk() != -1) {
                    if (removeListener != null) {
                        removeListener.onRemove(getAbsoluteAdapterPosition(), localDataSet.get(getAbsoluteAdapterPosition()).getPk());
                    }
                } else {
                    removeListData(getAbsoluteAdapterPosition());
                }
            });


            Objects.requireNonNull(layoutRoofPercent.getEditText()).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String roofPercent = layoutRoofPercent.getEditText().getText().toString();
                    if (localDataSet.size() > getAbsoluteAdapterPosition()) {
                        if (roofPercent.length() > 0) {
                            localDataSet.get(getAbsoluteAdapterPosition()).setRoofPer(Integer.parseInt(roofPercent));
                        }
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }
    }

    public void removeListData(int position) {
        if (localDataSet.size() > position) {
            String categoryID = String.valueOf(localDataSet.get(position).getRoofType());
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
                .inflate(R.layout.item_roof_type, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        BuildingAssets.RoofType data = localDataSet.get(position);
        if (position == 0) {
            viewHolder.addItem.setVisibility(View.VISIBLE);
            viewHolder.removeItem.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.addItem.setVisibility(View.INVISIBLE);
            viewHolder.removeItem.setVisibility(View.VISIBLE);
        }


        viewHolder.roofCategoryAdapter.setCommonSpinnerClickListener(id -> {
            if (selectedId != null) {
                if (selectedId.contains(id)) {
                    if (context != null) {
                        viewHolder.spinnerRoofCategory.setText("");
                        viewHolder.spinnerRoofCategory.setTag(null);
                        context.showToast(context.getString(R.string.err_spinner_type_already_selected));
                        viewHolder.roofCategoryAdapter.setContent(String.valueOf(localDataSet.get(position).getRoofType()));
                    }
                } else {
                    selectedId.add(id);
                    int tempId = localDataSet.get(position).getRoofType();
                    selectedId.remove(String.valueOf(tempId));
                    localDataSet.get(position).setRoofType(Integer.parseInt(id));
                }
            }
        });
        final int roofPercent = data.getRoofPer();
        final int roofCategory = data.getRoofType();
        Objects.requireNonNull(viewHolder.layoutRoofPercent.getEditText()).setText(String.valueOf(roofPercent));
        if (roofCategory == -999) {
            viewHolder.spinnerRoofCategory.setText("");
            viewHolder.spinnerRoofCategory.setTag(null);
        } else {
            viewHolder.roofCategoryAdapter.setContent(String.valueOf(roofCategory));
        }
        if (validateData) {
            if (viewHolder.spinnerRoofCategory.getTag() == null) {
                viewHolder.layoutRoofCategory.setError(context.getString(R.string.err_roof_category));
            } else {
                viewHolder.layoutRoofCategory.setErrorEnabled(false);
            }
            String tempPercent = Objects.requireNonNull(viewHolder.layoutRoofPercent.getEditText()).getText().toString();
            if (tempPercent.length() == 0 || Integer.parseInt(tempPercent) == 0) {
                viewHolder.layoutRoofPercent.setError(context.getString(R.string.err_roof_percentage));
            } else {
                viewHolder.layoutRoofPercent.setErrorEnabled(false);
            }
        } else {
            viewHolder.layoutRoofCategory.setErrorEnabled(false);
            viewHolder.layoutRoofPercent.setErrorEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }

    public ArrayList<BuildingAssets.RoofType> getLocalDataSet() {
        return localDataSet == null ? new ArrayList<>() : localDataSet;
    }

    public interface RemoveListener {
        void onRemove(int position, long pk);
    }
}
