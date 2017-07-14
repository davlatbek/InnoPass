package innopolis.innopass.presenters;

import android.content.Context;

import innopolis.innopass.models.entities.User;
import innopolis.innopass.models.managers.IUserManager;
import innopolis.innopass.models.managers.MethodName;
import innopolis.innopass.models.managers.UserManager;
import innopolis.innopass.views.activities.IRegistrationView;

/**
 * Created by davlet on 7/9/17.
 */

public class RegistrationPresenter {
    private static RegistrationPresenter INSTANCE;
    IRegistrationView registrationView;
    IUserManager userManager;

    private RegistrationPresenter(IRegistrationView registrationView,
                                  IUserManager userManager){
        this.userManager = userManager;
        this.registrationView = registrationView;
    }

    public static RegistrationPresenter getInstance(IRegistrationView registrationView,
                                                    Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RegistrationPresenter(registrationView,
                    UserManager.getInstance(context));
        }
        return INSTANCE;
    }

    public void registerNewUser(User user){
//        //do in standard way
//        if (student != null) {
//            if (userManager.addUser(student)){
//                registrationView.goToLoginScreen();
//            } else {
//                registrationView.showError("One of the fields is not valid or filled!");
//            }
//        }
        //do in background
        if (user != null) {
            if (userManager.doInBackGround(MethodName.ADD_STUDENT, user, null))
                registrationView.goToLoginScreen();
        }
    }
}
