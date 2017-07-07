package innopolis.innopass.managers;

import innopolis.innopass.entities.permissions.PermissionCard;
import innopolis.innopass.entities.permissions.PermissionQuery;

/**
 * Created by davlet on 7/6/17.
 */

public class IssuePermissionCard {
    public PermissionCard issue(PermissionQuery query){
        return new PermissionCard();
    }
}
