package innopolis.innopass.interfaces.view_interfaces;

/**
 * Created by davlet on 7/9/17.
 */

public interface ILoginView {
    void showError(String message);
    void goToUserProfile();
    void goToRegistrationPage();
    void setTextLoginPassword(String login, String pass);
}
