package innopolis.innopass.presenters;

import android.content.Context;

import innopolis.innopass.interfaces.manager_interfaces.IStudentManager;
import innopolis.innopass.interfaces.view_interfaces.ILoginView;
import innopolis.innopass.managers.StudentManager;
import innopolis.innopass.models.User;
import innopolis.innopass.utilities.SessionManager;

/**
 * Created by davlet on 7/9/17.
 */

public class LoginPresenter {
    private static LoginPresenter INSTANCE;
    ILoginView loginView;
    IStudentManager studentManager;
    SessionManager sessionManager;

    private LoginPresenter(ILoginView loginView, IStudentManager studentManager, Context context) {
        this.loginView = loginView;
        this.studentManager = studentManager;
        this.sessionManager = new SessionManager(context);
    }

    public static synchronized LoginPresenter getInstance(ILoginView loginView, Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LoginPresenter(loginView, StudentManager.getInstance(context), context);
        }
        return INSTANCE;
    }

    public void validateCredentials(String login, String password){
//        //doing work in background
//        studentManager.doInBackGround(StudentMethodName.GET_STUDENT_BY_LOGIN, login, password);

        if (!login.equals("") && !password.equals("")) {
            User user = studentManager.getStudentByLogin(login);
            if (user != null) {
                if  (user.getPassword().equals(password)){
                    loginView.goToUserProfile();
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
