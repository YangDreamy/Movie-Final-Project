package fr.epita.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/index","/login").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll();
	}
	
	
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.ldapAuthentication()
			.userDnPatterns("cn={0},ou=users,dc=example,dc=com")
			.contextSource()
			.url("ldap://localhost:10389/????X-CONNECTION-NAME=movieconnection,X-BIND-USER=uid=admin%2cou=system,X-BIND-PASSWORD=secret,X-COUNT-LIMIT=1000");
		
	}
	
	
//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//		
//
//		return new InMemoryUserDetailsManager(user);
//	}

}
