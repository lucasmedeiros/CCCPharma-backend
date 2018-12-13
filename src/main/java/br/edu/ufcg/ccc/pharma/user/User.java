package br.edu.ufcg.ccc.pharma.user;

import br.edu.ufcg.ccc.pharma.model.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@SuppressWarnings({"unused", "WeakerAccess"})

@Entity
@Table(name = "user")
public class User extends AbstractEntity {

    @NotEmpty(message = "'first name' field may not be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "'last name' field may not be empty")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "'email' field not valid")
    @NotEmpty(message = "'email' field may not be empty")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "'password' field may not be empty")
    @Length(min = 6, message = "your password must have at least 6 characters")
    @JsonIgnore
    private String password;

    @Column(name = "admin")
    @NotEmpty
    private boolean admin;

    @Column(name = "active")
    private int active;

    public User() {}

    public User(String firstName, String lastName, String email, String password, boolean admin, int active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.active = active;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
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
}
