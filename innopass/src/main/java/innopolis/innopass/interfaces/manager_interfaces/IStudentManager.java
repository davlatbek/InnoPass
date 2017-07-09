package innopolis.innopass.interfaces.manager_interfaces;

import java.util.List;

import innopolis.innopass.models.Student;

/**
 * Created by davlet on 7/9/17.
 */

public interface IStudentManager {
    List<Student> getAllStudentsList();
    List<Student> getStudentsListByGroupId(Long groupId);
    Student getStudentById(Long studentId);
}
