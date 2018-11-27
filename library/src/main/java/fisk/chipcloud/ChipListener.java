package fisk.chipcloud;

public interface ChipListener {

    void chipCheckedChange(int index, String label, boolean checked, boolean userClick);

    // TODO
    // void chipDeleted(int index, String label, boolean userClick);
    void chipDeleted(int index, String label);
}