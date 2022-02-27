package co.com.sofka.crud.model;

import java.io.Serializable;

public class GroupListDTO implements Serializable {
    private Long id;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id ;
    }

    public void setId(Long id) { this.id = id; }
}
