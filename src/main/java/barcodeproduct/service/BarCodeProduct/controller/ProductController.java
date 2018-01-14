package barcodeproduct.service.BarCodeProduct.controller;

import barcodeproduct.service.BarCodeProduct.model.Compagny;
import barcodeproduct.service.BarCodeProduct.service.CompagnyService;
import barcodeproduct.service.BarCodeProduct.service.CompagnyServiceImpl;
import barcodeproduct.service.BarCodeProduct.service.ProductServiceImpl;
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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProductController {

    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductLayer productLayer = new ProductLayer();

    @Autowired
    private ProductService productService = new ProductServiceImpl();

    @Autowired
    private CompagnyService compagnyService = new CompagnyServiceImpl();

    /**
     * Find product with gtin number
     * If we didn't find product on our database, we ask at ProductLayer's API to find it
     * @param gtin EAN
     * @return Product
     */
    @RequestMapping(value = "/products/{gtin}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductByGtin(@PathVariable String gtin){
        logger.info("Get product "+gtin);

        /**
         * Search a product in database
         */
        Product product = productService.getProductByGtin(Long.parseLong(gtin));
        JsonObject jsonReturn = new JsonObject();
        String name = "";
        String compagnyName = "";

        if(product != null) {
            name = product.getName();
            compagnyName = product.getCompagny().getName();
        }else{
            /**
             * if not exist in database, search it in API
             */
            JsonObject jsonObject = productLayer.getProductWithGtin(gtin);
            if(jsonObject != null){
                /**
                 * get information of product
                 */
                name = jsonObject.get("pl-prod-name").getAsString();
                compagnyName = jsonObject.get("pl-brand-name").getAsString();
                logger.info("product "+name);
                logger.info("form "+compagnyName);
                /**
                 * Create a new object product
                 */
                product = new Product();
                product.setId(Long.parseLong(gtin));
                product.setName(name);
                Compagny compagny = new Compagny();
                compagny.setName(compagnyName);
                product.setCompagny(compagny);
            }
        }
        /**
         * if a product not exist in database and API, send a NO_CONTENT
         */
        if(name.equals("")&&compagnyName.equals("")){
            return new ResponseEntity<Object>(null, HttpStatus.NO_CONTENT);
        }
        /**
         * Send a product
         */
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    /**
     * Create a product
     * @param product
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity insertProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder){
        logger.info("Create product");
        /**
         * check if already exist
         */
        Product productCheck = productService.getProductByGtin(product.getId());
        if(productCheck != null){
            logger.error("product exist");
            return new ResponseEntity<String>( "Unable to create product "+ product.getId() +" already exist.", HttpStatus.CONFLICT);
        }
        product = productService.insert(product);
        return new ResponseEntity<String>( "Create product "+ product.getId() +".", HttpStatus.CREATED);
    }

    /**
     * Update a product
     * @param product
     * @param gtin id of product
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/products/{gtin}", method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@RequestBody Product product, @PathVariable String gtin, UriComponentsBuilder ucBuilder){
        logger.info("update product");
        /**
         * check if already exist
         */
        Product productCheck = productService.getProductByGtin(Long.parseLong(gtin));
        if(productCheck == null){
            logger.error("product not exists");
            return new ResponseEntity<String>( "Unable to update product "+ product.getId() +" not exist.", HttpStatus.NO_CONTENT);
        }
        /**
         * update product
         */
        Product updated = productService.update(product, productCheck);
        if(updated == null){
            //unable to update
            logger.error("product not updated");
            return new ResponseEntity<String>( "Unable to update product "+ product.getId() +".", HttpStatus.BAD_REQUEST);
        }
        logger.info("product updated");
        return new ResponseEntity<Product>( updated, HttpStatus.OK);
    }

    /**
     * Get all compagnies
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/compagnies", method = RequestMethod.GET)
    public ResponseEntity getAllCompagnies(UriComponentsBuilder ucBuilder){
        logger.info("get all compagnies");
        List<Compagny> compagnyList = compagnyService.findAll();
        if(compagnyList == null){
            logger.error("compagnies not exists");
            return new ResponseEntity<String>( "Unable to find compagny.", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Compagny>>(compagnyList, HttpStatus.OK);
    }

    /**
     * Get one compagny
     * @param name
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/compagnies/{name}", method = RequestMethod.GET)
    public ResponseEntity getOneCompagny(@PathVariable String name ,UriComponentsBuilder ucBuilder){
        logger.info("get one compagny");
        /**
         * Get compagny in db
         */
        Compagny compagny = compagnyService.findOne(name);
        if(compagny == null){
            logger.error("compagnies not exists");
            return new ResponseEntity<String>( "Unable to find compagny.", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Compagny>(compagny, HttpStatus.OK);
    }

    /**
     * Create a compagny
     * @param name
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/compagnies", method = RequestMethod.POST)
    public ResponseEntity createCompagny(@RequestBody String name, UriComponentsBuilder ucBuilder){
        logger.info("create compagny");
        /**
         * if the name of compagny is null send a NO_CONTENT
         */
        if(name == null || name.isEmpty()){
            logger.error("not name for compagny");
            return new ResponseEntity<String>( "Unable to find compagny's name.", HttpStatus.NO_CONTENT);
        }
        Compagny compagny = new Compagny();
        compagny.setName(name);
        /**
         * insert compagny in db
         */
        Compagny compagnyCreated = compagnyService.save(compagny);
        return new ResponseEntity<Compagny>(compagnyCreated, HttpStatus.CREATED);
    }

}
