package org.crypto.training.repository.exception;

public class System_UserNotFoundException extends RuntimeException {

    public System_UserNotFoundException() {super();}

    public System_UserNotFoundException(String message) {super(message);}

    public System_UserNotFoundException(Throwable cause) {super(cause);}

    public System_UserNotFoundException(String errorMsg, Throwable cause) {super(errorMsg, cause);}
}
