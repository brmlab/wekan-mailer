package cz.brmlab.wm.config;

import cz.brmlab.wm.utils.exceptions.BrmException;
import cz.brmlab.wm.utils.exceptions.ExitCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

import static org.junit.Assert.*;

public class MailConfigurationTest {

    @Rule
    public final EnvironmentVariables environmentVariables
            = new EnvironmentVariables();

    private static final String MAIL_PROTOCOL = "MAIL_PROTOCOL";
    private static final String MAIL_PROTOCOL_VALUE = "imap";

    private static final String MAIL_URL = "MAIL_URL";
    private static final String MAIL_URL_VALUE = "mail.test.url";

    private static final String MAIL_PORT = "MAIL_PORT";
    private static final String MAIL_PORT_VALUE = "993";

    private static final String MAIL_USER = "MAIL_USER";
    private static final String MAIL_USER_VALUE = "someuser";

    private static final String MAIL_PASS = "MAIL_PASS";
    private static final String MAIL_PASS_VALUE = "somepass";

    private static final String MAIL_FOLDER = "MAIL_FOLDER";
    private static final String MAIL_FOLDER_VALUE = "inbox";


    @Before
    public void setEnvironmentVariables() {
        environmentVariables.set(MAIL_PROTOCOL, MAIL_PROTOCOL_VALUE);
        environmentVariables.set(MAIL_URL, MAIL_URL_VALUE);
        environmentVariables.set(MAIL_PORT, MAIL_PORT_VALUE);
        environmentVariables.set(MAIL_USER, MAIL_USER_VALUE);
        environmentVariables.set(MAIL_PASS, MAIL_PASS_VALUE);
        environmentVariables.set(MAIL_FOLDER, MAIL_FOLDER_VALUE);
    }

    @After
    public void cleanEnvVars() {
        environmentVariables.clear(MAIL_PROTOCOL, MAIL_URL, MAIL_USER, MAIL_PASS, MAIL_FOLDER);
    }

    @Test
    public void configurationOk() {

        MailConfiguration configuration = null;
        try {
            configuration = new MailConfiguration();
        } catch (BrmException e) {
            fail("OK configuration should not throw an error!");
        }
        assertEquals(MAIL_PROTOCOL_VALUE, configuration.getMailProtocol());
        assertEquals(MAIL_URL_VALUE, configuration.getMailUrl());
        assertEquals(MAIL_USER_VALUE, configuration.getMailUser());
        assertEquals(MAIL_PASS_VALUE, configuration.getMailPassword());
        assertEquals(MAIL_FOLDER_VALUE, configuration.getMailFolder());
        assertEquals(MAIL_PORT_VALUE, configuration.getMailPort());
    }

    @Test
    public void allConfigurationMissing() {
        cleanEnvVars();
        MailConfiguration configuration = null;
        try {
            configuration = new MailConfiguration();
            fail("Missing whole configuration should throw an error!");
        } catch (BrmException ignored) {

        }

        assertNull(null, configuration);
    }

    @Test
    public void missingOneProp() {

        environmentVariables.clear(MAIL_PASS);

        MailConfiguration configuration = null;
        try {
            configuration = new MailConfiguration();
            fail("Missing one property in configuration should throw an error!");
        } catch (BrmException ex) {
            assertEquals(ExitCode.CONFIGURATION_MISSING.getReason() + MAIL_PASS, ex.getMessage());
        }

        assertNull(null, configuration);
    }

    @Test
    public void unsupportedProtocol() {
        environmentVariables.set(MAIL_PROTOCOL, "POP3");

        MailConfiguration configuration = null;
        try {
            configuration = new MailConfiguration();
            fail("Unsupported protocol should throw an error!");
        } catch (BrmException ex) {
            assertEquals(ExitCode.UNSUPPORTED_PROTOCOL.getReason(), ex.getMessage());
        }

        assertNull(null, configuration);
    }

}

