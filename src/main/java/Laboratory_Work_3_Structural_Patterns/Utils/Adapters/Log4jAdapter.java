package Laboratory_Work_3_Structural_Patterns.Utils.Adapters;

import Laboratory_Work_3_Structural_Patterns.Interfaces.ILogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log4jAdapter implements ILogger {
    private final Logger logger;
    private static Log4jAdapter instance;

    private Log4jAdapter() {
        this.logger = LogManager.getLogger(Log4jAdapter.class);
    }

    // Singleton Logger
    public static Log4jAdapter getInstance() {
        if (instance == null) {
            instance = new Log4jAdapter();
            instance.infoLog("Log4j2 Logger Initialized using Singleton Pattern");
        }
        else {
            instance.infoLog("Log4j2 Logger already initialized - Returning existing instance");
        }
        return instance;
    }

    @Override
    public void infoLog(String message) {
        logger.info(getCallerClassAndMethod() + " :: " + message);
    }

    @Override
    public void warningLog(String message) {
        logger.warn(getCallerClassAndMethod() + " :: " + message);
    }

    @Override
    public void errorLog(String message) {
        logger.error(getCallerClassAndMethod() + " :: " + message);
    }

    // Helper method to extract the calling class and method
    private String getCallerClassAndMethod() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // Index 3: This should give us the caller method, which called the logging method.
        StackTraceElement caller = stackTrace[3];
        return caller.getClassName() + "::" + caller.getMethodName();
    }
}
