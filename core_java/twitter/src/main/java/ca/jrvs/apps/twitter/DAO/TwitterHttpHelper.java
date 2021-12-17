package ca.jrvs.apps.twitter.DAO;

import ca.jrvs.apps.twitter.interfaces.HttpHelper;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.URI;

public class TwitterHttpHelper implements HttpHelper {
    private OAuthConsumer consumer;
    private HttpClient httpClient;

    public TwitterHttpHelper(String consumerKey,String consumerSecret, String accessToken, String tokenSecret){
        System.out.println(consumerKey + " ; " + consumerSecret + " ; " + accessToken + " ; " + tokenSecret + " ; " );
        this.consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        this.consumer.setTokenWithSecret(accessToken, tokenSecret);
        this.httpClient = new DefaultHttpClient();
    }
    @Override
    public HttpResponse httpPost(URI uri) {
        try{
            return executeHttpRequest(HttpMethod.POST, uri,null);
        } catch(OAuthException | IOException | IllegalAccessException e){
            throw new RuntimeException("OAuth/IOException: On Post",e);
        }
    }

    @Override
    public HttpResponse httpGet(URI uri) {
        try{
            return executeHttpRequest(HttpMethod.GET, uri,null);
        } catch(OAuthException | IOException | IllegalAccessException e){
            throw new RuntimeException("OAuth/IOException: ",e);
        }
    }
    private HttpResponse executeHttpRequest(HttpMethod method, URI uri, StringEntity stringEntity) throws IOException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, IllegalAccessException {
        if(method == HttpMethod.GET){
            HttpGet request = new HttpGet(uri);
            this.consumer.sign(request);
            return this.httpClient.execute(request);
        }

        else if(method == HttpMethod.POST){
            HttpPost request = new HttpPost(uri);
            if(stringEntity != null){
                request.setEntity(stringEntity);
            }
            this.consumer.sign(request);
            return this.httpClient.execute(request);
        }

        else{
            throw new IllegalAccessException("UnknownMethod "+ method.name());
        }

    }

    public static void main(String[] args) {

    }
}

