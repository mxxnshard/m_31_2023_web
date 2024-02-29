package web.dao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "seasons")
public class SeasonModel {
    @Id
    String id;
    String name;

    @ManyToMany(mappedBy = "seasons")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<InsectModel> insects;
}
