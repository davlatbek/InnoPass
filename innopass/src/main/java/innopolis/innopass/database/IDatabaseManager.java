package innopolis.innopass.database;

import innopolis.innopass.models.Student;

/**
 * Created by davlet on 7/9/17.
 */

public interface IDatabaseManager {
    public Student getStudentByLogin(String login);
    public boolean addStudent(Student user);
}
