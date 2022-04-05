package blockchainstudy.example.tastyload.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "uuid")
@Builder @AllArgsConstructor @NoArgsConstructor
@ToString(exclude="userPw")
@Table(name="users")
public class User {
    // User 테이블의 기본 키
    // id 값 따로 할당 안해도 디비가 자동으로 AUTO_INCREMENT해서 기본키 생성
    @Id
    @GeneratedValue
    @Column(name = "user_uid")
    private Long uuid;

    @Column(name = "id")
    private String userId;

    @Column(name = "name")
    private String userName;

    @NotBlank
    @Email
    private String email;

    @Column(name = "passwd")
    private String userPw;

    private Date joinedDate;

    // TODO: 리뷰와 many2one 관계 jpa 추가.
    // private Review review
}
