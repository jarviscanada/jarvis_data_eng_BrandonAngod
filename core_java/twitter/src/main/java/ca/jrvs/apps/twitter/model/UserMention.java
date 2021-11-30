package ca.jrvs.apps.twitter.model;

public class UserMention {
    private int id;
    private String id_str,name,screen_name;
    private int[] indicies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public int[] getIndicies() {
        return indicies;
    }

    public void setIndicies(int[] indicies) {
        this.indicies = indicies;
    }
}
