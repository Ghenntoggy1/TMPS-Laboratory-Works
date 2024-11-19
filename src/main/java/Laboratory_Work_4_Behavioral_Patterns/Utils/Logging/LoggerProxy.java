package Laboratory_Work_4_Behavioral_Patterns.Utils.Logging;

import Laboratory_Work_4_Behavioral_Patterns.Enums.UserRoleEnum;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ILogger;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Adapters.Log4jAdapter;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Adapters.LoggingLoggerAdapter;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class LoggerProxy implements ILogger {
    ILogger logger;

    public LoggerProxy() {
        if (checkRole()) {
            this.logger = LoggingLoggerAdapter.getInstance();
            logger.infoLog("Logger Proxy Initialized using ADMIN Role");
        }
        else {
            this.logger = Log4jAdapter.getInstance();
            logger.infoLog("Logger Proxy Initialized using USER Role");
        }
    }

    private static boolean checkRole() {
        String content;
        try {
            content = Files.readString(Paths.get("src/main/resources/access.json"));
        }
        catch (Exception e) {
            return false;
        }
        String userRole = new JSONObject(content).getString("UserRole");
        if (userRole.equals(UserRoleEnum.ADMIN.toString())) {
            return true;
        }
        return false;
    }

    public static ILogger getInstance() {
        return new LoggerProxy();
    }

    @Override
    public void infoLog(String message) {
        this.logger.infoLog(message);
    }

    @Override
    public void errorLog(String message) {
        this.logger.errorLog(message);
    }

    @Override
    public void warningLog(String message) {
        this.logger.warningLog(message);
    }
}