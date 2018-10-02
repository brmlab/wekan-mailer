package cz.brmlab.wm;

import cz.brmlab.wm.utils.Exceptions.BrmException;
import cz.brmlab.wm.wekan.WekanConfiguration;
import cz.brmlab.wm.wekan.pojo.card.CardRequest;
import cz.brmlab.wm.wekan.rest.CardPost;
import cz.brmlab.wm.wekan.rest.LoginPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) {
        try {
            WekanConfiguration wekanConfiguration = new WekanConfiguration();

            LoginPost loginPost = new LoginPost(wekanConfiguration);
            loginPost.login();

            CardPost cardPost = new CardPost(loginPost.getToken(), wekanConfiguration);
            cardPost.postCard("Test from spring", "Test card from awesome spring app.\nAnd next line");

        } catch (BrmException ex) {
            System.exit(ex.getExitCode().getCode());
        }
    }
}
