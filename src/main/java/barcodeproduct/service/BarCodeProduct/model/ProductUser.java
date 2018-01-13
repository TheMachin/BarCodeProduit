package barcodeproduct.service.BarCodeProduct.model;

import barcodeproduct.service.BarCodeProduct.model.serializer.ProductSerializer;
import barcodeproduct.service.BarCodeProduct.model.serializer.ShopSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity(name = "productUser")
public class ProductUser implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private float price;
    private Date datePurchase;
    private Date dateEndCommercialWarranty;
    private Date dateEndConstructorWarranty;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "productUser")
    private Set<Document> documents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = ProductSerializer.class)
    private Product gtin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soldProducts_id")
    @JsonSerialize(using = ShopSerializer.class)
    private Shop purchaseLocation;

    public ProductUser() {
    }

    @JsonCreator
    public ProductUser(@JsonProperty("name") String name, @JsonProperty("price") float price, @JsonProperty("datePurchase") Date datePurchase, @JsonProperty("dateEndCommercialWarranty") Date dateEndCommercialWarranty, @JsonProperty("dateEndConstructorWarranty") Date dateEndConstructorWarranty, @JsonProperty("user") User user, @JsonProperty("documents") Set<Document> documents, @JsonProperty("gtin") Product gtin, @JsonProperty("purchaseLocation") Shop purchaseLocation) {
        this.name = name;
        this.price = price;
        this.datePurchase = datePurchase;
        this.dateEndCommercialWarranty = dateEndCommercialWarranty;
        this.dateEndConstructorWarranty = dateEndConstructorWarranty;
        this.user = user;
        this.documents = documents;
        this.gtin = gtin;
        this.purchaseLocation = purchaseLocation;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Date datePurchase) {
        this.datePurchase = datePurchase;
    }

    public Date getDateEndCommercialWarranty() {
        return dateEndCommercialWarranty;
    }

    public void setDateEndCommercialWarranty(Date dateEndCommercialWarranty) {
        this.dateEndCommercialWarranty = dateEndCommercialWarranty;
    }

    public Date getDateEndConstructorWarranty() {
        return dateEndConstructorWarranty;
    }

    public void setDateEndConstructorWarranty(Date dateEndConstructorWarranty) {
        this.dateEndConstructorWarranty = dateEndConstructorWarranty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Product getGtin() {
        return gtin;
    }

    public void setGtin(Product gtin) {
        this.gtin = gtin;
    }

    public Shop getPurchaseLocation() {
        return purchaseLocation;
    }

    public void setPurchaseLocation(Shop purchaseLocation) {
        this.purchaseLocation = purchaseLocation;
    }
}
