package barcodeproduct.service.BarCodeProduct.service;

import barcodeproduct.service.BarCodeProduct.model.Document;
import barcodeproduct.service.BarCodeProduct.model.ProductUser;
import barcodeproduct.service.BarCodeProduct.model.Shop;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductUserService {

    public ProductUser save(ProductUser productUser);
    public ProductUser findOne(Long id);
    public void delete(ProductUser productUser);
    public ProductUser update(ProductUser old, ProductUser productUser);
    public Document insertDocument(Document document);
    public Document findOneDocument(Long id);
    public Shop saveShop(Shop shop, ProductUser productUser);


    public Document updateDocument(Document documentOld, Document document);

    void deleteDocument(Document documentOld);
}
