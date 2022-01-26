package com.project.root.gameutilities;

import com.project.root.gameutilities.error_reason.Reason;

/**
 * This is an Exception that should be thrown when an invalid throw has been thrown. It encapsulates data of
 * of the invalid throw, such as the error message, the amount of missed
 *
 * @author Christopher Hübner
 * @version 1.0.0 16.01.2021
 */
public class InvalidThrowException extends Exception {

    /**
     * The {@link Reason} for this exception.
     */
    private final Reason reason;

    /**
     * The constructor for this exception.
     *
     * @param reason  The {@link Reason} for this exception.
     * @param message The error message that should be displayed on the command line when executed.
     */
    public InvalidThrowException(Reason reason, String message) {
        super(message);
        this.reason = reason;
    }

    /**
     * Returns the {@link Reason} for the exception.
     *
     * @return Returns the {@link #reason} for this exception.
     */
    public Reason reason() {
        return reason;
    }
}