package blockchainstudy.example.tastyload.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 로그인했을 때 DB에서 유저 정보와 권한 정보를 가져오는 메소드.
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findOneWithRolesByUserId(userId)
                .map(user -> createUser(user))
                .orElseThrow(() -> new UsernameNotFoundException(userId + " -> DB에서 찾을 수 없습니다."));
        return userDetails;
    }

    private org.springframework.security.core.userdetails.User createUser( User user) {
        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUserId(),
                user.getUserPw(),
                grantedAuthorities);

    }
}
