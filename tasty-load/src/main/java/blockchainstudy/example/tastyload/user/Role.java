package blockchainstudy.example.tastyload.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "role_name", length = 10)
    private String roleName;
}
