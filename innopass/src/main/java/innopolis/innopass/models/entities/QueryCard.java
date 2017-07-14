package innopolis.innopass.models;

import java.util.List;

/**
 * Created by davlet on 7/6/17.
 */

public class QueryCard {
    private Long id;
    private User user;
    private String queryMessage;
    private boolean status;
    private QueryPriority priority;
    private List<PermissionType> permissionList;

    public QueryCard(Long id, User user, String queryMessage,
                     QueryPriority priority, List<PermissionType> permissionList) {
        this.id = id;
        this.user = user;
        this.queryMessage = queryMessage;
        this.status = true;
        this.priority = priority;
        this.permissionList = permissionList;
    }

    public Long getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public QueryPriority getPriority() {
        return priority;
    }

    public void setPriority(QueryPriority priority) {
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
