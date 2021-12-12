package ca.jrvs.apps.twitter.Service;

import ca.jrvs.apps.twitter.interfaces.CrdDao;
import ca.jrvs.apps.twitter.interfaces.Service;
import ca.jrvs.apps.twitter.model.Tweet;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TwitterService implements Service {
    private CrdDao DAO;

    public TwitterService(CrdDao dao) {
        this.DAO = dao;
    }
    public TwitterService(){}
    public boolean validateTweet(Tweet tweet){
        //Check Length
        if(tweet.getText().length() > 141 ){
            return false;
        }
        //Check Longittude lattitude
        float[] coordinates=tweet.getCoordinates().getCoordinates();
        for(int i=0;i<2;i++){
            if(coordinates[i] >= 180 || coordinates[i] <= -180){
                return false;
            }
        }
        //Passed all checks return true
        return true;
    }
    public Tweet postTweet(Tweet tweet){
        if(validateTweet(tweet)) {
            return (Tweet) DAO.create(tweet);
        }
        else{
            return null;
        }
    }

    @Override
    public Tweet showTweet(String id,String[] fields) throws URISyntaxException {
        Tweet tweet = (Tweet) DAO.findById(id);
        for(int i=0;i<fields.length;i++) {
            if (tweet.getText().equals(fields[i])) {
                tweet.setText(null);
            }
        }
        return tweet;
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) throws URISyntaxException {
        List<Tweet> tweet = new ArrayList<Tweet>();
        for(int i=0;i<ids.length;i++) {
            tweet.add(DAO.deleteById(ids[i]));
        }
        return tweet;

    }

}
