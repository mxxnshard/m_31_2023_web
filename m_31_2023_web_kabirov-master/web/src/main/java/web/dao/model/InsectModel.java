package web.dao.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "insects")
public class InsectModel {
    @Id
    String id;

    @Column(name = "user_id")
    String userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ToString.Exclude
    UserModel user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "hats2seasons",
            joinColumns = @JoinColumn(name = "hat_id"),
            inverseJoinColumns = @JoinColumn(name = "season_id"))
    @EqualsAndHashCode.Exclude
    Set<SeasonModel> seasons;

    String name;
    Integer size;

    @Column(name = "created_at")
    LocalDateTime createdAt;
}
