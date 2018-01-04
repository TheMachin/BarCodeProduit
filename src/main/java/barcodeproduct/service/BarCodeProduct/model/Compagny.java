package barcodeproduct.service.BarCodeProduct.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "compagny")
public class Compagny {

    @Id
    private String name;


    @OneToMany(mappedBy = "compagny")
    private Set<Product> products;

    @OneToMany(mappedBy = "compagny")
    private Set<Shop> shops;

    public Compagny(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Shop> getShops() {
        return shops;
    }

    public void setShops(Set<Shop> shops) {
        this.shops = shops;
    }
}
