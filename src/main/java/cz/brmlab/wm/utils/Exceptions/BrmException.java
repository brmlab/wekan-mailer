package cz.brmlab.wm.utils.Exceptions;

import lombok.Data;

@Data
public class BrmException extends Exception {
    private final String message;
    private final ExitCode exitCode;
}
