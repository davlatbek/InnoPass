package innopolis.innopass.utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by davlet on 6/20/17.
 */

public class TempUsers {
    public static Map<String, String> userPasswordMap = new HashMap<>();

    static {
        userPasswordMap.put("", "");
        userPasswordMap.put("1", "1");
        userPasswordMap.put("albert", "einstein");
        userPasswordMap.put("lillie", "clinton");
        userPasswordMap.put("admin", "");
    }

    public static void addUser(String login, String password){
        userPasswordMap.put(login, password);
    }

    public static boolean existsUser(String login, String password){
        return userPasswordMap.containsKey(login)
                && password.equals(userPasswordMap.get(login));
    }
}
