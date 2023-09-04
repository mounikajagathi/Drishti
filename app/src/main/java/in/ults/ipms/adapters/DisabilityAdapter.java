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

public class DisabilityAdapter extends RecyclerView.Adapter<DisabilityAdapter.ViewHolder> {

    private ArrayList<BuildingAssets.Disability> localDataSet;
    private final Set<String> selectedId;
    private final BaseActivity context;
    private RemoveListener removeListener;
    private ArrayList<BuildingAssetSpinnerResponse.DisabilityTypes> disabilityList;
    private boolean validateData = false;

    public void setDisabilityList(ArrayList<BuildingAssetSpinnerResponse.DisabilityTypes> disabilityList) {
        this.disabilityList = disabilityList;
        notifyDataSetChanged();
    }

    public boolean validateData() {
        boolean isValidation = true;
        if (localDataSet != null) {
            for (BuildingAssets.Disability item : localDataSet) {
                if (item.getDisability() == -999) {
                    isValidation = false;
                }
                if (!(item.getDisabilityPercentage() != null && item.getDisabilityPercentage().length() > 0)) {
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

    public DisabilityAdapter(BaseActivity context) {
        this.context = context;
        localDataSet = new ArrayList<>();
        selectedId = new HashSet<>();
        localDataSet.add(new BuildingAssets.Disability("", -999));
    }

    public void setLocalDataSet(ArrayList<BuildingAssets.Disability> localDataSet) {
        if (localDataSet == null || localDataSet.size() == 0) {
            localDataSet = new ArrayList<>();
            localDataSet.add(new BuildingAssets.Disability("", -999));
        }
        this.localDataSet = localDataSet;
        for(BuildingAssets.Disability item : localDataSet){
            selectedId.add(String.valueOf(item.getDisability()));
        }
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView addItem;
        private final ImageView removeItem;
        private final TextInputLayout layoutDisability;
        private final IPMSSpinner spinnerDisability;
        private final TextInputLayout layoutDisabilityPercentage;
        CommonSpinnerAdapter<BuildingAssetSpinnerResponse.DisabilityTypes> disabilityAdapter;

        public ViewHolder(View view) {
            super(view);
            addItem = view.findViewById(R.id.addItem);
            removeItem = view.findViewById(R.id.removeItem);
            spinnerDisability = view.findViewById(R.id.srDisability);
            layoutDisability = view.findViewById(R.id.layoutDisability);
            layoutDisabilityPercentage = view.findViewById(R.id.layoutDisabilityPercentage);
            disabilityAdapter = CommonSpinnerAdapter.setAdapter(context, spinnerDisability, disabilityList);
            spinnerDisability.setAdapter(disabilityAdapter);

            addItem.setOnClickListener(v -> {
                localDataSet.add(new BuildingAssets.Disability("", -999));
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

            Objects.requireNonNull(layoutDisabilityPercentage.getEditText()).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (localDataSet.size() > getAbsoluteAdapterPosition()) {
                        localDataSet.get(getAbsoluteAdapterPosition()).setDisabilityPercentage(Objects.requireNonNull(layoutDisabilityPercentage.getEditText()).getText().toString());
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
            String categoryID = String.valueOf(localDataSet.get(position).getDisability());
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
                .inflate(R.layout.item_disability, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        BuildingAssets.Disability data = localDataSet.get(position);
        if (position == 0) {
            viewHolder.addItem.setVisibility(View.VISIBLE);
            viewHolder.removeItem.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.addItem.setVisibility(View.INVISIBLE);
            viewHolder.removeItem.setVisibility(View.VISIBLE);
        }


        viewHolder.disabilityAdapter.setCommonSpinnerClickListener(id -> {
            if (selectedId != null) {
                if (selectedId.contains(id)) {
                    if (context != null) {
                        viewHolder.spinnerDisability.setText("");
                        viewHolder.spinnerDisability.setTag(null);
                        context.showToast(context.getString(R.string.err_spinner_type_already_selected));
                        viewHolder.disabilityAdapter.setContent(String.valueOf(localDataSet.get(position).getDisability()));
                    }
                } else {
                    selectedId.add(id);
                    int tempId = localDataSet.get(position).getDisability();
                    selectedId.remove(String.valueOf(tempId));
                    localDataSet.get(position).setDisability(Integer.parseInt(id));
                }
            }
        });
        final String disabilityPercentage = data.getDisabilityPercentage();
        final int disability = data.getDisability();
        Objects.requireNonNull(viewHolder.layoutDisabilityPercentage.getEditText()).setText(disabilityPercentage);
        if (disability == -999) {
            viewHolder.spinnerDisability.setText("");
            viewHolder.spinnerDisability.setTag(null);
        } else {
            viewHolder.disabilityAdapter.setContent(String.valueOf(disability));
        }

        if (validateData) {
            if (viewHolder.spinnerDisability.getTag() == null) {
                viewHolder.layoutDisability.setError(context.getString(R.string.err_disability));
            } else {
                viewHolder.layoutDisability.setErrorEnabled(false);
            }
            if (Objects.requireNonNull(viewHolder.layoutDisabilityPercentage.getEditText()).getText().length() == 0) {
                viewHolder.layoutDisabilityPercentage.setError(context.getString(R.string.err_disability_percentage));
            } else {
                viewHolder.layoutDisabilityPercentage.setErrorEnabled(false);
            }
        } else {
            viewHolder.layoutDisability.setErrorEnabled(false);
            viewHolder.layoutDisabilityPercentage.setErrorEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }

    public ArrayList<BuildingAssets.Disability> getLocalDataSet() {
        return localDataSet == null ? new ArrayList<>() : localDataSet;
    }

    public interface RemoveListener {
        void onRemove(int position, long pk);
    }
}
