package ca.jrvs.apps.twitter.Service;

import ca.jrvs.apps.twitter.DAO.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import junit.framework.TestCase;

public class TwitterServiceTest extends TestCase {

    public void testValidateTweet() {
        float[] cord = new float[2];
        cord[0] = 0.0F;
        cord[1] = -1.0F;
        Coordinates coordinates = new Coordinates(cord);
        Tweet testTweet = new Tweet("qwesdf",cord);
        TwitterService tws = new TwitterService();
        assertEquals(true,tws.validateTweet(testTweet));
    }
}