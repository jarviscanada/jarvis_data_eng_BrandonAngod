package ca.jrvs.apps.twitter.Service;

import ca.jrvs.apps.twitter.DAO.TwitterDAO;
import ca.jrvs.apps.twitter.model.Tweet;

public class TwitterService {
    private TwitterDAO DAO;
    public boolean validateTweet(Tweet tweet){
        //Check Length
        if(tweet.getText().length() > 141 ){
            return false;
        }
        //Check Longittude lattitude
        Float[] coordinates=tweet.getCoordinates().getCoordinates();
        for(int i=0;i<2;i++){
            if(coordinates[i] >= 180 || coordinates[i] <= -180){
                return false;
            }
        }
        //Passed all checks return true
        return true;
    }
    public int postTweet(Tweet tweet){
        if(validateTweet(tweet)) {
            DAO.create(tweet);
            return 0;
        }
        else{
            return -1;
        }
    }

}
