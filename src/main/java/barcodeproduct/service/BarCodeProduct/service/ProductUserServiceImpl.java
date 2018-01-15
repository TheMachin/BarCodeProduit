package barcodeproduct.service.BarCodeProduct.service;

import barcodeproduct.service.BarCodeProduct.model.Document;
import barcodeproduct.service.BarCodeProduct.model.Product;
import barcodeproduct.service.BarCodeProduct.model.ProductUser;
import barcodeproduct.service.BarCodeProduct.model.Shop;
import barcodeproduct.service.BarCodeProduct.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductUserServiceImpl implements ProductUserService {

    @Autowired
    ProductUserQuery productUserQuery;

    @Autowired
    ProductQuery productQuery;

    @Autowired
    ProductService productService = new ProductServiceImpl();

    @Autowired
    DocumentQuery documentQuery;

    @Autowired
    ShopQuery shopQuery;


    /**
     * Save a productUser
     * Before it, save the product and the shop
     * @param productUser
     * @return
     */
    @Override
    public ProductUser save(ProductUser productUser) {

        if(productUser == null){
            return null;
        }

        if(productUser.getGtin() == null){
            return null;
        }

        if(productUser.getName() == null || productUser.getName().isEmpty()){
            return null;
        }

        /**
         * get a product
         * if exist, save a product else create a new product with the name of productUser
         */
        Long gtin = productUser.getGtin().getId();

        Product product = productQuery.findOne(gtin);

        if(product == null){
            product = productUser.getGtin();
            if(product.getName() == null || product.getName().isEmpty()){
                product.setName(productUser.getName());
            }
            product = productService.insert(product);
        }
        productUser.setGtin(product);

        /**
         * if the shop is not null, save it
         */
        if(productUser.getPurchaseLocation() != null){
            Shop shop = saveShop(productUser.getPurchaseLocation(), productUser);
            //if fail to save, put shop at null
            if(shop == null){
                productUser.setPurchaseLocation(null);
            }
        }

        return productUserQuery.save(productUser);
    }

    @Override
    public ProductUser findOne(Long id) {
        return productUserQuery.findOne(id);
    }

    @Override
    public void delete(ProductUser productUser) {
        for(Document document : productUser.getDocuments()){
            document.setProductUser(null);
            documentQuery.delete(document.getId());
        }
        productUserQuery.delete(productUser.getId());
    }

    @Override
    public ProductUser update(ProductUser old, ProductUser productUser) {

        if(productUser.getId() == null || productUser.getId() != old.getId()){
            return null;
        }

        old.setDateEndCommercialWarranty(productUser.getDateEndCommercialWarranty());
        old.setDateEndConstructorWarranty(productUser.getDateEndCommercialWarranty());
        old.setDatePurchase(productUser.getDatePurchase());
        old.setName(productUser.getName());
        old.setPrice(productUser.getPrice());

        return productUserQuery.save(old);
    }

    @Override
    public Document insertDocument(Document document) {

        if(document.getProductUser() == null){
            return null;
        }

        return documentQuery.save(document);
    }

    @Override
    public Document findOneDocument(Long id) {
        return documentQuery.findOne(id);
    }

    @Override
    public Shop saveShop(Shop shop, ProductUser productUser) {

        if(shop == null || productUser == null){
            return null;
        }

        productUser.setPurchaseLocation(shop);
        shop = shopQuery.save(shop);
        return shop;
    }

    @Override
    public Document updateDocument(Document documentOld, Document document) {

        if(document.getFileName() != null || !document.getFileName().isEmpty()){
            documentOld.setFileName(document.getFileName());
        }

        if(document.getFile() != null){
            documentOld.setFile(document.getFile());
        }

        return documentQuery.save(documentOld);
    }

    @Override
    public void deleteDocument(Document documentOld) {
        documentQuery.delete(documentOld.getId());
    }
}
