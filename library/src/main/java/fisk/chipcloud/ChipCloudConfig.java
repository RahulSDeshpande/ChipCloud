package fisk.chipcloud;

import android.graphics.Typeface;

public class ChipCloudConfig {

  public Typeface typeface = null;
  public int checkedChipColor = -1;
  public int uncheckedChipColor = -1;
  public int checkedTextColor = -1;
  public int uncheckedTextColor = -1;
  public ChipCloud.SelectMode selectMode = ChipCloud.SelectMode.multi;
  public boolean useInsetPadding = false;
  public long closeAnimationPeriod = -1;
  public int closeTint = -1;

  public ChipCloudConfig(){
  }

  public ChipCloudConfig typeface(Typeface typeface){
    this.typeface = typeface;
    return this;
  }

  public ChipCloudConfig checkedChipColor(int checkedChipColor){
    this.checkedChipColor = checkedChipColor;
    return this;
  }

  public ChipCloudConfig uncheckedChipColor(int uncheckedChipColor){
    this.uncheckedChipColor = uncheckedChipColor;
    return this;
  }

  public ChipCloudConfig checkedTextColor(int checkedTextColor){
    this.checkedTextColor = checkedTextColor;
    return this;
  }

  public ChipCloudConfig uncheckedTextColor(int uncheckedTextColor){
    this.uncheckedTextColor = uncheckedTextColor;
    return this;
  }

  public ChipCloudConfig selectMode(ChipCloud.SelectMode selectMode){
    this.selectMode = selectMode;
    return this;
  }

  public ChipCloudConfig useInsetPadding(boolean useInsetPadding){
    this.useInsetPadding = useInsetPadding;
    return this;
  }

  public ChipCloudConfig showClose(int closeTint){
    this.selectMode = ChipCloud.SelectMode.close;
    this.closeTint = closeTint;
    return this;
  }

  public ChipCloudConfig showClose(int closeTint, long closeAnimationPeriod){
    this.selectMode = ChipCloud.SelectMode.close;
    this.closeTint = closeTint;
    this.closeAnimationPeriod = closeAnimationPeriod;
    return this;
  }
}
