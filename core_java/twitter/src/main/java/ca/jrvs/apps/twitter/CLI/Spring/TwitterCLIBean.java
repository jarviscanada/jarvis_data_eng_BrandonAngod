package ca.jrvs.apps.twitter.CLI.Spring;

import ca.jrvs.apps.twitter.CLI.TwitterCLIApp;
import ca.jrvs.apps.twitter.DAO.TwitterDAO;
import ca.jrvs.apps.twitter.DAO.TwitterHttpHelper;
import ca.jrvs.apps.twitter.Service.TwitterService;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.interfaces.Controller;
import ca.jrvs.apps.twitter.interfaces.CrdDao;
import ca.jrvs.apps.twitter.interfaces.HttpHelper;
import ca.jrvs.apps.twitter.interfaces.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;
@Configuration
public class TwitterCLIBean {

    //private Object HttpHelper;
    public static void main(String[] args) throws URISyntaxException {
        ApplicationContext context = new AnnotationConfigApplicationContext(TwitterCLIBean.class);
        TwitterCLIApp app = context.getBean(TwitterCLIApp.class);
        app.run(args);
    }

    @Bean
    public TwitterCLIApp TwitterCLIApp(Controller controller){
        return new TwitterCLIApp(controller);
    }

    @Bean
    public Controller controller(Service service){
        return new TwitterController(service);
    }

    @Bean
    public Service service(CrdDao dao){
        return new TwitterService(dao);
    }

    @Bean
    public CrdDao crdDao(HttpHelper httpHelper){
        return new TwitterDAO(httpHelper);
    }

    @Bean
    HttpHelper helper(){
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        return new TwitterHttpHelper(consumerKey,consumerSecret,accessToken, tokenSecret);
    }
}
