package fisk.chipcloud;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;

import eu.fiskur.chipcloud.R;

public class ChipCloud implements View.OnClickListener {

    private final boolean USER_CLICK = true;
    private final boolean AUTO_CHECK = false;

    private final Context context;
    private final ViewGroup layout;
    private final SelectMode selectMode;
    private Typeface typeface = null;
    private StateListDrawable customDrawable = null;

    private ChipCloudConfig config = null;
    private ChipListener chipListener;
    // private ChipDeletedListener deletedListener;

    private boolean ignoreAutoChecks = false;
    private Drawable closeX = null;

    private List<Chip> chips;

    public ChipCloud(Context context, ViewGroup layout) {
        this.context = context;
        this.layout = layout;
        selectMode = SelectMode.multi;
        this.chips = new ArrayList<>(0);
    }

    public ChipCloud(Context context, ViewGroup layout, ChipCloudConfig config) {
        this.context = context;
        this.layout = layout;
        selectMode = config.selectMode;
        this.config = config;
        this.chips = new ArrayList<>(0);
    }

    public void setListener(ChipListener chipListener) {
        this.chipListener = chipListener;
    }

    /*
    public void setDeleteListener(ChipDeletedListener deletedListener) {
        this.deletedListener = deletedListener;
    }
    */

    public void setListener(ChipListener chipListener, boolean ignoreAutoChecks) {
        this.chipListener = chipListener;
        this.ignoreAutoChecks = ignoreAutoChecks;
    }

    // public <T> void addChips(T[] objects, List<Object> listMetadata) {
    public <T> void addChips(T[] objects) {

        for (T object : objects) {
            addChip(object, null);
        }
    }

    // public <T> void addChips(List<T> objects, List<Object> listMetadata) {
    public <T> void addChips(List<T> objects) {

        for (T object : objects) {
            addChip(object, null);
        }
    }

    // TODO
    // public <T> void addChips(T[] objects, List<Chip> chips) {}

    public void addChips(List<Chip> chips, boolean dummyTodo) {
        for (Chip chip : chips) {
            addChip(chip.getLabel(), chip.getMetadata());
        }
    }

    public <T> void addChip(T object, Object metadata) {
        addChip(object, metadata, null);
    }

    public <T> void addChipNoResize(T object, Object metadata, Drawable drawable) {
        addChip(object, metadata, drawable, false);
    }

    public <T> void addChip(T object, Object metadata, Drawable drawable) {
        addChip(object, metadata, drawable, true);
    }

    public <T> void addChip(T object, Object metadata, Drawable drawable, boolean resizeDrawable) {
        ToggleChip toggleChip;
        int chipHeight;
        if (config.useInsetPadding) {
            toggleChip = (ToggleChip) LayoutInflater.from(context).inflate(R.layout.inset_toggle_chip, layout, false);
            chipHeight = context.getResources().getDimensionPixelSize(R.dimen.inset_chip_height);
        } else {
            toggleChip = (ToggleChip) LayoutInflater.from(context).inflate(R.layout.toggle_chip, layout, false);
            chipHeight = context.getResources().getDimensionPixelSize(R.dimen.chip_height);
        }

        toggleChip.setLabel(object.toString());

        ConfigHelper.initialise(toggleChip, config);

        if (drawable != null) {
            if (resizeDrawable) {
                toggleChip.setDrawable(context, drawable);
            } else {
                toggleChip.setDrawableNoResize(context, drawable);
            }
        }

        if (config.selectMode == SelectMode.close) {
            if (closeX == null) {
                closeX = ConfigHelper.closeDrawable(context, config.closeTint);
            }
            toggleChip.showClose(closeX);
        }
        toggleChip.setMinHeight(chipHeight);
        toggleChip.setOnClickListener(this);

        layout.addView(toggleChip);

        if (layout.getChildCount() >= 0) {
            chips.add(new Chip(
                    (layout.getChildCount() - 1) < 0 ? 0 : (layout.getChildCount() - 1),
                    toggleChip.getLabel(),
                    metadata));
        }
    }

