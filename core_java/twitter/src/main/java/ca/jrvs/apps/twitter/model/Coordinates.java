package ca.jrvs.apps.twitter.model;

public class Coordinates {
    private float[] coordinates;
    private String type;

    public Coordinates(float[] coordinates) {
        this.coordinates = coordinates;
    }

    public float[] getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(float[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
