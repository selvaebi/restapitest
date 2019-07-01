package uk.ac.ebi.test.configuration.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uk.ac.ebi.test.entities.SecurityUser;
import uk.ac.ebi.test.repositories.SecurityUserRepository;

import java.util.Arrays;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private SecurityUserRepository securityUserRepository;

    public UserDetailsServiceImpl(SecurityUserRepository securityUserRepository) {
        this.securityUserRepository = securityUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser securityUser = securityUserRepository.findByEmail(username);
        if (securityUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(securityUser.getEmail(), securityUser.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(securityUser.getRole().name())));
    }
}