package fisk.chipcloud;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

import eu.fiskur.chipcloud.R;

class ConfigHelper {

  static Drawable closeDrawable(Context context, int xColour){
    Drawable closeX = ContextCompat.getDrawable(context, R.drawable.cross);
    Drawable wrappedX = closeX.mutate();
    wrappedX = DrawableCompat.wrap(wrappedX);
    DrawableCompat.setTint(wrappedX, xColour);
    DrawableCompat.setTintMode(wrappedX, PorterDuff.Mode.SRC_IN);
    return wrappedX;
  }

  static void initialise(ToggleChip toggleChip, ChipCloudConfig config){
    if(config != null){
        toggleChip.getBackground().setColorFilter(config.uncheckedChipColor, PorterDuff.Mode.SRC);
        toggleChip.setTextColor(config.uncheckedTextColor);
      if(config.typeface != null){
        toggleChip.setTypeface(config.typeface);
      }
    }
  }

  static void update(ToggleChip toggleChip, ChipCloudConfig config){
    if(config != null) {
      if (toggleChip.isChecked()) {
          toggleChip.getBackground().setColorFilter(config.checkedChipColor, PorterDuff.Mode.SRC);
          toggleChip.setTextColor(config.checkedTextColor);
      } else {
          toggleChip.getBackground().setColorFilter(config.uncheckedChipColor, PorterDuff.Mode.SRC);
          toggleChip.setTextColor(config.uncheckedTextColor);
      }
    }
  }
}
