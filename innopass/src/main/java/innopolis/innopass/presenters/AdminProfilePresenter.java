package innopolis.innopass.presenters;

import android.content.Context;

import innopolis.innopass.models.managers.IUserManager;
import innopolis.innopass.views.activities.IAdminProfileView;
import innopolis.innopass.models.managers.UserManager;
import innopolis.innopass.utilities.SessionManager;

/**
 * Created by davlet on 7/12/17.
 */

public class AdminProfilePresenter {
    private static AdminProfilePresenter INSTANCE;
    IAdminProfileView adminProfileView;
    IUserManager userManager;
    SessionManager sessionManager;

    private AdminProfilePresenter(IAdminProfileView adminProfileView, IUserManager userManager, Context context) {
        this.adminProfileView = adminProfileView;
        this.userManager = userManager;
        this.sessionManager = new SessionManager(context);
    }

    public static synchronized AdminProfilePresenter getInstance(IAdminProfileView adminProfileView,
                                                                 Context context) {
        if (INSTANCE == null) {
            INSTANCE = new AdminProfilePresenter(adminProfileView,
                    UserManager.getInstance(context), context);
        }
        return INSTANCE;
    }
}
