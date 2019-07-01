package uk.ac.ebi.test.entities;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class SecurityUser {

    @Email
    @Column(nullable = false)
    @NotNull
    @Id
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    public enum Role { ROLE_USER, ROLE_ADMIN }

    @Enumerated(EnumType.STRING)
    private Role role;

    public SecurityUser() {
    }

    public SecurityUser(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
