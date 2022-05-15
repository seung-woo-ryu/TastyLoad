package blockchainstudy.example.tastyload.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "uuid")
@AllArgsConstructor @NoArgsConstructor @Builder
@Table(name="users")
@ToString(exclude = "roles")
public class User implements Serializable{
    // User 테이블의 기본 키
    // id 값 따로 할당 안해도 디비가 자동으로 AUTO_INCREMENT해서 기본키 생성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_uid")
    private Long uuid;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "name")
    private String userName;

    @NotBlank
    @Email
    private String email;

    @Column(name = "passwd")
    private String userPw;

    private Date joinedDate;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "role_name")}
    )
    private Set<Role> roles;


}
