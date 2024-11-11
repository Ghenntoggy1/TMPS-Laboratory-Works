package Laboratory_Work_3_Structural_Patterns.Utils.Logging;

import Laboratory_Work_3_Structural_Patterns.Enums.UserRoleEnum;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ILogger;
import Laboratory_Work_3_Structural_Patterns.Utils.Adapters.Log4jAdapter;
import Laboratory_Work_3_Structural_Patterns.Utils.Adapters.LoggingLoggerAdapter;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class LoggerProxy {
    ILogger logger;

    public LoggerProxy() {
        this.logger = LoggingLoggerAdapter.getInstance();
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
        if (checkRole()) {
            ILogger logger = LoggingLoggerAdapter.getInstance();
            logger.infoLog("Logger Proxy Initialized using ADMIN Role");
            return logger;
        }
        return Log4jAdapter.getInstance();
    }
}