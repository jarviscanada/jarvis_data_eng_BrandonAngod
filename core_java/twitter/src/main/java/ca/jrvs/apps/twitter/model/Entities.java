package ca.jrvs.apps.twitter.model;

import java.net.URL;

public class Entities {
    private HashTag[] hashtags;
    private URL urls;
    private UserMention[] userMentions;

    public HashTag[] getHashtags() {
        return hashtags;
    }

    public void setHashtags(HashTag[] hashtags) {
        this.hashtags = hashtags;
    }

    public URL getUrls() {
        return urls;
    }

    public void setUrls(URL urls) {
        this.urls = urls;
    }

    public UserMention[] getUserMentions() {
        return userMentions;
    }

    public void setUserMentions(UserMention[] userMentions) {
        this.userMentions = userMentions;
    }
}
