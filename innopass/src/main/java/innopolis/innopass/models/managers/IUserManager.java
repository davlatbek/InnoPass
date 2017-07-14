package innopolis.innopass.interfaces.manager_interfaces;

import java.util.List;

import innopolis.innopass.models.User;

/**
 * Created by davlet on 7/9/17.
 */

public interface IUserManager {
    User getUserByLogin(String login);
    User getUserById(Long userId);
    List<User> getAllUsersList();
    boolean addUser(User user);
    boolean doInBackGround(Object... args);
}
