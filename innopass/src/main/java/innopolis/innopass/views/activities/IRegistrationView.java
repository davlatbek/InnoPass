package innopolis.innopass.views.activities;

import android.view.View;

/**
 * Created by davlet on 7/9/17.
 */

public interface IRegistrationView {
    void registerUserButton(View view);
    void cancelRegistration(View view);
    void goToLoginScreen();
    void showError(String message);
}
