package innopolis.innopass.presenters;

import innopolis.innopass.database.IDatabaseManager;
import innopolis.innopass.interfaces.view_interfaces.ILoginView;

/**
 * Created by davlet on 7/9/17.
 */

public class LoginPresenter {
    private static LoginPresenter LOGIN_PRESENTER_INSTANCE;
    ILoginView loginView;
    IDatabaseManager database;

    private LoginPresenter(ILoginView loginView, IDatabaseManager database) {
        this.loginView = loginView;
        this.database = database;
    }

    public static synchronized LoginPresenter getInstance(ILoginView loginView,
                                                          IDatabaseManager database) {
        if (LOGIN_PRESENTER_INSTANCE == null) {
            LOGIN_PRESENTER_INSTANCE = new LoginPresenter(loginView, database);
        }
        return LOGIN_PRESENTER_INSTANCE;
    }

    public void validateCredentials(String login, String password){
        if (login != null && password != null && !login.equals("") && !password.equals("")) {
            if  (database.getUserByLogin(login).getPassword().equals(password)){
                loginView.goToUserProfile();
            }
        }
        else {
            loginView.showError("Invalid login or password!");
        }
    }


}
