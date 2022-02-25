package co.com.sofka.crud.model;

import org.hibernate.loader.plan.exec.process.spi.ReturnReader;

import javax.persistence.*;
import java.util.List;
import javax.persistence.OneToMany;

import static javax.persistence.CascadeType.ALL;

@Entity
public class GroupList {

    @Id
    @GeneratedValue
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
}
