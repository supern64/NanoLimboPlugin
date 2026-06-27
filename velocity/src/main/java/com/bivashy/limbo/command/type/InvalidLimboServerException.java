package com.bivashy.limbo.command.type;

import revxrsal.commands.exception.InvalidValueException;

public class InvalidLimboServerException extends InvalidValueException {
    public InvalidLimboServerException(String message) {
        super(message);
    }
}
