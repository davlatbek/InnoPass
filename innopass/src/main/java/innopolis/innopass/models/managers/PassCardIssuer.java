package innopolis.innopass.models.managers;

import java.util.Calendar;

import innopolis.innopass.models.entities.PassCard;
import innopolis.innopass.models.entities.QueryCard;

/**
 * Created by davlet on 7/6/17.
 */

public class PassCardIssuer {
    public PassCard issue(QueryCard query){
        return new PassCard(1L);
    }

    public void changeCardPermissions(QueryCard query){

    }

    public void blockCardForPeriod(Calendar calendar){

    }
}
