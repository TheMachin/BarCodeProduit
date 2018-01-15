package barcodeproduct.service.BarCodeProduct.model;

import barcodeproduct.service.BarCodeProduct.model.serializer.CompagnySerializer;
import barcodeproduct.service.BarCodeProduct.model.serializer.ShopSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "product")
public class Product implements Serializable{

    /**
     * GTIN Number
     */
    @Id
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compagny_id")
    @JsonSerialize(using = CompagnySerializer.class) //force compagny to be an object in json
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

    public Compagny getCompagny() {
        return compagny;
    }

    public void setCompagny(Compagny compagny) {
        this.compagny = compagny;
    }
}
