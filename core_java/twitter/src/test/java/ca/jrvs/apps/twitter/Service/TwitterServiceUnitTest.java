package ca.jrvs.apps.twitter.Service;

import ca.jrvs.apps.twitter.JsonParser;
import ca.jrvs.apps.twitter.interfaces.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
    @Mock
    CrdDao dao;

    @InjectMocks
    TwitterService service;

    private String tweetJsonStr;

    @Before
    public void setup(){
        String hashTag = "#abc";
        String text = "@someone sometext "+hashTag + " " + System.currentTimeMillis();
        float[] cords = new float[]{1.0f,-1.0f};
        try{
            dao.create(new Tweet(text,cords));
            fail();
        }catch (RuntimeException e){
            assertTrue(true);
        }

        tweetJsonStr = "{\n"
                +" \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                +" \"id\":1097607853932564480, \n"
                +" \"id_str\":\"1097607853932564480\",\n"
                +" \"text\":\"test with loc223\",\n"
                +" \"entities\":{\n"
                +"     \"hashtags\":[],"
                +"     \"user_mention\":[]"
                +"     },\n"
                +" \"coordinates\":null,"
                +" \"retweet_count\":0,\n"
                +" \"favorite_count\":0,\n"
                +" \"favorite\":false,\n"
                +" \"retweeted\":false\n"
                +"}";
    }
    @Test
    public void postTweet() throws URISyntaxException, IOException {
        when(dao.create(any())).thenReturn(new Tweet());
        TwitterService spyService = Mockito.spy(service);
        service.postTweet(new Tweet("Test",new float[]{50.0f,0.0f}));
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
        doReturn(expectedTweet).when(spyService).postTweet(any());
        Tweet tweet = spyService.postTweet(new Tweet("Test",new float[]{50.0f,0.0f}));
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void showTweet() throws URISyntaxException, IOException {
        when(dao.findById(any())).thenReturn(new Tweet());
        TwitterService spyService = Mockito.spy(service);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
        doReturn(expectedTweet).when(spyService).showTweet(anyString(),any(String[].class));
        String[] fields = new String[]{"2352","2134"};
        service.showTweet("1468598138508296199",fields);
        Tweet tweet = spyService.showTweet("1468598138508296199",fields);
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void deleteTweet() throws URISyntaxException, IOException {
        when(dao.findById(any())).thenReturn(new Tweet());
        TwitterService spyService = Mockito.spy(service);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
        doReturn(expectedTweet).when(spyService).deleteTweets(any(String[].class));
        service.deleteTweets(new String[]{"1468598138508296199"});
        List<Tweet> tweet = spyService.deleteTweets(new String[]{"1468598138508296199"});
        assertNotNull(tweet);
    }
}
