package barcodeproduct.service.BarCodeProduct.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "product")
public class Product {

    /**
     * GTIN Number
     */
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "gtin")
    private Set<ProductUser> productUsers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compagny_id")
    private Compagny compagny;

    public Product(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductUser> getProductUsers() {
        return productUsers;
    }

    public void setProductUsers(Set<ProductUser> productUsers) {
        this.productUsers = productUsers;
    }

    public Compagny getCompagny() {
        return compagny;
    }

    public void setCompagny(Compagny compagny) {
        this.compagny = compagny;
    }
}
