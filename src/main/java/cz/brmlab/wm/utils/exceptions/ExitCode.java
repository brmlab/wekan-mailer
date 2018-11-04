package cz.brmlab.wm.utils.exceptions;

import lombok.Getter;

public enum ExitCode {

    CONFIGURATION_MISSING(10, "Missing configuration property: "),
    POST_ERROR(20, "Failed POST request, RC: ");

    @Getter
    private String reason;
    @Getter
    private int code;

    ExitCode(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }


}
