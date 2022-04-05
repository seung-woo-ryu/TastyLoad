package blockchainstudy.example.tastyload.user;

import lombok.*;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.Date;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "uuid")
@Builder @AllArgsConstructor @NoArgsConstructor
@ToString(exclude="userPw")
public class User {
    // User 테이블의 기본 키
    // id 값 따로 할당 안해도 디비가 자동으로 AUTO_INCREMENT해서 기본키 생성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uuid;

    private String userId;

    private String userName;

    private Email email;

    private String userPw;

    private Date joinDate;
    // TODO: 리뷰와 many2one 관계 jpa 추가.
    // private Review review
}
