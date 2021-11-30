package ca.jrvs.apps.twitter.model;

public class HashTag {
    private int[] indicies;
    private String text;

    public int[] getIndicies() {
        return indicies;
    }

    public void setIndicies(int[] indicies) {
        this.indicies = indicies;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
