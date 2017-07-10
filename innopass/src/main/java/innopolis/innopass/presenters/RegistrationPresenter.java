package innopolis.innopass.presenters;

import android.content.Context;

import innopolis.innopass.interfaces.manager_interfaces.IStudentManager;
import innopolis.innopass.interfaces.manager_interfaces.StudentMethodName;
import innopolis.innopass.interfaces.view_interfaces.IRegistrationView;
import innopolis.innopass.managers.StudentManager;
import innopolis.innopass.models.Student;

/**
 * Created by davlet on 7/9/17.
 */

public class RegistrationPresenter {
    private static RegistrationPresenter INSTANCE;
    IRegistrationView registrationView;
    IStudentManager studentManager;

    private RegistrationPresenter(IRegistrationView registrationView,
                                  IStudentManager studentManager){
        this.studentManager = studentManager;
        this.registrationView = registrationView;
    }

    public static RegistrationPresenter getInstance(IRegistrationView registrationView,
                                                    Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RegistrationPresenter(registrationView,
                    StudentManager.getInstance(context));
        }
        return INSTANCE;
    }

    public void registerNewUser(Student student){
//        //do in standard way
//        if (student != null) {
//            if (studentManager.addStudent(student)){
//                registrationView.goToLoginScreen();
//            } else {
//                registrationView.showError("One of the fields is not valid or filled!");
//            }
//        }
        //do in background
        if (student != null) {
            if (studentManager.doInBackGround(StudentMethodName.ADD_STUDENT, student, null))
                registrationView.goToLoginScreen();
        }
    }
}
