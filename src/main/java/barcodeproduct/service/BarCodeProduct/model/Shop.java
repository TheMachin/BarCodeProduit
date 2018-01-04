package barcodeproduct.service.BarCodeProduct.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String address;
    private boolean online;

    @OneToMany(mappedBy = "purchaseLocation")
    private Set<ProductUser> soldProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compagny_id")
    private Compagny compagny;

    private Shop(){

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Set<ProductUser> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductUser> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public Compagny getCompagny() {
        return compagny;
    }

    public void setCompagny(Compagny compagny) {
        this.compagny = compagny;
    }
}
