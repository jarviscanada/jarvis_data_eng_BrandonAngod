package ca.jrvs.apps.twitter.DAO;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class TwitterDAOTest extends TestCase {
    private TwitterDAO dao;
    @Before
    public void setup(){
        String consumerKey = System.getenv("consumerKey");
        String consumerSecretKey = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        System.out.println(consumerKey+"|"+consumerSecretKey+"|"+accessToken+"|"+tokenSecret);
        TwitterHttpHelper httpHelper = new TwitterHttpHelper(consumerKey,consumerSecretKey,accessToken, tokenSecret);
        this.dao = new TwitterDAO(httpHelper);
    }

    @Test
    public void testCreate() throws JsonProcessingException {
        String hashTag = "#HelloWorld";
        String text = "@Keldav1 Hello World "+hashTag;
        float[] cord = new float[2];
        cord[0] = -1.0F;
        cord[1] = 1.0F;
        Coordinates cordinate = new Coordinates(cord);
        Tweet tweet = new Tweet(text,cordinate);
        tweet.setId("234");
        tweet.setId_str("234");
        URI uri = null;
        try {
            uri = new URI("https://api.twitter.com/1.1/tweets");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        tweet.setUri(uri);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tweet);
        System.out.println(json);
        Tweet postTweet = dao.create(tweet);

        assertEquals(text,postTweet.getText());

        assertNotNull(tweet.getCoordinates());
        assertEquals(cord,tweet.getCoordinates().getCoordinates());
    }

    @Test
    public void testFindByID() throws URISyntaxException {
        String s = "1468598138508296199";
        Tweet tweet = new Tweet();
        tweet.setText("1");
        tweet.setId("1468598138508296199");
        tweet.setId_str("1468598138508296199");
        Tweet postedTweet = dao.findById("1468598138508296199");
        assertEquals(tweet,dao.findById(s));
    }
    @Test
    public void testDeleteById() throws URISyntaxException {
        String s = "1468598138508296199";
        Tweet tweet = new Tweet();
        tweet.setText("1");
        tweet.setId("1468598138508296199");
        tweet.setId_str("1468598138508296199");
        Tweet postedTweet = dao.deleteById("1468598138508296199");
        assertNull(dao.findById("1468598138508296199"));
    }
}