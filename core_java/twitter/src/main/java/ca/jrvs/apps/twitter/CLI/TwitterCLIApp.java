package ca.jrvs.apps.twitter.CLI;

import ca.jrvs.apps.twitter.DAO.TwitterDAO;
import ca.jrvs.apps.twitter.DAO.TwitterHttpHelper;
import ca.jrvs.apps.twitter.Service.TwitterService;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.interfaces.Controller;
import ca.jrvs.apps.twitter.interfaces.CrdDao;
import ca.jrvs.apps.twitter.interfaces.HttpHelper;
import ca.jrvs.apps.twitter.interfaces.Service;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URISyntaxException;

public class TwitterCLIApp {
    private Controller controller;
    private String USAGE="TwitterApp post|show|delete [options]";
    public TwitterCLIApp(Controller controller){this.controller = controller;}

    public static void main(String[] args) throws URISyntaxException {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey,consumerSecret,accessToken,tokenSecret);
        CrdDao dao = new TwitterDAO(httpHelper);
        Service service = new TwitterService(dao);
        Controller controller = new TwitterController(service);
        TwitterCLIApp app = new TwitterCLIApp(controller);

        app.run(args);
    }
    public void run(String[] args) throws URISyntaxException {
        if(args.length == 0){
            throw new IllegalArgumentException(USAGE);
        }
        switch (args[0].toLowerCase()){
            case "post":
                printTweet(controller.postTweet(args));
                break;
            case "show":
                printTweet(controller.showTweet(args));
                break;
            case "delete":
                controller.deleteTweet(args).forEach(this::printTweet);
                break;
            default:
                throw new IllegalArgumentException(USAGE);
        }
    }
    private void printTweet(Tweet tweet){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tweet);
            System.out.println(json);
        }catch(JsonProcessingException e){
            throw new RuntimeException("Unable to conver to string",e);
        }
    }
}
