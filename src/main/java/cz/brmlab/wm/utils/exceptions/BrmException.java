package cz.brmlab.wm.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class BrmException extends Exception {
    @Getter
    @NonNull
    private final String message;
    @Getter
    @NonNull
    private final ExitCode exitCode;
}
