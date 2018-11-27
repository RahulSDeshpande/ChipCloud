package fisk.chipcloud;

public class Chip {

    private int index;
    private String label;
    private boolean checked = false;
    private Object metadata;

    public Chip(int index, String label, Object metadata) {
        this.index = index;
        this.label = label;
        this.metadata = metadata;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }
}