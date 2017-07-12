package fisk.chipcloud;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

//Lint is recommending useing AppCompat but that'd add a dependency on the support lib, want to avoid that if possible:
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

  public void setLabel(String label){
    setText(label);
  }
}
