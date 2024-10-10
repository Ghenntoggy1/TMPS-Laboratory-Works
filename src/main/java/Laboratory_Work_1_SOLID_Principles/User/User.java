package Laboratory_Work_1_SOLID_Principles.User;


import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.IUser;

public class User implements IUser {
    private int userId;
    private String name;
    private ILogger logger;

    public User(int userId, String name, ILogger logger) {
        this.userId = userId;
        this.name = name;
        this.logger = logger;
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
