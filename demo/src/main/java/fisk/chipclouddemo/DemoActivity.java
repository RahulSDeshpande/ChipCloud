package fisk.chipclouddemo;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.google.android.flexbox.FlexboxLayout;

import fisk.chipcloud.ChipCloud;
import fisk.chipcloud.ChipCloudConfig;
import fisk.chipcloud.ChipDeletedListener;
import fisk.chipcloud.ChipListener;
import fisk.chipclouddemo.demo.R;

public class DemoActivity extends AppCompatActivity {

  private static final String TAG = "DemoActivity";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo);

    FlexboxLayout flexboxDrawable = (FlexboxLayout) findViewById(R.id.flexbox_drawable);

    ChipCloudConfig drawableConfig = new ChipCloudConfig()
        .selectMode(ChipCloud.SelectMode.multi)
        .checkedChipColor(Color.parseColor("#ddaa00"))
        .checkedTextColor(Color.parseColor("#ffffff"))
        .uncheckedChipColor(Color.parseColor("#e0e0e0"))
        .uncheckedTextColor(Color.parseColor("#000000"));

    ChipCloud drawableChipCloud = new ChipCloud(this, flexboxDrawable, drawableConfig);
    drawableChipCloud.addChip("Anna A", ContextCompat.getDrawable(this, R.drawable.anna_a));
    drawableChipCloud.addChip("Anna B", ContextCompat.getDrawable(this, R.drawable.anna_b));
    drawableChipCloud.addChip("Anna C", ContextCompat.getDrawable(this, R.drawable.anna_c));
    drawableChipCloud.addChip("Anna D", ContextCompat.getDrawable(this, R.drawable.anna_d));
    drawableChipCloud.addChip("Anna E", ContextCompat.getDrawable(this, R.drawable.anna_e));


    FlexboxLayout flexboxDrawableWithClose = (FlexboxLayout) findViewById(R.id.flexbox_drawable_close);

    ChipCloudConfig drawableWithCloseConfig = new ChipCloudConfig()
        .selectMode(ChipCloud.SelectMode.multi)
        .checkedChipColor(Color.parseColor("#ddaa00"))
        .checkedTextColor(Color.parseColor("#ffffff"))
        .uncheckedChipColor(Color.parseColor("#e0e0e0"))
        .uncheckedTextColor(Color.parseColor("#000000"))
        .showClose(Color.parseColor("#a6a6a6"), 500);

    ChipCloud drawableWithCloseChipCloud = new ChipCloud(this, flexboxDrawableWithClose, drawableWithCloseConfig);
    drawableWithCloseChipCloud.addChip("Anna A", ContextCompat.getDrawable(this, R.drawable.anna_a));
    drawableWithCloseChipCloud.addChip("Anna B", ContextCompat.getDrawable(this, R.drawable.anna_b));
    drawableWithCloseChipCloud.addChip("Anna C", ContextCompat.getDrawable(this, R.drawable.anna_c));
    drawableWithCloseChipCloud.addChip("Anna D", ContextCompat.getDrawable(this, R.drawable.anna_d));
    drawableWithCloseChipCloud.addChip("Anna E", ContextCompat.getDrawable(this, R.drawable.anna_e));

    FlexboxLayout flexbox = (FlexboxLayout) findViewById(R.id.flexbox);

    ChipCloudConfig config = new ChipCloudConfig()
        .selectMode(ChipCloud.SelectMode.multi)
        .checkedChipColor(Color.parseColor("#ddaa00"))
        .checkedTextColor(Color.parseColor("#ffffff"))
        .uncheckedChipColor(Color.parseColor("#e0e0e0"))
        .uncheckedTextColor(Color.parseColor("#000000"));

    ChipCloud chipCloud = new ChipCloud(this, flexbox, config);

    chipCloud.addChip("HelloWorld!");

    String[] demoArray = getResources().getStringArray(R.array.demo_array);
    chipCloud.addChips(demoArray);

    chipCloud.setChecked(2);

    String label = chipCloud.getLabel(2);
    Log.d(TAG, "Label at index 2: " + label);

    chipCloud.setListener(new ChipListener() {
      @Override
      public void chipCheckedChange(int index, boolean checked, boolean userClick) {
        if(userClick) {
          Log.d(TAG, String.format("chipCheckedChange Label at index: %d checked: %s", index, checked));
        }
      }
    });

    //Deleteable
    FlexboxLayout deleteableFlexbox = (FlexboxLayout) findViewById(R.id.flexbox_deleteable);

    ChipCloudConfig deleteableConfig = new ChipCloudConfig()
        .selectMode(ChipCloud.SelectMode.multi)
        .checkedChipColor(Color.parseColor("#ddaa00"))
        .checkedTextColor(Color.parseColor("#ffffff"))
        .uncheckedChipColor(Color.parseColor("#e0e0e0"))
        .showClose(Color.parseColor("#a6a6a6"))
        .useInsetPadding(false)
        .uncheckedTextColor(Color.parseColor("#000000"));

    ChipCloud deleteableCloud = new ChipCloud(this, deleteableFlexbox, deleteableConfig);
    deleteableCloud.addChip("Ardvark");
    deleteableCloud.addChip("Baboon");
    deleteableCloud.addChip("Cat");
    deleteableCloud.addChip("Dog");
    deleteableCloud.addChip("Eel");
    deleteableCloud.addChip("Fox");
    deleteableCloud.addChip("Giraffe");
    deleteableCloud.addChip("Hyena");
    deleteableCloud.addChip("Iguana");

    deleteableCloud.setDeleteListener(new ChipDeletedListener() {
      @Override
      public void chipDeleted(int index, String label) {
        Log.d(TAG, String.format("chipDeleted at index: %d label: %s", index, label));
      }
    });

    //Horizontal Scroll
    LinearLayout horizontalScroll = (LinearLayout) findViewById(R.id.horizontal_layout);
    config.useInsetPadding = true;
    config.selectMode = ChipCloud.SelectMode.multi;
    ChipCloud horizontalChipCloud = new ChipCloud(this, horizontalScroll, config);
    horizontalChipCloud.addChips(demoArray);
  }
}
