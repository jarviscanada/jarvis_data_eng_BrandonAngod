package ca.jrvs.apps.twitter.model;

import java.math.BigInteger;
import java.net.URI;

public class Tweet {
    private String created_at,text,id_str,source;
    private BigInteger id;
    //User user;
    private URI uri;


    private int retweet_count,favorite_count;
    private Coordinates cordinates;
    private Entities entities;
    private Boolean truncated,favorited,retweeted;

    public Tweet(String body, float[] cord){
        this.text = body;
        this.cordinates.setCoordinates(cord);
    }
    public Tweet(String body, Coordinates cord){
        this.text = body;
        this.cordinates = cord;
    }

    public Tweet() {

    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = new BigInteger(id);
    }

    public int getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(int retweet_count) {
        this.retweet_count = retweet_count;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public Coordinates getCoordinates() {
        return cordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.cordinates = coordinates;
    }

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public Boolean getTruncated() {
        return truncated;
    }

    public void setTruncated(Boolean truncated) {
        this.truncated = truncated;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public Boolean getRetweeted() {
        return retweeted;
    }

    public void setRetweeted(Boolean retweeted) {
        this.retweeted = retweeted;
    }
}