    public void setChecked(int index) {

        ToggleChip toggleChip = (ToggleChip) layout.getChildAt(index);
        check(toggleChip, true, AUTO_CHECK);
        // chips.get(index).setChecked(true);

        if (selectMode == SelectMode.single || selectMode == SelectMode.mandatory) {

            int childCount = layout.getChildCount();

            for (int i = 0; i < childCount; i++) {

                View child = layout.getChildAt(i);

                if (child != toggleChip) {
                    ToggleChip otherChip = (ToggleChip) child;
                    check(otherChip, false, AUTO_CHECK);
                    // chips.get(i).setChecked(false);
                }
            }
        }
    }

    public void setSelectedIndexes(int[] indexes, boolean selectOrDeselect) {
        if (selectMode == SelectMode.single || selectMode == SelectMode.mandatory) {
            return;
        }

        for (int index : indexes) {
            ToggleChip chip = (ToggleChip) layout.getChildAt(index);
            check(chip, selectOrDeselect, AUTO_CHECK);
            // chips.get(index).setChecked(selectOrDeselect);
        }
    }

    public void setSelectedIndexes(int[] indexes) {
        if (selectMode == SelectMode.single || selectMode == SelectMode.mandatory) {
            return;
        }

        for (int index : indexes) {
            ToggleChip chip = (ToggleChip) layout.getChildAt(index);
            check(chip, true, AUTO_CHECK);
            // chips.get(index).setChecked(true);
        }
    }

    public void deselectIndex(int index) {

        ToggleChip toggleChip = (ToggleChip) layout.getChildAt(index);

        switch (selectMode) {

            case multi:
            case single:
                check(toggleChip, false, AUTO_CHECK);
                // chips.get(index).setChecked(false);
                break;

            default:
                //
        }
    }

    public String getLabel(int index) {
        return ((ToggleChip) layout.getChildAt(index)).getText().toString();
    }

    public List<Chip> getAllChips() {
        return chips;
    }

    public List<Chip> getAllSelectedChips() {

        List<Chip> selectedChips = new ArrayList<>(0);

        for (Chip chip : chips) {
            if (chip.isChecked()) selectedChips.add(chip);
        }

        return selectedChips;
    }

    @Override
    public void onClick(View view) {

        ToggleChip clickedChip = (ToggleChip) view;

        switch (selectMode) {

            case multi:
                check(clickedChip, !clickedChip.isChecked(), USER_CLICK);
                break;

            case single:
                check(clickedChip, !clickedChip.isChecked(), USER_CLICK);

                if (clickedChip.isChecked()) {

                    int childCount = layout.getChildCount();

                    for (int i = 0; i < childCount; i++) {

                        View child = layout.getChildAt(i);

                        if (child != clickedChip) {
                            ToggleChip otherChip = (ToggleChip) child;
                            check(otherChip, false, AUTO_CHECK);
                        }
                    }
                }
                break;

            case mandatory:
                if (!clickedChip.isChecked()) {

                    check(clickedChip, true, USER_CLICK);
                    int childCount = layout.getChildCount();

                    for (int i = 0; i < childCount; i++) {

                        View child = layout.getChildAt(i);

                        if (child != clickedChip) {
                            ToggleChip otherChip = (ToggleChip) child;
                            check(otherChip, false, AUTO_CHECK);
                        }
                    }
                }
                break;

            case close:
                final int index = layout.indexOfChild(view);
                final ToggleChip deletedChip = (ToggleChip) view;

                if (config.closeAnimationPeriod != -1) {

                    AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
                    anim.setDuration(config.closeAnimationPeriod);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (chipListener != null) {
                                chipListener.chipDeleted(index, deletedChip.getLabel());
                            }
                            layout.removeView(deletedChip);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    view.startAnimation(anim);
                } else {
                    if (chipListener != null) {
                        chipListener.chipDeleted(index, deletedChip.getLabel());
                    }
                    layout.removeView(deletedChip);
                }

                break;

            case none:

            default:
                //do nothing
        }
    }

    private void check(ToggleChip toggleChip, boolean checked, boolean isUserClick) {

        toggleChip.setChecked(checked);
        ConfigHelper.update(toggleChip, config);

        if (chipListener != null) {

            if (!isUserClick && ignoreAutoChecks) {
                return;
            }

            int index = layout.indexOfChild(toggleChip);
            chips.get(index).setChecked(checked);

            chipListener.chipCheckedChange(index, toggleChip.getLabel(), checked, isUserClick);
        }
    }

    public enum SelectMode {
        multi,
        single,
        mandatory,
        close,
        none
    }
}