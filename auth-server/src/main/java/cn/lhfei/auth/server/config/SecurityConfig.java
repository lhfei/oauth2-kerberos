package cn.lhfei.auth.server.config;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
@PropertySource("classpath:/ldap.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.requestMatchers()
        .antMatchers("/login", "/login.html", "/oauth/authorize", "perform_login")
        .and()
        
        .requestMatchers()
        .antMatchers("/**")
        .and()
        .authorizeRequests()
        
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        /*.loginPage("/login.html")
        .loginProcessingUrl("/perform_login")
        .defaultSuccessUrl("/home.html", true)
        .failureUrl("/login.html?error=true")*/
        .permitAll();
    }

    
    @Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.ldapAuthentication()
			.userDnPatterns("uid={0},ou=People")
			.groupSearchBase("ou=Group")
			.contextSource(contextSource())
			.passwordCompare()
				.passwordEncoder(new LdapShaPasswordEncoder())
				.passwordAttribute("userPassword");
	}

	@Bean
	public DefaultSpringSecurityContextSource contextSource() {
		LOG.info("LDAP URL: {}", ldapurl);
		return new DefaultSpringSecurityContextSource(Collections.singletonList(ldapurl),
				baseDn);
	}
	
	@Bean 
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate(contextSource());
	}
	
	@Autowired
    private AuthenticationManager authenticationManager;
	@Value("${auth.ldap.url}")
	private String ldapurl;
	@Value("${auth.ldap.base}")
	private String baseDn;
	
}
