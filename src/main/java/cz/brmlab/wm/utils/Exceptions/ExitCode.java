package cz.brmlab.wm.utils.Exceptions;

import lombok.Getter;

public enum ExitCode {

    CONFIGURATION_MISSING(10, "Missing configuration property: ");

    @Getter
    private String reason;
    @Getter
    private int code;

    ExitCode(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }


}
