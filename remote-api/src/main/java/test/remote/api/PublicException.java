package test.remote.api;

import java.util.Random;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
public class PublicException extends Exception {

    Reason reason;

    public PublicException() {
        this(Reason.UnknownException);
    }

    public PublicException(Reason reason) {
        super(reason.toString());
        this.reason = reason;
    }

    public enum Reason {
        InvalidArguments,
        MyStrangeException,
        UnknownException
    }

}
