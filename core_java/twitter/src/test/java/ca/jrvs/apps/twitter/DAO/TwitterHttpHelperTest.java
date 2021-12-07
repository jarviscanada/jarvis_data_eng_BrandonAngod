package ca.jrvs.apps.twitter.DAO;

import junit.framework.TestCase;
import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class TwitterHttpHelperTest extends TestCase {
    private String consumerKey,consumerSecretKey,accessToken,tokenSecret;
    @Before
    public void setup(){

    }
    @Test
    public void testHttpPost() throws URISyntaxException {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecretKey = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        System.out.println(consumerKey + " ; " + consumerSecretKey + " ; " + accessToken + " ; " + tokenSecret + " ; " );

        TwitterHttpHelper httpHelp = new TwitterHttpHelper(consumerKey, consumerSecretKey, accessToken, tokenSecret);

        try {
            HttpResponse response = httpHelp.httpPost(new URI("https://api.twitter.com/2/tweets"));
        } catch (URISyntaxException e) {
            throw new URISyntaxException("Error upon testing: ",e.toString());
        }
    }

    public void testHttpGet() throws URISyntaxException {
        TwitterHttpHelper httpHelp = new TwitterHttpHelper(consumerKey, consumerSecretKey, accessToken, tokenSecret);

        try {
            HttpResponse response = httpHelp.httpGet(new URI("https://api.twitter.com/2/tweets/:id?tweet.fields=created_at,attachments&expansions=author_id"));
        } catch (URISyntaxException e) {
            throw new URISyntaxException("Error upon testing: ",e.toString());
        }
    }
}