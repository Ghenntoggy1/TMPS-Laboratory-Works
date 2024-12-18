package Laboratory_Work_3_Structural_Patterns.Utils.Adapters;

import Laboratory_Work_3_Structural_Patterns.Interfaces.ILogger;
import Laboratory_Work_3_Structural_Patterns.Utils.Logging.LogFormatter;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.io.IOException;

public class LoggingLoggerAdapter implements ILogger {
    private static LoggingLoggerAdapter instance;
    private final Logger logger;

    private LoggingLoggerAdapter() {
        this.logger = Logger.getLogger(LoggingLoggerAdapter.class.getName());
        this.logger.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new LogFormatter());
        consoleHandler.setLevel(Level.ALL);

        try {
            FileHandler fileHandler = new FileHandler("src/main/java/Laboratory_Work_3_Structural_Patterns/Utils/Logging/Logs/logs.log", true);
            fileHandler.setFormatter(new LogFormatter());
            fileHandler.setLevel(Level.ALL);
            this.logger.addHandler(fileHandler);
        } catch (IOException e) {
            System.out.println("Failed to Initialize File Handler for Logging: " + e.getMessage());
            this.errorLog("Failed to Initialize File Handler for Logging: " + e.getMessage());
        }

        this.logger.addHandler(consoleHandler);
        this.logger.setUseParentHandlers(false);
    }

    // Singleton Logger
    public static LoggingLoggerAdapter getInstance() {
        if (instance == null) {
            instance = new LoggingLoggerAdapter();
            instance.infoLog("Java Logging Logger Initialized using Singleton Pattern");
        }
        else {
            instance.infoLog("Java Logging Logger already initialized - Returning existing instance");
        }
        return instance;
    }

    // Helper method to get calling class and method
    private String getCallingClassAndMethod() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // Index 0: getStackTrace(), Index 1: getCallingClassAndMethod(), Index 2: this method (infoLog, etc.)
        // Index 3: Caller method
        StackTraceElement caller = stackTrace[4];
        return caller.getClassName() + "::" + caller.getMethodName();
    }

    @Override
    public void infoLog(String message) {
        logger.log(Level.INFO, getCallingClassAndMethod() + " :: " + message);
    }

    @Override
    public void warningLog(String message) {
        logger.log(Level.WARNING, getCallingClassAndMethod() + " :: " + message);
    }

    @Override
    public void errorLog(String message) {
        logger.log(Level.SEVERE, getCallingClassAndMethod() + " :: " + message);
    }
}