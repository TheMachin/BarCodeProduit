package barcodeproduct.service.BarCodeProduct.service;

import barcodeproduct.service.BarCodeProduct.controller.UserController;
import barcodeproduct.service.BarCodeProduct.model.Compagny;
import barcodeproduct.service.BarCodeProduct.model.Product;
import barcodeproduct.service.BarCodeProduct.query.CompagnyQuery;
import barcodeproduct.service.BarCodeProduct.query.ProductQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ProductQuery productQuery;

    @Autowired
    private CompagnyQuery compagnyQuery;



    @Override
    public Product getProductByGtin(Long gtin) {
        return productQuery.findOne(gtin);
    }

    @Override
    public Product insert(Product product) {
        if(product == null){
            logger.error("product null");
            return null;
        }
        Compagny compagny = compagnyQuery.save(product.getCompagny());
        if(compagny == null){
            logger.error("compagny null");
            return null;
        }
        product.setCompagny(compagny);
        return save(product);
    }

    @Override
    public Product save(Product product) {
        return productQuery.save(product);
    }

    /**
     * Update product
     * Check if we must update brand
     * @param product to update
     * @param old product
     * @return product updated
     */
    @Override
    public Product update(Product product, Product old) {
        if(product.getCompagny() != null){
            if(!product.getCompagny().equals(old.getCompagny())){
                Compagny compagny = compagnyQuery.save(product.getCompagny());
                old.setCompagny(compagny);
            }
        }

        old.setName(product.getName());

        return productQuery.save(old);
    }
}
