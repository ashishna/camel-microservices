package pro.codeschool.camelmicroservices.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name="users")
@NamedQuery(name = "step1", query = "select x from users x where x.id = '?1'")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="first_name", nullable = false)
    private String fName;

    @Column(name="last_name", nullable = false)
    @NotNull
    private String lName;

    @Column(name="is_enabled")
    @NotNull
    private boolean isEnabled;

    @Column(name="email")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
