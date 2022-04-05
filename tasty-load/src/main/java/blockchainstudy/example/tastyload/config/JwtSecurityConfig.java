package blockchainstudy.example.tastyload.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web){
        // h2-console, 파비콘 요청은 security 적용  x.
        // TODO: 얘 없으면 h2 console 작업하는게 귀찮아질거 같은데 어떻게 되는지 확인.
        web
                .ignoring()
                .antMatchers(
                        "/h2-console/**",
                        "/favicon.ico"
                );

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // HttpServletRequest를 사용하는 요청들 접근 제한 설정
        // api/hello는 인증 없이 요청 가능
        // anyRequest().authenticated() => 나머지 요처들은 모두 인증이 필요.
        http
                .authorizeRequests()
                .antMatchers("/api/hello").permitAll()
                .anyRequest().authenticated();
    }

}
