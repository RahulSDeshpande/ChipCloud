package fisk.chipcloud;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;

import eu.fiskur.chipcloud.R;

public class ToggleChip extends AppCompatCheckedTextView {

    public ToggleChip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ToggleChip(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToggleChip(Context context) {
        super(context);
    }

    public void showClose(Drawable closeDrawable) {
        if (getCompoundDrawables()[0] != null) {
            setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0], null, closeDrawable, null);
            int paddingRight = getResources().getDimensionPixelOffset(R.dimen.eight_dp);
            setPadding(0, 0, paddingRight, 0);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, closeDrawable, null);
            int paddingLeft = getResources().getDimensionPixelOffset(R.dimen.twelve_dp);
            int paddingRight = getResources().getDimensionPixelOffset(R.dimen.eight_dp);
            setPadding(paddingLeft, 0, paddingRight, 0);
        }
    }

    public void setDrawableNoResize(Context context, Drawable drawable) {
        if (getCompoundDrawables()[2] != null) {
            setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            int paddingRight = getResources().getDimensionPixelOffset(R.dimen.eight_dp);
            setPadding(0, 0, paddingRight, 0);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(drawable, null, getCompoundDrawables()[2], null);
            int paddingRight = getResources().getDimensionPixelOffset(R.dimen.twelve_dp);
            setPadding(0, 0, paddingRight, 0);
        }
    }

    public void setDrawable(Context context, Drawable drawable) {
        RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), drawableToBitmap(drawable));
        imageDrawable.setCircular(true);
        imageDrawable.setCornerRadius(Math.max(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()) / 2.0f);

        if (getCompoundDrawables()[2] != null) {
            setCompoundDrawablesWithIntrinsicBounds(imageDrawable, null, null, null);
            int paddingRight = getResources().getDimensionPixelOffset(R.dimen.eight_dp);
            setPadding(0, 0, paddingRight, 0);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(imageDrawable, null, getCompoundDrawables()[2], null);
            int paddingRight = getResources().getDimensionPixelOffset(R.dimen.twelve_dp);
            setPadding(0, 0, paddingRight, 0);
        }
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            return Bitmap.createScaledBitmap(bitmap, getResources().getDimensionPixelOffset(R.dimen.thirtytwo_dp), getResources().getDimensionPixelOffset(R.dimen.thirtytwo_dp), false);
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return Bitmap.createScaledBitmap(bitmap, getResources().getDimensionPixelOffset(R.dimen.thirtytwo_dp), getResources().getDimensionPixelOffset(R.dimen.thirtytwo_dp), false);
    }

    public String getLabel() {
        return getText().toString();
    }

    public void setLabel(String label) {
        setText(label);
    }
}