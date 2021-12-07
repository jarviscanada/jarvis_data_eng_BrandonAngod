package ca.jrvs.apps.twitter.DAO;

import ca.jrvs.apps.twitter.interfaces.CrdDao;
import ca.jrvs.apps.twitter.interfaces.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.HttpResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

public class TwitterDAO implements CrdDao<Tweet, String> {
    //URI Constants
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "//1.1/statuses/update.json";
    private static final String SHOW_PATH = "//1.1/statuses/show.json";
    private static final String DELETE_PATH = "//1.1/statuses/destroy";
    //URI Symbols
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    //Response code
    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;

    public TwitterDAO(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public Tweet create(Tweet tweet) {
        HttpResponse response = httpHelper.httpPost(tweet.getUri());
        return parseTweet(response,HTTP_OK);
    }

    private Tweet parseTweet(HttpResponse response, Integer StatusCode){
        Tweet tweet = null;

        int status = response.getStatusLine().getStatusCode();
        if(status != StatusCode){
            try{
                System.out.println(EntityUtils.toString(response.getEntity()));
            }catch (IOException e){
                System.out.println("No entity");
            }
            throw new RuntimeException("HTTP Status:"+ status);
        }
        if(response.getEntity()==null){
            throw new RuntimeException("Empty response body");
        }

        String jsonStr;
        try{
            jsonStr = EntityUtils.toString(response.getEntity());
        }catch(IOException e ){
            throw new RuntimeException("JSON str to Obj bad conversion",e );
        }
        return tweet;
    }
    @Override
    public Tweet findById(String s) throws URISyntaxException {
        URI uri = new URI(API_BASE_URI + SHOW_PATH + "/recent?conversation_id:" + s);
        HttpResponse response = httpHelper.httpGet(uri);
        return parseTweet(response,HTTP_OK);
    }

    @Override
    public Tweet deleteById(String s) throws URISyntaxException {
        URI uri = new URI(API_BASE_URI + DELETE_PATH + "/recent?conversation_id:" + s);
        HttpResponse response = httpHelper.httpGet(uri);
        return parseTweet(response,HTTP_OK);
    }
}
