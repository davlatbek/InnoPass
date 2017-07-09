package innopolis.innopass.presenters;

import innopolis.innopass.database.IDatabaseManager;
import innopolis.innopass.interfaces.view_interfaces.IRegistrationView;

/**
 * Created by davlet on 7/9/17.
 */

public class RegistrationPresenter {
    private static RegistrationPresenter REGISTRATION_PRESENTER_INSTANCE;
    IRegistrationView registrationView;
    IDatabaseManager database;

    private RegistrationPresenter(IRegistrationView registrationView, IDatabaseManager database){
        this.database = database;
        this.registrationView = registrationView;
    }

    public static RegistrationPresenter getInstance(IRegistrationView registrationView,
                                                    IDatabaseManager database) {
        if (REGISTRATION_PRESENTER_INSTANCE == null) {
            REGISTRATION_PRESENTER_INSTANCE = new RegistrationPresenter(registrationView, database);
        }
        return REGISTRATION_PRESENTER_INSTANCE;
    }
}
