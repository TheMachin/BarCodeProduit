package barcodeproduct.service.BarCodeProduct.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="username", scope = String.class)
@Entity(name="user")
public class User implements Serializable{

    @Id
    private String username; //email
    private String password;
    private boolean enable;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private Set<ProductUser> productUsers;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_name", referencedColumnName = "name"))
    private Set<Role> roles;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Set<ProductUser> getProductUsers() {
        return productUsers;
    }

    public void setProductUsers(Set<ProductUser> productUsers) {
        this.productUsers = productUsers;
    }

    /**
     * Add a role for user
     * @param roleName
     */
    private void addRole(String roleName){
        //check if the set exists or to initialize it
        if(this.roles==null){
            roles = new HashSet<Role>();
        }
        Role role = new Role();
        role.setName(roleName);
        if(!roles.contains(role)){
            roles.add(role);
        }
    }

    public void addUserRole(){
        addRole("ROLE_USER");
    }

}
