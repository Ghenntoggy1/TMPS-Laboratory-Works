package Laboratory_Work_2_Creational_Patterns.Utils.Logging;

import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.io.IOException;

public final class LoggerImpl implements ILogger {
    private static LoggerImpl instance;
    private final Logger logger;

    public LoggerImpl() {
        this.logger = Logger.getLogger(LoggerImpl.class.getName());
        this.logger.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new LogFormatter());
        consoleHandler.setLevel(Level.ALL);

        try {
            FileHandler fileHandler = new FileHandler("src/main/java/Laboratory_Work_1_SOLID_Principles/Utils/Logging/Logs/logs.log", true);
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
    public static LoggerImpl getInstance() {
        if (instance == null) {
            instance = new LoggerImpl();
            instance.infoLog("Logger Initialized using Singleton Pattern");
        }
        else {
            instance.infoLog("Logger already initialized - Returning existing instance");
        }
        return instance;
    }

    @Override
    public void infoLog(String message) {
        logger.log(Level.INFO, message);
    }

    @Override
    public void warningLog(String message) {
        logger.log(Level.WARNING, message);
    }

    @Override
    public void errorLog(String message) {
        logger.log(Level.SEVERE, message);
    }
}