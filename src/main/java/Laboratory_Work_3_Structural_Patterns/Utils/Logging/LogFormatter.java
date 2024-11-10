package Laboratory_Work_3_Structural_Patterns.Utils.Logging;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return
                "[" + record.getLevel() + "] :: " + record.getSourceMethodName() + " :: "
                + record.getMessage() + "\n";
    }
}
