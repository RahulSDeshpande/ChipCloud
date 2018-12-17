package fisk.chipclouddemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexboxLayout;

import fisk.chipcloud.ChipCloud;
import fisk.chipcloud.ChipCloudConfig;
import fisk.chipcloud.ChipListener;
import fisk.chipclouddemo.demo.R;

public class DemoActivity extends AppCompatActivity {

    private final String TAG = "DemoActivity";

    private TextView tvSelectedChipsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        FlexboxLayout flexboxDrawable = findViewById(R.id.flexbox_drawable);

        ChipCloudConfig drawableConfig = new ChipCloudConfig()
                .selectMode(ChipCloud.SelectMode.multi)
                .checkedChipColor(Color.parseColor("#ddaa00"))
                .checkedTextColor(Color.parseColor("#ffffff"))
                .uncheckedChipColor(Color.parseColor("#e0e0e0"))
                .uncheckedTextColor(Color.parseColor("#000000"))
                .chipTextSize(16f)
                .chipPadding(20, 10, 20, 10)
                .chipMargin(10, 5, 10, 5);

        ChipCloud drawableChipCloud = new ChipCloud(this, flexboxDrawable, drawableConfig);
        drawableChipCloud.addChip("Anna A", ContextCompat.getDrawable(this, R.drawable.anna_a));
        drawableChipCloud.addChip("Anna B", ContextCompat.getDrawable(this, R.drawable.anna_b));
        drawableChipCloud.addChip("Anna C", ContextCompat.getDrawable(this, R.drawable.anna_c));
        drawableChipCloud.addChip("Anna D", ContextCompat.getDrawable(this, R.drawable.anna_d));
        drawableChipCloud.addChip("Anna E", ContextCompat.getDrawable(this, R.drawable.anna_e));

        FlexboxLayout flexboxDrawableWithClose = findViewById(R.id.flexbox_drawable_close);

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

        FlexboxLayout flexbox = findViewById(R.id.flexbox);
        flexbox.setAlignItems(AlignItems.CENTER);

        ChipCloudConfig config = new ChipCloudConfig()
                .selectMode(ChipCloud.SelectMode.multi)
                .checkedTextColor(Color.parseColor("#ffffff"))
                .uncheckedTextColor(Color.parseColor("#333333"))
                .chipTextSize(16f)
                .chipPadding(20, 10, 20, 10);

        final ChipCloud chipCloud = new ChipCloud(this, flexbox, config);

        chipCloud.addChip("HelloWorld!", null);

        String[] demoArray = getResources().getStringArray(R.array.demo_array);
        chipCloud.addChips(demoArray);

        // chipCloud.setChecked(2);

        String label = chipCloud.getLabel(2);
        Log.d(TAG, "Label at index 2: " + label);

        tvSelectedChipsCount = findViewById(R.id.tv_selected_chips_count);

        chipCloud.setListener(new ChipListener() {
            @Override
            public void chipCheckedChange(int index, String label, boolean checked, boolean userClick) {
                if (userClick) {
                    Log.d(TAG, String.format("chipCheckedChange Label at index: %d checked: %s", index, checked));
                }

                tvSelectedChipsCount.setText("Chips selected: " + chipCloud.getAllSelectedChips().size());
            }

            @Override
            public void chipDeleted(int index, String label) {
                Log.d(TAG, String.format("chipDeleted at index: %d label: %s", index, label));
                tvSelectedChipsCount.setText("Chips selected: " + chipCloud.getAllSelectedChips().size());
            }
        });

        chipCloud.setChecked(2);

        //Deleteable
        FlexboxLayout deleteableFlexbox = findViewById(R.id.flexbox_deleteable);

        ChipCloudConfig deleteableConfig = new ChipCloudConfig()
                .selectMode(ChipCloud.SelectMode.multi)
                .checkedChipColor(Color.parseColor("#ddaa00"))
                .checkedTextColor(Color.parseColor("#ffffff"))
                .uncheckedChipColor(Color.parseColor("#e0e0e0"))
                .showClose(Color.parseColor("#a6a6a6"))
                .useInsetPadding(false)
                .uncheckedTextColor(Color.parseColor("#000000"));

        ChipCloud deleteableCloud = new ChipCloud(this, deleteableFlexbox, deleteableConfig);
        deleteableCloud.addChip("Ardvark", null);
        deleteableCloud.addChip("Baboon", null);
        deleteableCloud.addChip("Cat", null);
        deleteableCloud.addChip("Dog", null);
        deleteableCloud.addChip("Eel", null);
        deleteableCloud.addChip("Fox", null);
        deleteableCloud.addChip("Giraffe", null);
        deleteableCloud.addChip("Hyena", null);
        deleteableCloud.addChip("Iguana", null);

        deleteableCloud.setListener(new ChipListener() {
            @Override
            public void chipCheckedChange(int index, String label, boolean checked, boolean userClick) {
                if (userClick) {
                    Log.d(TAG, String.format("chipCheckedChange Label at index: %d checked: %s", index, checked));
                }
            }

            @Override
            public void chipDeleted(int index, String label) {
                Log.d(TAG, String.format("chipDeleted at index: %d label: %s", index, label));
            }
        });

        //Horizontal Scroll
        LinearLayout horizontalScroll = findViewById(R.id.horizontal_layout);
        config.useInsetPadding = true;
        config.selectMode = ChipCloud.SelectMode.multi;
        ChipCloud horizontalChipCloud = new ChipCloud(this, horizontalScroll, config);
        horizontalChipCloud.addChips(demoArray);
    }
}