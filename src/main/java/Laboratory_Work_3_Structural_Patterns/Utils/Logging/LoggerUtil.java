package Laboratory_Work_3_Structural_Patterns.Utils.Logging;

import Laboratory_Work_3_Structural_Patterns.Interfaces.ILogger;
import Laboratory_Work_3_Structural_Patterns.Utils.Adapters.Log4jAdapter;
import Laboratory_Work_3_Structural_Patterns.Utils.Adapters.LoggingLoggerAdapter;

public class LoggerUtil {
    public static ILogger getInstance() {
        return Log4jAdapter.getInstance();
    }
}