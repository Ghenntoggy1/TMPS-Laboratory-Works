package Laboratory_Work_4_Behavioral_Patterns.Utils.Adapters;

import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ILogger;
import org.apache.logging.log4j.Level;
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

    // Helper method to get calling class and method
    private String getCallingClassAndMethod() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // Index 0: getStackTrace(), Index 1: getCallingClassAndMethod(), Index 2: this method (infoLog, etc.)
        // Index 3: Caller method
        StackTraceElement caller = stackTrace[3];
        return caller.getClassName() + "::" + caller.getMethodName();
    }

    @Override
    public void infoLog(String message) {
        logger.log(Level.INFO, getCallingClassAndMethod() + " :: " + message);
    }

    @Override
    public void warningLog(String message) {
        logger.log(Level.WARN, getCallingClassAndMethod() + " :: " + message);
    }

    @Override
    public void errorLog(String message) {
        logger.log(Level.ERROR, getCallingClassAndMethod() + " :: " + message);
    }
}
