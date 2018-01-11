package barcodeproduct.service.BarCodeProduct.model;

import com.fasterxml.jackson.annotation.*;
import com.mysql.jdbc.Blob;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "document")
public class Document implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;

    @JsonIgnore
    private byte[] file;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productUser_id")
    private ProductUser productUser;

    public Document(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ProductUser getProductUser() {
        return productUser;
    }



    public void setProductUser(ProductUser productUser) {
        this.productUser = productUser;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
