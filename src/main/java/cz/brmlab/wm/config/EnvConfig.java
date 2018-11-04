package cz.brmlab.wm.config;

import cz.brmlab.wm.utils.exceptions.BrmException;
import cz.brmlab.wm.utils.exceptions.ExitCode;

import java.util.List;


interface EnvConfig {

    /**
     * Checks if environment variables does contains keys from provided list of keys.
     *
     * @param props List of required environment properties.
     * @throws BrmException if required property key is missing in environment variables.
     */
    default void checkProps(List<String> props) throws BrmException {
        for (String prop : props) {
            if (System.getenv(prop) == null) {
                String message = ExitCode.CONFIGURATION_MISSING.getReason() + prop;
                throw new BrmException(message, ExitCode.CONFIGURATION_MISSING);
            }
        }
    }
}
