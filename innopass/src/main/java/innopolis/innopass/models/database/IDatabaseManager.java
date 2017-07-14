package innopolis.innopass.models.database;

import innopolis.innopass.models.entities.User;

/**
 * Created by davlet on 7/9/17.
 */

public interface IDatabaseManager {
    User getUserByLogin(String login);
    boolean addUser(User user);
}
