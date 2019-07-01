package uk.ac.ebi.test.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.ebi.test.entities.SecurityUser;
import uk.ac.ebi.test.repositories.SecurityUserRepository;

@RestController
@RequestMapping(path = "securityUsers")
public class SecurityUserRestController {

    @Autowired
    private SecurityUserRepository securityUserRepository;

    @PostMapping("/sign-up")
    public void signUp(@RequestParam("email") String email, @RequestParam("password") String password) {
        securityUserRepository.save(new SecurityUser(email, password, SecurityUser.Role.ROLE_USER));
    }

    @PostMapping("/changeRoles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void changeRoles(@RequestParam("email") String email, @RequestParam("role") String role) {
        SecurityUser securityUser = securityUserRepository.findByEmail(email);
        securityUser.setRole(SecurityUser.Role.valueOf(role));
        securityUserRepository.save(securityUser);
    }
}
