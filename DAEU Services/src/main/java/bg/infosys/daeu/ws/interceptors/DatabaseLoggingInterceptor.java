package bg.infosys.daeu.ws.interceptors;

import bg.infosys.daeu.ws.services.LoggingService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Aspect
@Component
public class DatabaseLoggingInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseLoggingInterceptor.class);

    @Autowired
    private LoggingService loggingService;

    @Around(value = "@annotation(logRegister)")
    public Object callAt(ProceedingJoinPoint joinPoint, LogRegister logRegister) {
        logger.info("Log Interceptor has been called");

        String name = logRegister.name();
        Object result = null;

        try {
            result = joinPoint.proceed();

            if (Objects.isNull(result)) {
                logger.warn("Said Method {}, is void, will not log its result", name);
            } else {
                logger.info("Returned Result for {} -->> {}", name, result);
            }

        } catch (Throwable e) {
            logger.info("{} Has thrown an error", name);
        }
        loggingService.logTime(name, LocalDate.now(), Objects.nonNull(result));
        // check answer and log
        return result;
    }

}
