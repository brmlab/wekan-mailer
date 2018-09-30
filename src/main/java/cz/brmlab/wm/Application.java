package cz.brmlab.wm;

import cz.brmlab.wm.utils.Exceptions.BrmException;
import cz.brmlab.wm.wekan.WekanConfiguration;
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
    public void run(String... args)  {
        try {
            WekanConfiguration wekanConfiguration = new WekanConfiguration();
        } catch (BrmException ex) {
            System.exit(ex.getExitCode().getCode());
        }
    }
}
