package barcodeproduct.service.BarCodeProduct.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import barcodeproduct.service.BarCodeProduct.api.ProductLayer;
import barcodeproduct.service.BarCodeProduct.model.Product;
import barcodeproduct.service.BarCodeProduct.service.ProductService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/v1")
public class ProductController {

    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductLayer productLayer = new ProductLayer();

    @Autowired
    private ProductService productService;

    /**
     * Find product with gtin number
     * If we didn't find product on our database, we ask at ProductLayer's API to find it
     * @param gtin EAN
     * @return Product
     */
    @RequestMapping(value = "/products/{gtin}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductByGtin(@PathVariable String gtin){
        logger.info("Get product "+gtin);

        //on cherche le produit dans notre bdd
        Product product = productService.getProductByGtin(Long.parseLong(gtin));
        JsonObject jsonReturn = new JsonObject();
        String name = "";
        String compagnyName = "";
        //si un produit n'existe pas dans notre bdd, on va chercher avec un api
        if(product != null) {
            name = product.getName();
            compagnyName = product.getCompagny().getName();
        }else{
            JsonObject jsonObject = productLayer.getProductWithGtin(gtin);
            if(jsonObject != null){
                //Si un produit existe depuis la bdd de l'api, on récupere les infos
                name = jsonObject.get("pl-prod-name").getAsString();
                compagnyName = jsonObject.get("pl-brand-name").getAsString();
                logger.info("product "+name);
                logger.info("form "+compagnyName);
            }
        }
        //si on a aucune info on retourne un 204 NO CONTENT
        if(name.equals("")&&compagnyName.equals("")){
            return new ResponseEntity<Object>(null, HttpStatus.NO_CONTENT);
        }
        jsonReturn.addProperty("gtin",gtin);
        jsonReturn.addProperty("name",name);
        jsonReturn.addProperty("compagny",compagnyName);

        return new ResponseEntity<String>(jsonReturn.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity insertProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder){
        logger.info("Create product");
        //check if already exist
        Product productCheck = productService.getProductByGtin(product.getId());
        if(productCheck != null){
            logger.error("product exist");
            return new ResponseEntity( "Unable to create product "+ product.getId() +" already exist.", HttpStatus.CONFLICT);
        }
        product = productService.insert(product);
        return new ResponseEntity( "Create product "+ product.getId() +".", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder){
        logger.info("update product");
        //check if already exist
        Product productCheck = productService.getProductByGtin(product.getId());
        if(productCheck == null){
            logger.error("product not exists");
            return new ResponseEntity( "Unable to update product "+ product.getId() +" not exist.", HttpStatus.NO_CONTENT);
        }
        Product updated = productService.update(product, productCheck);
        if(updated == null){
            logger.error("product not updated");
            return new ResponseEntity( "Unable to update product "+ product.getId() +".", HttpStatus.BAD_REQUEST);
        }
        logger.info("product updated");
        return new ResponseEntity( "Update product "+ updated.getId() +".", HttpStatus.OK);
    }

}
