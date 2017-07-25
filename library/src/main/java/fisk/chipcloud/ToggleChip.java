package fisk.chipcloud;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import eu.fiskur.chipcloud.R;

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
      int paddingRight = getResources().getDimensionPixelOffset(R.dimen.eight_dp);
      setPadding(0, 0, paddingRight, 0);
    }else{
      setCompoundDrawablesWithIntrinsicBounds(null, null, closeDrawable, null);
      int paddingLeft = getResources().getDimensionPixelOffset(R.dimen.twelve_dp);
      int paddingRight = getResources().getDimensionPixelOffset(R.dimen.eight_dp);
      setPadding(paddingLeft, 0, paddingRight, 0);
    }


  }

  public void setDrawableNoResize(Context context, Drawable drawable){
    if(getCompoundDrawables()[2] != null) {
      setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
      int paddingRight = getResources().getDimensionPixelOffset(R.dimen.eight_dp);
      setPadding(0, 0, paddingRight, 0);
    }else{
      setCompoundDrawablesWithIntrinsicBounds(drawable, null, getCompoundDrawables()[2], null);
      int paddingRight = getResources().getDimensionPixelOffset(R.dimen.twelve_dp);
      setPadding(0, 0, paddingRight, 0);
    }
  }

  public void setDrawable(Context context, Drawable drawable){
    RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), drawableToBitmap(drawable));
    imageDrawable.setCircular(true);
    imageDrawable.setCornerRadius(Math.max(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()) / 2.0f);

    if(getCompoundDrawables()[2] != null) {
      setCompoundDrawablesWithIntrinsicBounds(imageDrawable, null, null, null);
      int paddingRight = getResources().getDimensionPixelOffset(R.dimen.eight_dp);
      setPadding(0, 0, paddingRight, 0);
    }else{
      setCompoundDrawablesWithIntrinsicBounds(imageDrawable, null, getCompoundDrawables()[2], null);
      int paddingRight = getResources().getDimensionPixelOffset(R.dimen.twelve_dp);
      setPadding(0, 0, paddingRight, 0);
    }
  }

  private Bitmap drawableToBitmap (Drawable drawable) {
    if (drawable instanceof BitmapDrawable) {
      Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
      Bitmap scaled = Bitmap.createScaledBitmap(bitmap, getResources().getDimensionPixelOffset(R.dimen.thirtytwo_dp), getResources().getDimensionPixelOffset(R.dimen.thirtytwo_dp), false);
      return scaled;
    }

    Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
    drawable.draw(canvas);

    Bitmap scaled = Bitmap.createScaledBitmap(bitmap, getResources().getDimensionPixelOffset(R.dimen.thirtytwo_dp), getResources().getDimensionPixelOffset(R.dimen.thirtytwo_dp), false);
    return scaled;
  }

  public void setLabel(String label){
    setText(label);
  }
}
