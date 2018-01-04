package barcodeproduct.service.BarCodeProduct.model;

import javax.persistence.*;

@Entity(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String extension;
    private String type;

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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ProductUser getProductUser() {
        return productUser;
    }

    public void setProductUser(ProductUser productUser) {
        this.productUser = productUser;
    }
}
