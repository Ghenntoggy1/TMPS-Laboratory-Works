package Laboratory_Work_1_SOLID_Principles.Utils;

import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.io.IOException;

public class LoggerImpl implements ILogger {
    private final Logger logger;

    public LoggerImpl() {
        this.logger = Logger.getLogger(LoggerImpl.class.getName());
        this.logger.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new LogFormatter());
        consoleHandler.setLevel(Level.ALL);

        try {
            FileHandler fileHandler = new FileHandler("banking.log", true);
            fileHandler.setFormatter(new LogFormatter());
            fileHandler.setLevel(Level.ALL);
            this.logger.addHandler(fileHandler);
        } catch (IOException e) {
            System.err.println("Failed to initialize file handler for logging: " + e.getMessage());
        }

        this.logger.addHandler(consoleHandler);
        this.logger.setUseParentHandlers(false);
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