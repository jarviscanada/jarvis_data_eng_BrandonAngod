package ca.jrvs.apps.twitter.DAO;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Before;

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

    public void testCreate() throws JsonProcessingException {
        String hashTag = "#HelloWorld";
        String text = "@Keldav1 Hello World" + hashTag;
        float[] cord = new float[2];
        cord[0] = 0.0F;
        cord[1] = -1.0F;
        Coordinates cordinate = new Coordinates(cord);
        Tweet tweet = new Tweet(text,cord);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tweet);
        System.out.println(json);

        Tweet postTweet = dao.create(tweet);

        assertEquals(text,postTweet.getText());

        assertNotNull(tweet.getCoordinates());
        assertEquals(cord,tweet.getCoordinates().getCoordinates());
    }
}