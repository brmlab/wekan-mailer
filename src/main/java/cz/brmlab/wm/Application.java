package cz.brmlab.wm;

import cz.brmlab.wm.utils.Exceptions.BrmException;
import cz.brmlab.wm.wekan.WekanConfiguration;
import cz.brmlab.wm.wekan.pojo.card.PostCardResponse;
import cz.brmlab.wm.wekan.rest.CardPost;
import cz.brmlab.wm.wekan.rest.LoginPost;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.trace("Starting wekan-mailer app...");
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) {
        try {
            WekanConfiguration wekanConfiguration = new WekanConfiguration();

            LoginPost loginPost = new LoginPost(wekanConfiguration);
            loginPost.login();

            CardPost cardPost = new CardPost(loginPost.getToken(), wekanConfiguration);
            PostCardResponse postCardResponse = cardPost.postCard("Test from spring", "Test card from awesome spring app.\nAnd next line");

        } catch (BrmException ex) {
            log.error("Error {} encountered, shutting down!", ex.getExitCode());
            System.exit(ex.getExitCode().getCode());
        }
    }
}
