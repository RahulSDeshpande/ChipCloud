package fisk.chipcloud;

public interface ChipListener {

    void onChipCheckChanged(int index, String label, boolean checked, boolean userClick, Chip chip);

    // TODO
    // void onChipDeleted(int index, String label, boolean userClick);
    void onChipDeleted(int index, String label, Chip chip);
}