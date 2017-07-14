package innopolis.innopass.database;

import innopolis.innopass.models.User;

/**
 * Created by davlet on 7/9/17.
 */

public interface IDatabaseManager {
    User getUserByLogin(String login);
    boolean addUser(User user);
}
