package innopolis.innopass.presenters;

import android.content.Context;

import innopolis.innopass.models.managers.IUserManager;
import innopolis.innopass.views.activities.ILoginView;
import innopolis.innopass.models.managers.UserManager;
import innopolis.innopass.models.entities.User;
import innopolis.innopass.utilities.SessionManager;

/**
 * Created by davlet on 7/9/17.
 */

public class LoginPresenter {
    private static LoginPresenter INSTANCE;
    ILoginView loginView;
    IUserManager studentManager;
    SessionManager sessionManager;

    private LoginPresenter(ILoginView loginView, IUserManager studentManager, Context context) {
        this.loginView = loginView;
        this.studentManager = studentManager;
        this.sessionManager = new SessionManager(context);
    }

    public static synchronized LoginPresenter getInstance(ILoginView loginView, Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LoginPresenter(loginView, UserManager.getInstance(context), context);
        }
        return INSTANCE;
    }

    public void validateCredentials(String login, String password){
        if (!login.equals("") && !password.equals("")) {
            User user = studentManager.getUserByLogin(login);
            if (user != null) {
                if  (user.getPassword().equals(password)){
                    if (user.getLogin().equals("admin"))
                        loginView.goToAdminProfile();
                    else
                        loginView.goToUserProfile(user.getId());
                    sessionManager.createLoginSession(login, password);
                } else {
                    loginView.showError("Invalid password!");
                }
            } else {
                loginView.showError("User " + login + " doesn't exist!");
            }
        }
        else {
            loginView.showError("Credentials can't be empty!");
        }
    }
}
