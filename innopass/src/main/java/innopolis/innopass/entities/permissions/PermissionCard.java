package innopolis.innopass.entities.permissions;

import java.util.List;

import innopolis.innopass.entities.User;

/**
 * Created by davlet on 7/6/17.
 */

public class PermissionCard {
    private final Long id;
    private User user;
    private boolean isValid;
    private boolean isBlocked;
    private List<PermissionQuery> queryHistory;
    private List<PermissionType> permissionList;

    public PermissionCard(Long id){
        this.id = id;
    }

    public PermissionCard(Long id, List<PermissionQuery> queryHistory, User user,
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

    public List<PermissionQuery> getQueryHistory() {
        return queryHistory;
    }

    public void setQueryHistory(List<PermissionQuery> queryHistory) {
        this.queryHistory = queryHistory;
    }

    public List<PermissionType> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<PermissionType> permissionList) {
        this.permissionList = permissionList;
    }
}
