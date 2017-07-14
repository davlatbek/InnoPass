package innopolis.innopass.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import innopolis.innopass.R;
import innopolis.innopass.models.entities.Contact;
import innopolis.innopass.models.entities.ContactType;
import innopolis.innopass.models.entities.Employee;
import innopolis.innopass.models.entities.PassCard;
import innopolis.innopass.models.entities.PermissionType;
import innopolis.innopass.models.entities.QueryCard;
import innopolis.innopass.models.entities.QueryPriority;
import innopolis.innopass.models.entities.User;

/**
 * Created by davlet on 7/04/17.
 */

public class TempData {
    public static List<User> usersList;
    public static List<QueryCard> queryCardList;
    public static List<PassCard> cardList;
    public static List<Contact> contacts;
    public static List<Employee> employees;
    private static Random random = new Random();

    static {
        Calendar calendar = Calendar.getInstance();
        usersList = new ArrayList<>();
        employees = new ArrayList<>();
        contacts = new ArrayList<>();
        queryCardList = new ArrayList<>();
        cardList = new ArrayList<>();

        //these created queries
        calendar.set(1994, 01, 19);
        usersList.add(new User(1L, "login1", "Password19#", "name1", "surname1", "middlename1",
                calendar, R.drawable.einstein, null));
        calendar.set(1990, 01, 20);
        usersList.add(new User(2L, "login2", "pass2", "name2", "surname2", "middlename2",
                calendar, R.drawable.einstein, null));
        calendar.set(1990, 01, 21);
        usersList.add(new User(3L, "login3", "pass3", "name3", "surname3", "middlename3",
                calendar, R.drawable.einstein, null));
        calendar.set(1990, 01, 42);
        usersList.add(new User(4L, "login4", "pass4", "name4", "surname4", "middlename4",
                calendar, R.drawable.einstein, null));
        calendar.set(1990, 01, 23);
        usersList.add(new User(5L, "login5", "pass5", "name5", "surname5", "middlename5",
                calendar, R.drawable.einstein, null));


        //these already have cards
        usersList.add(new User(6L, "betty", "gates", "Betty", "Gates", "Mendelson",
                Calendar.getInstance(), R.drawable.lillie, null));
        usersList.add(new User(7L, "login", "pass", "Albert", "Einstein", "Jewish",
                Calendar.getInstance(), R.drawable.einstein, null));
        usersList.add(new User(8L, "login", "pass", "Andy", "Rubin", "Jewish",
                Calendar.getInstance(), R.drawable.portraitphoto, null));
        usersList.add(new User(9L, "login", "pass", "Lillie", "Clinton", "Jewish",
                Calendar.getInstance(), R.drawable.lillie, null));

        Contact tempContact;
        for (User student : usersList){
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

        List<PermissionType> permissionTypes = new ArrayList<>();
        permissionTypes.add(PermissionType.CAMPUS1);
        permissionTypes.add(PermissionType.UNIVERSITY);
        permissionTypes.add(PermissionType.CAMPUS4);
        permissionTypes.add(PermissionType.CAMPUS1);
        queryCardList.add(new QueryCard(1L, usersList.get(0),
                "Hello, I want a pass card only for this semester, including month december 2017.", QueryPriority.MEDIUM, permissionTypes));
        queryCardList.add(new QueryCard(2L, usersList.get(1),
                "Hello, I want a pass card only for this semester, including month december 2017.", QueryPriority.MEDIUM, permissionTypes));
        queryCardList.add(new QueryCard(3L, usersList.get(2),
                "Some message", QueryPriority.MEDIUM, permissionTypes));
        queryCardList.add(new QueryCard(4L, usersList.get(3),
                "Some message", QueryPriority.LOW, permissionTypes));
        queryCardList.add(new QueryCard(5L, usersList.get(4),
                "Some message", QueryPriority.HIGH, permissionTypes));

        cardList.add(new PassCard(1L, null,
                usersList.get(5),
                false, false, permissionTypes));
        cardList.add(new PassCard(2L, null,
                usersList.get(6),
                false, false, permissionTypes));
        cardList.add(new PassCard(3L, null,
                usersList.get(7),
                true, false, permissionTypes));
        cardList.add(new PassCard(4L, null,
                usersList.get(8),
                false, false, permissionTypes));
    }

    private TempData() {

    }

    public static User getUserById(Long id) {
        for (User user : usersList) {
            if (user.getId().equals(id))
                return user;
        }
        return null;
    }

    public List<Contact> getContactsForStudentById(Long id){
        for (User student : usersList){
            if (student.getId().equals(id))
                return student.getContacts();
        }
        return null;
    }
}
