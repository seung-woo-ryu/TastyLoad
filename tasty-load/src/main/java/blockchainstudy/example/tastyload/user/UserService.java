package blockchainstudy.example.tastyload.user;


import blockchainstudy.example.tastyload.errors.DuplicatedUserException;
import blockchainstudy.example.tastyload.jwt.JwtFilter;
import blockchainstudy.example.tastyload.jwt.JwtTokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public UserDto signup(UserDto userDto) {
        if (userRepository.findOneWithRolesByUserId(userDto.getUserId()).orElse(null) != null) {
            throw new DuplicatedUserException("이미 존재하는 유저입니다");
        }

        Role role = Role.builder()
                .roleName("ROLE_ADMIN")
                .build();

        User user = User.builder()
                .userName(userDto.getUserName())
                .userPw("{noop}"+userDto.getUserPw())
                .email(userDto.getEmail())
                .userId(userDto.getUserId())
                .roles(new HashSet<>(Arrays.asList(userDto.getRole())))
                .joinedDate(userDto.getJoinedDate())
                .build();

        return new UserDto(userRepository.save(user));
    }

    public ResponseEntity<TokenDto> authenticate(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getUserPw());

        // authenticate(~Token) 부분이 실행될 때 CustomUserDetailsService.loadUserByUserId가 실행됨.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}
