package Laboratory_Work_1_SOLID_Principles.Utils.Logging;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return new Date(record.getMillis()) + " :: " +
                "[" + record.getLevel() + "] :: " + record.getSourceMethodName() + " :: "
                + record.getMessage() + "\n";
    }
}
