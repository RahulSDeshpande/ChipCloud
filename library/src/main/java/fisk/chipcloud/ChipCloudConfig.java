package fisk.chipcloud;

import android.graphics.Typeface;

public class ChipCloudConfig {

    Typeface typeface = null;

    // Checked/Unchecked Chip color
    int checkedChipColor = -1;
    int uncheckedChipColor = -1;
    int checkedTextColor = -1;
    int uncheckedTextColor = -1;

    // Chip text size
    float chipTextSize = 13f;

    // TODO | Chip padding
    int chipPaddingStart = 9;
    int chipPaddingTop = 0;
    int chipPaddingEnd = 9;
    int chipPaddingBottom = 0;

    // Chip margin
    int chipMarginStart = 0;
    int chipMarginTop = 0;
    int chipMarginEnd = 0;
    int chipMarginBottom = 0;

    // TODO | Chip Stroke Color
    int chipStrokeColor = -1;
    int chipStrokeWidth = 1;

    public ChipCloud.SelectMode selectMode = ChipCloud.SelectMode.multi;

    public boolean useInsetPadding = false;

    long closeAnimationPeriod = -1;
    int closeTint = -1;

    public ChipCloudConfig() {
    }

    public ChipCloudConfig typeface(Typeface typeface) {
        this.typeface = typeface;
        return this;
    }

    public ChipCloudConfig checkedChipColor(int checkedChipColor) {
        this.checkedChipColor = checkedChipColor;
        return this;
    }

    public ChipCloudConfig uncheckedChipColor(int uncheckedChipColor) {
        this.uncheckedChipColor = uncheckedChipColor;
        return this;
    }

    public ChipCloudConfig checkedTextColor(int checkedTextColor) {
        this.checkedTextColor = checkedTextColor;
        return this;
    }

    public ChipCloudConfig uncheckedTextColor(int uncheckedTextColor) {
        this.uncheckedTextColor = uncheckedTextColor;
        return this;
    }

    public ChipCloudConfig chipTextSize(float chipTextSize) {
        this.chipTextSize = chipTextSize;
        return this;
    }

    public ChipCloudConfig chipPadding(int chipPaddingStart,
                                       int chipPaddingTop,
                                       int chipPaddingEnd,
                                       int chipPaddingBottom) {
        this.chipPaddingStart = chipPaddingStart;
        this.chipPaddingTop = chipPaddingTop;
        this.chipPaddingEnd = chipPaddingEnd;
        this.chipPaddingBottom = chipPaddingBottom;
        return this;
    }

    public ChipCloudConfig chipMargin(int chipMarginStart,
                                      int chipMarginTop,
                                      int chipMarginEnd,
                                      int chipMarginBottom) {
        this.chipMarginStart = chipMarginStart;
        this.chipMarginTop = chipMarginTop;
        this.chipMarginEnd = chipMarginEnd;
        this.chipMarginBottom = chipMarginBottom;
        return this;
    }

    public ChipCloudConfig selectMode(ChipCloud.SelectMode selectMode) {
        this.selectMode = selectMode;
        return this;
    }

    public ChipCloudConfig useInsetPadding(boolean useInsetPadding) {
        this.useInsetPadding = useInsetPadding;
        return this;
    }

    public ChipCloudConfig showClose(int closeTint) {
        this.selectMode = ChipCloud.SelectMode.close;
        this.closeTint = closeTint;
        return this;
    }

    public ChipCloudConfig showClose(int closeTint, long closeAnimationPeriod) {
        this.selectMode = ChipCloud.SelectMode.close;
        this.closeTint = closeTint;
        this.closeAnimationPeriod = closeAnimationPeriod;
        return this;
    }
}