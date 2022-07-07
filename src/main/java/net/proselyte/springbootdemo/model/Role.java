package net.proselyte.springbootdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_role",
        uniqueConstraints = {
                @UniqueConstraint(name = "role_name_unique", columnNames = "role_name")
        })
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String role) {
        this.id = id;
        this.name = role;
    }

    //возвращает имя роли
    @Override
    public String getAuthority() {
        return getName();
    }
}
