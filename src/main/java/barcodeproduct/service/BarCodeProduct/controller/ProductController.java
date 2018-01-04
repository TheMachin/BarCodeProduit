package barcodeproduct.service.BarCodeProduct.controller;

import barcodeproduct.service.BarCodeProduct.api.ProductLayer;
import barcodeproduct.service.BarCodeProduct.logger.ConsoleLogger;
import barcodeproduct.service.BarCodeProduct.logger.Logger;
import barcodeproduct.service.BarCodeProduct.model.Product;
import barcodeproduct.service.BarCodeProduct.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ProductController {

    public static final Logger logger = new ConsoleLogger();

    private ProductLayer productLayer = new ProductLayer();

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products/{gtin}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductByGtin(@PathVariable String gtin){
        logger.log("Get product "+gtin);

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
                logger.log("product "+name);
                logger.log("form "+compagnyName);
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

    @RequestMapping(value = "/products/", method = RequestMethod.POST)
    public void insertProduct(){

    }

}
