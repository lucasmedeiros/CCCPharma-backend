package br.edu.ufcg.ccc.pharma.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@SuppressWarnings({"unused"})
@Entity
@Table(name = "user")
public class User extends AbstractEntity {

    @NotEmpty(message = "'first name' field may not be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "'last name' field may not be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "'email' field not valid")
    @NotEmpty(message = "'email' field may not be empty")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "'password' field may not be empty")
    @Length(min = 6, message = "your password must have at least 6 characters")
    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @NotEmpty(message = "'role' field may not be empty")
    private String role;

    public User() {}

    public User(String firstName, String lastName, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return this.getRole().toLowerCase().equals("admin");
    }
}
