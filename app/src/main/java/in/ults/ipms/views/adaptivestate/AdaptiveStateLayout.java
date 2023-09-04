package in.ults.ipms.views.adaptivestate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by Mohammed Shafeeq on 9/4/2018.
 */

@SuppressLint("UseSparseArrays")
public class AdaptiveStateLayout extends FrameLayout implements AdaptiveState {

    private LayoutInflater inflater;
    private final ViewGroup nullParent = null;
    private final List<View> contentViews = new ArrayList<>();
    private final HashMap<Integer, View> stateViews = new HashMap<>();
    private AdaptiveStateLayoutListener clickListener;
    private HashMap<Integer, AdaptiveStateItem> availableStates = new HashMap<>();


    public AdaptiveStateLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public AdaptiveStateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdaptiveStateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (child.getTag() == null) {
            contentViews.add(child);
        }
    }

    private void setContentVisibility(boolean visible) {
        for (View v : contentViews) {
            v.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    private void hideAllStates() {
        try {
            for (Integer v : stateViews.keySet()) {
                if (stateViews.get(v) != null) {
                    Objects.requireNonNull(stateViews.get(v)).setVisibility(View.GONE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void setActualLayout(@LayoutRes int actualLayoutId) {
        View stateLayout = inflater.inflate(actualLayoutId, nullParent);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        contentViews.clear();
        addView(stateLayout, layoutParams);
    }


    public void setActualLayoutView(View actualLayout) {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        contentViews.clear();
        addView(actualLayout, layoutParams);
    }


    public void addStates(int stateType, AdaptiveStateItem data) {
        availableStates.put(stateType, data);
    }

    public void addFullStates(HashMap<Integer, AdaptiveStateItem> availableStates) {
        if (availableStates != null) {
            this.availableStates = availableStates;
        }
    }

    @Override
    public void showActualState() {
        hideAllStates();
        setContentVisibility(true);
    }

    @Override
    public void showState(final int state) {
        hideAllStates();
        setContentVisibility(false);
        if (stateViews.containsKey(state) &&
                stateViews.get(state) != null) {
            Objects.requireNonNull(stateViews.get(state)).setVisibility(View.VISIBLE);
        } else if (availableStates != null &&
                availableStates.containsKey(state)) {
            AdaptiveStateItem object = availableStates.get(state);
            View stateLayout = inflater.inflate(Objects.requireNonNull(object).getLayoutID(), nullParent);
            stateLayout.setTag(state);
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.gravity = Gravity.CENTER;
            for (int buttonId : object.getButtonId()) {
                if (buttonId!= -1 && stateLayout.findViewById(buttonId) != null) {
                    stateLayout.findViewById(buttonId).setOnClickListener(v -> {
                        if (clickListener != null) {
                            clickListener.onAdaptiveStateClick(state, buttonId);
                        }
                    });
                }
            }
            stateViews.put(state, stateLayout);
            addView(stateLayout, layoutParams);
        }
    }

    public interface AdaptiveStateLayoutListener {
        void onAdaptiveStateClick(int stateType, int buttonId);
    }

    public void setOnClickListener(AdaptiveStateLayoutListener clickListener) {
        this.clickListener = clickListener;
    }


}
