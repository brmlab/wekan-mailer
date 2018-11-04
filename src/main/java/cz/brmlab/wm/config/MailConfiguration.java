package cz.brmlab.wm.config;

import cz.brmlab.wm.utils.exceptions.BrmException;
import cz.brmlab.wm.utils.exceptions.ExitCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class MailConfiguration implements EnvConfig {

    //ENV variables for mail
    private static final String MAIL_PROTOCOL = "MAIL_PROTOCOL";
    private static final String MAIL_URL = "MAIL_URL";
    private static final String MAIL_PORT = "MAIL_PORT";
    private static final String MAIL_USER = "MAIL_USER";
    private static final String MAIL_PASS = "MAIL_PASS";
    private static final String MAIL_FOLDER = "MAIL_FOLDER";

    //List of mail ENV vars
    private static final List<String> properties = new ArrayList<>(Arrays.asList(MAIL_PROTOCOL, MAIL_URL, MAIL_PORT, MAIL_USER, MAIL_PASS, MAIL_FOLDER));

    @Getter
    private String mailProtocol;

    @Getter
    private String mailUrl;

    @Getter
    private String mailUser;

    @Getter
    private String mailPassword;

    @Getter
    private String mailFolder;

    @Getter
    private String mailPort;

    /**
     * Configuration for mail. Taken from the container ENV variables.
     *
     * @throws BrmException if some of the properties is missing in ENV variables.
     */
    public MailConfiguration() throws BrmException {
        log.trace("{}() - start.", this.getClass().getSimpleName());

        checkProps(properties);

        this.mailProtocol = System.getenv(MAIL_PROTOCOL);
        this.mailUrl = System.getenv(MAIL_URL);
        this.mailPort = System.getenv(MAIL_PORT);
        this.mailUser = System.getenv(MAIL_USER);
        this.mailPassword = System.getenv(MAIL_PASS);
        this.mailFolder = System.getenv(MAIL_FOLDER);

        checkProtocol();

        log.info("Mail config loaded successfully.");
    }

    private void checkProtocol() throws BrmException {
        if (!this.mailProtocol.equalsIgnoreCase("imap")){
            throw new BrmException(ExitCode.UNSUPPORTED_PROTOCOL.getReason(), ExitCode.UNSUPPORTED_PROTOCOL);
        }
    }

}
