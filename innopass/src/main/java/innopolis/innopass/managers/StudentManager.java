package innopolis.innopass.managers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import innopolis.innopass.database.DatabaseHelper;
import innopolis.innopass.database.IDatabaseManager;
import innopolis.innopass.interfaces.manager_interfaces.IStudentManager;
import innopolis.innopass.interfaces.manager_interfaces.StudentMethodName;
import innopolis.innopass.models.Student;

/**
 * Created by davlet on 7/9/17.
 */

public class StudentManager extends AsyncTask<Object, String, Object[]>
        implements IStudentManager {
    private static StudentManager studentManager;
    private IDatabaseManager databaseManager;
    private Context context;

    public StudentManager(Context context){
        this.context = context;
        databaseManager = DatabaseHelper.getInstance(context);
    }

    public static StudentManager getInstance(Context context) {
        if (studentManager == null) {
            synchronized (Student.class){
                if (studentManager == null) {
                    studentManager = new StudentManager(context);
                }
            }
        }
        return studentManager;
    }

    public void setContext(Context context){
        this.context = context;
        databaseManager = DatabaseHelper.getInstance(context);
    }

    @Override
    public List<Student> getAllStudentsList() {
        return null;
    }

    @Override
    public Student getStudentById(Long studentId) {
        return null;
    }

    @Override
    public Student getStudentByLogin(String login) {
        return databaseManager.getStudentByLogin(login);
    }

    @Override
    public boolean addStudent(Student student) {
        return databaseManager.addStudent(student);
    }

    @Override
    public boolean doInBackGround(Object... args) {
        this.execute(args);
        return true;
    }

    @Override
    protected Object[] doInBackground(Object... args) {
        StudentMethodName methodName = (StudentMethodName) args[0];
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
                addStudent((Student) args[1]);
                result[0] = "Finished adding new student!";
                result[1] = null;
                result[2] = null;
                return result;
            case GET_STUDENT_BY_LOGIN:
                publishProgress("Getting student by login...");
                result[1] = getStudentByLogin((String) args[1]);
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
