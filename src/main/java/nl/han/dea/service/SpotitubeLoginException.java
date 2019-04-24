package nl.han.dea.service;

public class SpotitubeLoginException extends RuntimeException {
    private ExceptionCause exceptionCause;

    public SpotitubeLoginException(String message, ExceptionCause exceptionCause) {
        super(message);
        this.exceptionCause = exceptionCause;
    }

    public ExceptionCause getExceptionCause() {
        return exceptionCause;
    }
}