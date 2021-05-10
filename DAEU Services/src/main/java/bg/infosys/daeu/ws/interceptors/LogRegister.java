package bg.infosys.daeu.ws.interceptors;

import java.lang.annotation.*;

/**
 * Annotation to control logging based on failure or success of method execution. Should be targeted at database logs.
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LogRegister {

    /**
     * името с което да бъде записан логъра в базата данни
     */
    String name();
}
