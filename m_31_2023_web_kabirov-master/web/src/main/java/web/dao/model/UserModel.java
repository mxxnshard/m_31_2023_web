package web.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import web.security.UserRole;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "users_find_by_first_name",
                query = "select u from UserModel as u where firstName = :firstName")})
public class UserModel {
    @Id
    private String id;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;
    private String email;
    private String pass;

    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<InsectModel> insects;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id")
    })
    @Column(name = "user_role_name")
    private Set<UserRole> userRoles = Collections.emptySet();
}
