package blockchainstudy.example.tastyload.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long uuid;

    @NotNull
    private String userId;

    @NotNull
    private String userName;

    @NotNull
    private String userPw;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String email;

    private Role role;

    @NotNull
    private Date joinedDate;

    public UserDto(User source) {
        copyProperties(source, this);
    }
}
