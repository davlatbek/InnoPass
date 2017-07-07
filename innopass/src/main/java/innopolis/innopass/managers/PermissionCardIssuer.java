package innopolis.innopass.managers;

import java.util.Calendar;

import innopolis.innopass.entities.permissions.PermissionCard;
import innopolis.innopass.entities.permissions.PermissionQuery;

/**
 * Created by davlet on 7/6/17.
 */

public class PermissionCardIssuer {
    public PermissionCard issue(PermissionQuery query){
        return new PermissionCard(1L);
    }

    public void changeCardPermissions(PermissionQuery query){

    }

    public void blockCardForPeriod(Calendar calendar){

    }
}
