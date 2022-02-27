package co.com.sofka.crud.model;

import java.io.Serializable;

public class TodoDTO implements Serializable {
    private Long id;
    private String name;
    private boolean completed;
    private Long groupListId;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted(){
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Long getGroupListId() {
        return this.groupListId;
    }

    public void setGroupListId(Long gid) {
        this.groupListId = gid;
    }
}