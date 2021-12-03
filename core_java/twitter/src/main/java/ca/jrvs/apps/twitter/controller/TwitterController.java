package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.interfaces.Controller;
import ca.jrvs.apps.twitter.interfaces.Service;
import ca.jrvs.apps.twitter.model.Tweet;

import java.util.List;

public class TwitterController implements Controller {
    private static final String COORD_SEP=":";
    private static final String COMMA=",";

    private Service service;
    @Override
    public Tweet postTweet(String[] args) {
        //Check Arguments
        if(args.length!=3){
            //False amount of arguemnts
            throw new IllegalArgumentException("Usage: TwitterCLIApp post / message / longitude:latitude");
        }
        String body = args[1];
        String coordinates = args[2];
        String[] coordinateString=coordinates.split(COORD_SEP);
        Float[] cordFloatArray = new Float[2];
        for(int i=0;i<2;i++){
            cordFloatArray[i] = Float.parseFloat(coordinateString[i])
        }

        Tweet tweet = new Tweet(body,cordFloatArray);

        return service.postTweet(tweet);
    }

    @Override
    public Tweet showTweet(String[] args) {
        return null;
    }

    @Override
    public List<Tweet> deleteTweet(String[] args) {
        return null;
    }
}
