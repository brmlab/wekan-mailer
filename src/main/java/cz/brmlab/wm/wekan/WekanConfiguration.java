package cz.brmlab.wm.wekan;

import cz.brmlab.wm.utils.Exceptions.BrmException;
import cz.brmlab.wm.utils.Exceptions.ExitCode;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WekanConfiguration {

    /**
     * Class logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(WekanConfiguration.class);

    //ENV variables for wekan
    private static final String WEKAN_URL = "WEKAN_URL";
    private static final String WEKAN_USER = "WEKAN_USER";
    private static final String WEKAN_PASSWORD = "WEKAN_PASSWORD";
    private static final String WEKAN_TARGET_BOARD = "WEKAN_TARGET_BOARD";
    private static final String WEKAN_TARGET_LIST = "WEKAN_TARGET_LIST";

    //List of wekan ENV vars
    private static final List<String> properties = new ArrayList<>(Arrays.asList(WEKAN_URL, WEKAN_USER, WEKAN_PASSWORD, WEKAN_TARGET_BOARD, WEKAN_TARGET_LIST));

    /**
     * Configuration for wekan. Taken from the container ENV variables.
     *
     * @throws BrmException if some of the properties is missing in ENV variables.
     */
    public WekanConfiguration() throws BrmException {
        for (String prop : properties) {
            checkProp(prop);
        }
        this.wekanUrl = System.getenv(WEKAN_URL);
        this.wekanUser = System.getenv(WEKAN_USER);
        this.wekanPassword = System.getenv(WEKAN_PASSWORD);
        this.wekanBoard = System.getenv(WEKAN_TARGET_BOARD);
        this.wekanList = System.getenv(WEKAN_TARGET_LIST);
    }

    @Getter
    private String wekanUrl;

    @Getter
    private String wekanUser;

    @Getter
    private String wekanPassword;

    @Getter
    private String wekanBoard;

    @Getter
    private String wekanList;

    private void checkProp(String prop) throws BrmException {
        if (System.getenv(prop) == null) {
            String message = ExitCode.CONFIGURATION_MISSING.getReason() + prop;
            LOG.error(message, ExitCode.CONFIGURATION_MISSING);
            throw new BrmException(message, ExitCode.CONFIGURATION_MISSING);
        }
    }
}
