package Laboratory_Work_3_Structural_Patterns.User;

import Laboratory_Work_3_Structural_Patterns.Interfaces.ILogger;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IUser;
import Laboratory_Work_3_Structural_Patterns.Utils.Logging.LoggerImpl;

public class User implements IUser {
    private int userId;
    private String name;
    private final ILogger logger;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.logger = LoggerImpl.getInstance();
        this.logger.infoLog("User " + name + " created with ID " + userId);
    }

    @Override
    public int getUserId() {
        this.logger.infoLog("Requested ID for user " + this.userId);
        return this.userId;
    }

    @Override
    public String getName() {
        this.logger.infoLog("Requested name for user " + this.userId);
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name.matches("[a-zA-Z]+")) {
            this.name = name;
            this.logger.infoLog("User " + this.userId + " changed name to " + name);
        } else {
            this.logger.errorLog("Attempt to set invalid name " + name + " for user " + this.userId);
        }
    }
}
