package innopolis.innopass.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import innopolis.innopass.R;
import innopolis.innopass.entities.Contact;
import innopolis.innopass.entities.ContactType;
import innopolis.innopass.entities.Employee;
import innopolis.innopass.entities.Student;

/**
 * Created by davlet on 7/04/17.
 */

public class TempData {
    public static List<Student> students;
    public static List<Employee> employees;
    public static List<Contact> contacts;
    private static Random random = new Random();

    static {
        Calendar calendar = Calendar.getInstance();
        students = new ArrayList<>();
        employees = new ArrayList<>();
        contacts = new ArrayList<>();

        calendar.set(1994, 01, 19);
        students.add(new Student("lillie", "clinton", "perelman",
                calendar, 1L, R.drawable.portraitphoto, null));
        calendar.set(1990, 01, 20);
        students.add(new Student("albert", "einstein", "stein",
                calendar, 1L, R.drawable.einstein, null));
        calendar.set(1990, 01, 21);
        students.add(new Student("lionel", "messi", "vein",
                calendar, 1L, R.drawable.lillie, null));
        calendar.set(1990, 01, 22);
        students.add(new Student("cristiano", "ronaldo", "kurzvei",
                calendar, 1L, R.drawable.einstein, null));
        calendar.set(1990, 01, 23);
        students.add(new Student("andy", "rubin", "rubin",
                calendar, 1L, R.drawable.lillie, null));

        Contact tempContact;
        for (Student student : students){
            List<Contact> tempList = new ArrayList<>();
            for (int i = 0; i < 3; i++){
                StringBuilder tempPhone = new StringBuilder("+7");
                for (int j = 0; j <= 9; j++){
                    tempPhone.append(random.nextInt(9));
                }
                tempContact = new Contact(ContactType.PHONE, tempPhone.toString());
                tempList.add(tempContact);
            }
            student.setContacts(tempList);
        }
    }

    private TempData() {

    }

    public static List<Student> getStudents() {
        return students;
    }

    public static void setStudents(List<Student> students) {
        TempData.students = students;
    }

    public static List<Employee> getEmployees() {
        return employees;
    }

    public static void setEmployees(List<Employee> employees) {
        TempData.employees = employees;
    }

    public Student getStudentById(Long id) {
        for (Student student : students) {
            if (student.getId().equals(id))
                return student;
        }
        return null;
    }

    public List<Contact> getContactsForStudentById(Long id){
        for (Student student : students){
            if (student.getId().equals(id))
                return student.getContacts();
        }
        return null;
    }
}
