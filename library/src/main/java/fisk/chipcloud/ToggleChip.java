package fisk.chipcloud;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

//Lint is recommending using AppCompat but that'd add a dependency on the support lib, want to avoid that if possible:
@SuppressLint("AppCompatCustomView")
public class ToggleChip extends CheckedTextView {

  public ToggleChip(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public ToggleChip(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ToggleChip(Context context) {
    super(context);
  }

  public void showClose(Drawable closeDrawable){
    if(getCompoundDrawables()[0] != null){
      setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0], null, closeDrawable, null);
    }else{
      setCompoundDrawablesWithIntrinsicBounds(null, null, closeDrawable, null);
    }
  }

  public void setDrawable(Drawable drawable){
    if(getCompoundDrawables()[2] != null) {
      setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }else{
      setCompoundDrawablesWithIntrinsicBounds(drawable, null, getCompoundDrawables()[2], null);
    }
  }

  public void setLabel(String label){
    setText(label);
  }
}
