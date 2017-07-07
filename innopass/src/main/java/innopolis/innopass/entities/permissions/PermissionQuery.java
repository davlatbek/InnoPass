package innopolis.innopass.entities.permissions;

import org.w3c.dom.ProcessingInstruction;

import java.util.List;

import innopolis.innopass.entities.User;

/**
 * Created by davlet on 7/6/17.
 */

public class PermissionQuery {
    private final Long id;
    private User user;
    private String queryMessage;
    private PermissionPriorityType priority;
    private List<PermissionType> permissionList;

    public PermissionQuery(Long id, User user, String queryMessage,
                           PermissionPriorityType priority, List<PermissionType> permissionList) {
        this.id = id;
        this.user = user;
        this.queryMessage = queryMessage;
        this.priority = priority;
        this.permissionList = permissionList;
    }

    public Long getId() {
        return id;
    }

    public PermissionPriorityType getPriority() {
        return priority;
    }

    public void setPriority(PermissionPriorityType priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQueryMessage() {
        return queryMessage;
    }

    public void setQueryMessage(String queryMessage) {
        this.queryMessage = queryMessage;
    }

    public List<PermissionType> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<PermissionType> permissionList) {
        this.permissionList = permissionList;
    }
}
