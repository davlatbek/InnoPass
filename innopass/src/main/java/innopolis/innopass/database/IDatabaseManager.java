package innopolis.innopass.database;

import innopolis.innopass.models.User;

/**
 * Created by davlet on 7/9/17.
 */

public interface IDatabaseManager {
    public User getUserByLogin(String login);
    public String getPasswordByLogin(String password);
    public boolean addUser(User user);
}
