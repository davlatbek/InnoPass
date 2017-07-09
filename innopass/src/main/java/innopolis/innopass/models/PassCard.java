package innopolis.innopass.models;

import java.util.List;

/**
 * Created by davlet on 7/6/17.
 */

public class PassCard {
    private final Long id;
    private User user;
    private boolean isValid;
    private boolean isBlocked;
    private List<QueryCard> queryHistory;
    private List<PermissionType> permissionList;

    public PassCard(Long id){
        this.id = id;
    }

    public PassCard(Long id, List<QueryCard> queryHistory, User user,
                    boolean isValid, boolean isBlocked,
                    List<PermissionType> permissionList) {
        this.id = id;
        this.user = user;
        this.isValid = isValid;
        this.isBlocked = isBlocked;
        this.queryHistory = queryHistory;
        this.permissionList = permissionList;
    }

    public Long getId(){
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public List<QueryCard> getQueryHistory() {
        return queryHistory;
    }

    public void setQueryHistory(List<QueryCard> queryHistory) {
        this.queryHistory = queryHistory;
    }

    public List<PermissionType> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<PermissionType> permissionList) {
        this.permissionList = permissionList;
    }
}
