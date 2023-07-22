package org.crypto.training.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "assets")
public class Asset {

    public Asset() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @OneToMany(mappedBy = "asset", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Investment> investments;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}
