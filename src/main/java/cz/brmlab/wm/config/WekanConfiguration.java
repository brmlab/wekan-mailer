package cz.brmlab.wm.config;

import cz.brmlab.wm.utils.Exceptions.BrmException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class WekanConfiguration implements EnvConfig {

    //ENV variables for wekan
    private static final String WEKAN_URL = "WEKAN_URL";
    private static final String WEKAN_USER = "WEKAN_USER";
    private static final String WEKAN_PASS = "WEKAN_PASS";
    private static final String WEKAN_TARGET_BOARD = "WEKAN_TARGET_BOARD";
    private static final String WEKAN_TARGET_LIST = "WEKAN_TARGET_LIST";

    //List of wekan ENV vars
    private static final List<String> properties = new ArrayList<>(Arrays.asList(WEKAN_URL, WEKAN_USER, WEKAN_PASS, WEKAN_TARGET_BOARD, WEKAN_TARGET_LIST));

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

    /**
     * Configuration for wekan. Taken from the container ENV variables.
     *
     * @throws BrmException if some of the properties is missing in ENV variables.
     */
    public WekanConfiguration() throws BrmException {
        log.trace("{}() - start.", this.getClass().getSimpleName());

        checkProps(properties);

        this.wekanUrl = System.getenv(WEKAN_URL);
        this.wekanUser = System.getenv(WEKAN_USER);
        this.wekanPassword = System.getenv(WEKAN_PASS);
        this.wekanBoard = System.getenv(WEKAN_TARGET_BOARD);
        this.wekanList = System.getenv(WEKAN_TARGET_LIST);

        log.info("Wekan config loaded successfully.");
    }

}
