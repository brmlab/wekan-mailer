package cz.brmlab.wm.wekan;

import cz.brmlab.wm.utils.Exceptions.BrmException;
import cz.brmlab.wm.utils.Exceptions.ExitCode;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

import static org.junit.Assert.*;

public class WekanConfigurationTest {

    @Rule
    public final EnvironmentVariables environmentVariables
            = new EnvironmentVariables();

    private static final String WEKAN_URL = "WEKAN_URL";
    private static final String WEKAN_URL_VALUE = "wekan.test.url";

    private static final String WEKAN_USER = "WEKAN_USER";
    private static final String WEKAN_USER_VALUE = "someuser";

    private static final String WEKAN_PASSWORD = "WEKAN_PASSWORD";
    private static final String WEKAN_PASSWORD_VALUE = "somepass";

    private static final String WEKAN_TARGET_BOARD = "WEKAN_TARGET_BOARD";
    private static final String WEKAN_TARGET_BOARD_VALUE = "someboardif";

    private static final String WEKAN_TARGET_LIST = "WEKAN_TARGET_LIST";
    private static final String WEKAN_TARGET_LIST_VALUE = "somelistid";


    @After
    public void cleanEnvVars() {
        environmentVariables.clear(WEKAN_URL, WEKAN_USER, WEKAN_PASSWORD, WEKAN_TARGET_BOARD, WEKAN_TARGET_LIST);
    }

    @Test
    public void configurationOk() {
        environmentVariables.set(WEKAN_URL, WEKAN_URL_VALUE);
        environmentVariables.set(WEKAN_USER, WEKAN_USER_VALUE);
        environmentVariables.set(WEKAN_PASSWORD, WEKAN_PASSWORD_VALUE);
        environmentVariables.set(WEKAN_TARGET_BOARD, WEKAN_TARGET_BOARD_VALUE);
        environmentVariables.set(WEKAN_TARGET_LIST, WEKAN_TARGET_LIST_VALUE);


        WekanConfiguration configuration = null;
        try {
            configuration = new WekanConfiguration();
        } catch (BrmException e) {
            fail("OK configuration should not throw an error!");
        }
        assertEquals(WEKAN_URL_VALUE, configuration.getWekanUrl());
        assertEquals(WEKAN_USER_VALUE, configuration.getWekanUser());
        assertEquals(WEKAN_PASSWORD_VALUE, configuration.getWekanPassword());
        assertEquals(WEKAN_TARGET_BOARD_VALUE, configuration.getWekanBoard());
        assertEquals(WEKAN_TARGET_LIST_VALUE, configuration.getWekanList());
    }

    @Test
    public void allConfigurationMissing() {
        WekanConfiguration configuration = null;
        try {
            configuration = new WekanConfiguration();
            fail("Missing whole configuration should not throw an error!");
        } catch (BrmException ignored) {

        }
        assertNull(null, configuration);
    }

    @Test
    public void missingOneProp() {

        environmentVariables.set(WEKAN_URL, WEKAN_URL_VALUE);
        environmentVariables.set(WEKAN_USER, WEKAN_USER_VALUE);
        //environmentVariables.set(WEKAN_PASSWORD, WEKAN_PASSWORD_VALUE);
        environmentVariables.set(WEKAN_TARGET_BOARD, WEKAN_TARGET_BOARD_VALUE);
        environmentVariables.set(WEKAN_TARGET_LIST, WEKAN_TARGET_LIST_VALUE);

        WekanConfiguration configuration = null;
        try {
            configuration = new WekanConfiguration();
            fail("Missing one property in configuration should not throw an error!");
        } catch (BrmException ex) {
            assertEquals(ExitCode.CONFIGURATION_MISSING.getReason() + WEKAN_PASSWORD, ex.getMessage());
        }
        assertNull(null, configuration);
    }
}

