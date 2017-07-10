package innopolis.innopass.interfaces.manager_interfaces;

import java.util.List;

import innopolis.innopass.models.Student;

/**
 * Created by davlet on 7/9/17.
 */

public interface IStudentManager {
    Student getStudentByLogin(String login);
    boolean addStudent(Student student);
    List<Student> getAllStudentsList();
    Student getStudentById(Long studentId);
    boolean doInBackGround(Object... args);
}
