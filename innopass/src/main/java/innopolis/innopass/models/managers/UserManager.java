package innopolis.innopass.managers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import innopolis.innopass.database.DatabaseHelper;
import innopolis.innopass.database.IDatabaseManager;
import innopolis.innopass.interfaces.manager_interfaces.IUserManager;
import innopolis.innopass.interfaces.manager_interfaces.MethodName;
import innopolis.innopass.models.Student;
import innopolis.innopass.models.User;

/**
 * Created by davlet on 7/9/17.
 */

public class UserManager extends AsyncTask<Object, String, Object[]>
        implements IUserManager {
    private static UserManager userManager;
    private IDatabaseManager databaseManager;
    private Context context;

    public UserManager(Context context){
        this.context = context;
        databaseManager = DatabaseHelper.getInstance(context);
    }

    public static UserManager getInstance(Context context) {
        if (userManager == null) {
            synchronized (Student.class){
                if (userManager == null) {
                    userManager = new UserManager(context);
                }
            }
        }
        return userManager;
    }

    public void setContext(Context context){
        this.context = context;
        databaseManager = DatabaseHelper.getInstance(context);
    }

    @Override
    public List<User> getAllUsersList() {
        return null;
    }

    @Override
    public Student getUserById(Long userId) {
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        return databaseManager.getUserByLogin(login);
    }

    @Override
    public boolean addUser(User user) {
        return databaseManager.addUser(user);
    }

    @Override
    public boolean doInBackGround(Object... args) {
        this.execute(args);
        return true;
    }

    @Override
    protected Object[] doInBackground(Object... args) {
        MethodName methodName = (MethodName) args[0];
        Object args1;
        if (args[1] != null)
            args1 = args[1];
        Object args2;
        if (args[2] != null)
            args2 = args[2];
        //return objects
        //1 - finish message
        //2 - return object
        //3 - additional arg
        Object[] result = new Object[3];
        switch (methodName) {
            case ADD_STUDENT:
                publishProgress("Adding new student...");
                addUser((User)args[1]);
                result[0] = "Finished adding new student!";
                result[1] = null;
                result[2] = null;
                return result;
            case GET_STUDENT_BY_LOGIN:
                publishProgress("Getting student by login...");
                result[1] = getUserByLogin((String) args[1]);
                result[0] = "Finished getting student";
                result[2] = args[2];
                break;
            default:
                Log.i("tag", "Nothing to do in backgroud");
                break;
        }
        return result;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Toast.makeText(context, values[0], Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Object[] object) {
        super.onPostExecute(object);
        Toast.makeText(context, object[0].toString(), Toast.LENGTH_SHORT).show();
    }
}
