package Laboratory_Work_1_SOLID_Principles.Utils;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return record.getLongThreadID() + " :: " + record.getSourceClassName() + " :: " + record.getSourceMethodName()
                + " :: " + new Date(record.getMillis()) + "\n" + record.getLevel() + " :: " + record.getMessage()
                + "\n";
    }
}
