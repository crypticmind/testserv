package ar.com.crypticmind.testserv.service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(configure: HttpSecurity) {
        configure
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().disable()
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .x509()
                .subjectPrincipalRegex("CN=(.*?)(?:,|\$)")
                .userDetailsService(userDetailsService())
    }

    @Bean
    override fun userDetailsService() =
            InMemoryUserDetailsManager().also {
                it.createUser(User.withUsername("xxtestserv-client").password("password").roles("user").build())
            }

}