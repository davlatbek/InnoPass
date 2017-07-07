package innopolis.innopass.entities.permissions;

import java.util.List;

import innopolis.innopass.entities.User;

/**
 * Created by davlet on 7/6/17.
 */

public class PermissionCard {
    private User name;
    private boolean isValid;
    private List<PermissionType> permissionTypeList;
}
