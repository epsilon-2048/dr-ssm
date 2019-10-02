package epsilon.ssm.exceptionHandler.myException;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class UnauthorizedException extends RuntimeException {
    //如果继承Excepiton则抛出UndeclaredThrowableException，不是想要的结果
    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

    public UnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
