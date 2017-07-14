package innopolis.innopass.views.activities;

/**
 * Created by davlet on 7/9/17.
 */

public interface ILoginView {
    void showError(String message);
    void goToUserProfile(Long id);
    void goToAdminProfile();
    void goToRegistrationPage();
    void setTextLoginPassword(String login, String pass);
}
