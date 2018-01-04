package barcodeproduct.service.BarCodeProduct.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name="user")
public class User {

    @Id
    private String username; //email
    private String password;
    private boolean enable;

    @OneToMany(mappedBy = "user")
    private Set<ProductUser> productUsers;

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
}
