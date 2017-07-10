package innopolis.innopass.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;
import java.util.Map;

import innopolis.innopass.views.activities.LoginActivity;

/**
 * Created by davlet on 7/10/17.
 */

public class SessionManager {
    SharedPreferences sharedPreferences;
    Editor editor;
    Context context;

    private static final Integer PRIVATE_MODE = 0;
    private static final String PREF_NAME = "PREF";
    private static final String IS_LOGGED_IN = "logged_in";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASS = "pass";

    public SessionManager(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(String login, String password){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(KEY_LOGIN, login);
        editor.putString(KEY_PASS, password);
        editor.commit();
    }

    public void checkLogin(){
        if (!this.isLoggedIn()){
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
    }

    public void logout(){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public Map<String, String> getUserDetails(){
        Map<String, String> user = new HashMap<>();
        user.put(KEY_LOGIN, sharedPreferences.getString(KEY_LOGIN, null));
        user.put(KEY_PASS, sharedPreferences.getString(KEY_PASS, null));
        return user;
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }
}
