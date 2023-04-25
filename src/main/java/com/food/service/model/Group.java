package com.food.service.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_group")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToMany
    @JoinTable(name = "group_permission",
                joinColumns = @JoinColumn(name = "group_id"),
                inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group group)) return false;
        return getId().equals(group.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
