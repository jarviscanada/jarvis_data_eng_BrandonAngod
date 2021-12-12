package ca.jrvs.apps.twitter.DAO;

import ca.jrvs.apps.twitter.JsonParser;
import ca.jrvs.apps.twitter.interfaces.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class TwitterDAOUnitTest {
    private String tweetJsonStr,text;
    private float[] cords;
    @Mock
    HttpHelper mockHelper;

    @InjectMocks
    TwitterDAO dao;

    @Before
    public void setup(){
        String hashTag = "#abc";
        text = "@someone sometext "+hashTag + " " + System.currentTimeMillis();
        cords = new float[]{1.0f,-1.0f};
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
    public void showTweet() throws Exception{
        when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDAO spyDAO = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
        doReturn(expectedTweet).when(spyDAO).parseTweet(any(),anyInt());
        Tweet tweet = spyDAO.create(new Tweet(text,cords));
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void findById() throws Exception{
        when(mockHelper.httpGet(isNotNull())).thenReturn(null);
        TwitterDAO spyDAO = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
        doReturn(expectedTweet).when(spyDAO).parseTweet(any(),anyInt());
        Tweet tweet = spyDAO.findById("1097607853932564480");
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void deleteTweet() throws Exception{
        when(mockHelper.httpGet(isNotNull())).thenReturn(null);
        TwitterDAO spyDAO = Mockito.spy(dao);
        Tweet tweet = spyDAO.deleteById("1097607853932564480");
        assertNull(tweet);
        assertNull(tweet.getText());
    }
}
