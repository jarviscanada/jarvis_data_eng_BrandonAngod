package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.DAO.TwitterDAO;
import ca.jrvs.apps.twitter.DAO.TwitterHttpHelper;
import ca.jrvs.apps.twitter.Service.TwitterService;
import ca.jrvs.apps.twitter.interfaces.Controller;
import ca.jrvs.apps.twitter.interfaces.CrdDao;
import ca.jrvs.apps.twitter.interfaces.HttpHelper;
import ca.jrvs.apps.twitter.interfaces.Service;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class TwitterControllerTest {
    private HttpHelper httpHelper;
    private CrdDao dao;
    private Service service;
    private TwitterController controller;
    @Before
    public void setup(){
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

         httpHelper = new TwitterHttpHelper(consumerKey,consumerSecret,accessToken,tokenSecret);
         dao = new TwitterDAO(httpHelper);
         service = new TwitterService(dao);
         controller = new TwitterController(service);
    }

    @Test
    public void postTweet() {
        String body= "123";
        float[] cords=new float[]{1,2};
        Tweet testTweet = new Tweet(body,cords);
        assertEquals(service.postTweet(testTweet),controller.postTweet(new String[]{"123", "1,2"}));
    }

    @Test
    public void showTweet() throws URISyntaxException {
        assertEquals("1",controller.showTweet(new String[]{"1468598138508296199"}));
    }

    @Test
    public void deleteTweet() throws URISyntaxException {
        assertNull(controller.deleteTweet(new String[]{"1468598138508296199"}).toString());
    }
}