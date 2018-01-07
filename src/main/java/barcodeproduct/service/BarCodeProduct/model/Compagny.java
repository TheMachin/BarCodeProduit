package barcodeproduct.service.BarCodeProduct.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="name", scope = String.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "compagny")
public class Compagny implements Serializable{

    @Id
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "compagny")
    private Set<Product> products;

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
}
