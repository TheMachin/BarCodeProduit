package barcodeproduct.service.BarCodeProduct.controller;

import barcodeproduct.service.BarCodeProduct.model.Document;
import barcodeproduct.service.BarCodeProduct.model.ProductUser;
import barcodeproduct.service.BarCodeProduct.model.Shop;
import barcodeproduct.service.BarCodeProduct.model.User;
import barcodeproduct.service.BarCodeProduct.service.ProductUserService;
import barcodeproduct.service.BarCodeProduct.service.ProductUserServiceImpl;
import barcodeproduct.service.BarCodeProduct.service.UserService;
import barcodeproduct.service.BarCodeProduct.service.UserServiceImpl;
import com.google.gson.Gson;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.io.*;
import java.sql.Blob;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService = new UserServiceImpl();

    @Autowired
    private ProductUserService productUserService = new ProductUserServiceImpl();

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);


    /**
     * Get all user
     * @return lisrt of user or no content if the list is empty
     */
    @RequestMapping(value = "/users/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUser(){
        logger.info("get all user");
        List<User> users = userService.findAll();
        if(users == null){
            logger.error("list user is null");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        logger.info("send list user");
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    /**
     * Get one user
     * @param username
     * @return a user
     */
    @RequestMapping(value = "/users/{username}/", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable String username){
        logger.info("get user "+username);
        User user = userService.getUser(username);
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        logger.info("send user");
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * Create user
     * @requestParam email
     * @requestParam password
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestParam(name = "email", required = true) String username,
                                        @RequestParam(name = "password", required = true) String password,
                                        UriComponentsBuilder ucBuilder){
        logger.info("Creating user ");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        /**
         * Check if user exist
         */
        if(userService.getUser(user.getUsername()) != null){
            logger.error("user exist");
            return new ResponseEntity<String>( "Unable to create user "+user.getUsername() +" already exist.", HttpStatus.CONFLICT);
        }
        /**
         * if the password is null or empty
         */
        if(user.getPassword() == null || user.getPassword().isEmpty()){
            logger.error("user password missing");
            return new ResponseEntity<String>( "Unable to create user "+user.getUsername() +" password is missing.", HttpStatus.BAD_REQUEST);
        }
        /**
         * enable user
         * save user
         */
        user.setEnable(true);
        userService.save(user);
        logger.info("user created");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/users/{username}").buildAndExpand(user.getUsername()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    /**
     * Update only password of user
     * @requestParam email
     * @requestParam password
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestParam(name = "email", required = true) String username,
                                        @RequestParam(name = "password", required = true) String password,
                                        UriComponentsBuilder ucBuilder){
        logger.info("Update User");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        /**
         * search user
         */
        User userActual = userService.getUser(user.getUsername());
        /**
         * return a no content if user not found
         */
        if(userActual == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NO_CONTENT);
        }
        /**
         * change password and save it
         */
        userActual.setPassword(user.getPassword());
        userService.save(userActual);
        if(user.getPassword().equals(userActual.getPassword())){
            logger.info("user updated");
            return new ResponseEntity<String>("User updated", HttpStatus.OK);
        }
        logger.error("user not updated");
        return new ResponseEntity<String>("Unable to update user", HttpStatus.BAD_REQUEST);
    }

    /**
     * Get all product of user
     * @return
     */
    @RequestMapping(value = "/users/products", method = RequestMethod.GET)
    public ResponseEntity getAllProductOfUser(){
        logger.info("get user's products");
        User user = userService.getUser(getCurrentNameUser());
        logger.info(user.getUsername());
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Set<ProductUser>>(user.getProductUsers(), HttpStatus.OK);
    }


    /**
     * Get a product by id
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOneProductOfUser(@PathVariable String id){
        logger.info("get one user's product");
        User user = userService.getUser(getCurrentNameUser());
        logger.info(user.getUsername());
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        ProductUser productUser = productUserService.findOne(Long.parseLong(id));
        if(productUser != null) {
            return new ResponseEntity<ProductUser>(productUser, HttpStatus.OK);
        }
        return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/users/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeOneProductOfUser(@PathVariable String id){
        logger.info("delete one user's product");
        User user = userService.getUser(getCurrentNameUser());
        logger.info(user.getUsername());
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        ProductUser productUser = productUserService.findOne(Long.parseLong(id));

        if(productUser != null) {
            productUserService.delete(productUser);
            return new ResponseEntity<String>("delete", HttpStatus.OK);
        }
        return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
    }

    /**
     *
     * @param productUser
     * @return
     */
    @RequestMapping(value = "/users/products", method = RequestMethod.POST)
    public ResponseEntity<?> createUserProduct(@RequestBody ProductUser productUser){
        logger.info("insert a product to user");
        User user = userService.getUser(getCurrentNameUser());
        logger.info(user.getUsername());
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }

        if(productUser.getGtin() == null){
            logger.error("gtin not found");
            return new ResponseEntity<String>( "Product object not found.", HttpStatus.NOT_FOUND);
        }

        productUser.setUser(user);
        if(productUser.getPurchaseLocation() == null){
            Shop shop = new Shop();
            productUser.setPurchaseLocation(shop);
        }


        ProductUser productuserCreated = productUserService.save(productUser);
        return new ResponseEntity<ProductUser>(productuserCreated, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserProduct(@RequestBody ProductUser productUser, @PathVariable String id){
        logger.info("update a product to user");
        User user = userService.getUser(getCurrentNameUser());
        logger.info(user.getUsername());
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        ProductUser old = productUserService.findOne(Long.parseLong(id));
        if(old == null){
            logger.error("product user not found");
            return new ResponseEntity<String>( "Id not found.", HttpStatus.NOT_FOUND);
        }

        /**
         * update information in existing productUser
         */
        old.setPrice(productUser.getPrice());
        old.setName(productUser.getName());
        old.setDatePurchase(productUser.getDatePurchase());
        old.setDateEndConstructorWarranty(productUser.getDateEndConstructorWarranty());
        old.setDateEndCommercialWarranty(productUser.getDateEndCommercialWarranty());

        /**
         * //check if shop exist to add it in update
         */
        if(productUser.getPurchaseLocation() != null){
            /**
             * return a no content if the shop have not an id
             */
            if(productUser.getPurchaseLocation().getId() == null){
                return new ResponseEntity<String>("id shop is null", HttpStatus.NO_CONTENT);
            }
            old.setPurchaseLocation(productUser.getPurchaseLocation());
        }
        ProductUser updated = productUserService.save(old);
        if(updated == null){
            return new ResponseEntity<String>("Unable to update", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<ProductUser>(old, HttpStatus.OK);
    }

    /**
     * get all document's product by id
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/products/{id}/documents", method = RequestMethod.GET)
    public ResponseEntity<?> getDocumentsProductOfUser(@PathVariable String id){
        logger.info("get all document's product of user");
        User user = userService.getUser(getCurrentNameUser());
        logger.info(user.getUsername());
        if(user == null){
            logger.error("product not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        ProductUser productUser = productUserService.findOne(Long.parseLong(id));
        if(productUser != null) {
            return new ResponseEntity<Set<Document>>(productUser.getDocuments(), HttpStatus.OK);
        }

        return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
    }

    /**
     * Get one document of one product
     * @param id
     * @param idDoc
     * @return
     */
    @RequestMapping(value = "/users/products/{id}/documents/{idDoc}", method = RequestMethod.GET)
    public ResponseEntity<?> getOneDocumentsProductOfUser(@PathVariable String id, @PathVariable String idDoc){
        logger.info("get one document's product of user");
        User user = userService.getUser(getCurrentNameUser());
        logger.info(user.getUsername());
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        ProductUser productUser = productUserService.findOne(Long.parseLong(id));
        if(productUser != null) {
            for(Document document : productUser.getDocuments()){
                if(document.getId().equals(Long.parseLong(idDoc))){
                    return new ResponseEntity<Document>(document, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
    }

    /**
     * create a new document
     * @param id of product to user
     * @param fileName
     * @param file
     * @return 201 if the creation is made
     */
    @RequestMapping(value = "/users/products/{id}/documents", method = RequestMethod.POST)
    public ResponseEntity<?> createDocumentsProductOfUser(@PathVariable String id, @RequestParam String fileName, @RequestParam MultipartFile file){
        logger.info("create document's productUser");
        /**
         * get user
         */
        User user = userService.getUser(getCurrentNameUser());
        logger.info(user.getUsername());
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        /**
         * get product
         */
        ProductUser productUser = productUserService.findOne(Long.parseLong(id));
        if(productUser != null) {
            Document document = new Document();
            document.setProductUser(productUser);
            /**
             * check if file exist
             */
            if(file == null){
                logger.error("file is not exist to create a new document");
                return new ResponseEntity<String>("File not found", HttpStatus.NO_CONTENT);
            }
            /**
             * convert file to byte and create a new document
             */
            byte[] fileByte = getByteFromMultipartFile(file);

            document.setFileName(fileName);
            document.setFile(fileByte);

            Document documentCreated = productUserService.insertDocument(document);
            if(documentCreated != null){
                return new ResponseEntity<Document>(documentCreated, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<String>("Fail to insert document", HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<String>("Id not found", HttpStatus.NO_CONTENT);
    }

    /**
     * update a document
     * @param id of product of user
     * @param idDoc of document
     * @param file
     * @param fileName
     * @return
     */
    @RequestMapping(value = "/users/products/{id}/documents/{idDoc}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDocumentsProductOfUser(@PathVariable String id,
                                                          @PathVariable String idDoc,

                                                          @RequestParam(value = "file") MultipartFile file,
                                                          @RequestParam(value = "fileName") String fileName)
    {
        logger.info("update document's productUser");
        User user = userService.getUser(getCurrentNameUser());
        logger.info(user.getUsername());
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        Document documentOld = productUserService.findOneDocument(Long.parseLong(idDoc));
        if(documentOld == null){
            logger.error("document not found");
            return new ResponseEntity<String>( "document not found.", HttpStatus.NOT_FOUND);
        }
        if(!documentOld.getProductUser().getUser().getUsername().equals(user.getUsername())){
            return new ResponseEntity<String>( "not user's document.", HttpStatus.FORBIDDEN);
        }
        Document document = new Document();
        document.setProductUser(documentOld.getProductUser());

        /**
         * convert file to byte if exist
         */
        byte[] fileByte = null;
        if(file != null){
            fileByte = getByteFromMultipartFile(file);
        }


        document.setFileName(fileName);
        document.setFile(fileByte);

        Document documentUpdated = productUserService.updateDocument(documentOld, document);
        if(documentUpdated != null){
            return new ResponseEntity<Document>(documentUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Unable to update document", HttpStatus.NO_CONTENT);
    }

    /**
     * delete document
     * @param id product of user
     * @param idDoc document
     * @return 200 if the delete is a success
     */
    @RequestMapping(value = "/users/products/{id}/documents/{idDoc}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDocumentsProductOfUser(@PathVariable String id, @PathVariable String idDoc){
        logger.info("delete document's productUser");
        User user = userService.getUser(getCurrentNameUser());
        logger.info(user.getUsername());
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        /**
         * find a document
         */
        Document documentOld = productUserService.findOneDocument(Long.parseLong(idDoc));
        if(documentOld == null){
            logger.error("document not found");
            return new ResponseEntity<String>( "document not found.", HttpStatus.NOT_FOUND);
        }

        if(documentOld.getProductUser().getId().equals(id)){
            productUserService.deleteDocument(documentOld);
            return new ResponseEntity<String>("", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Not document of product user",HttpStatus.CONFLICT);
    }

    /**
     * Download a document
     * @param id
     * @param idDoc
     * @return
     */
    @RequestMapping(value = "/users/products/{id}/documents/{idDoc}/download", method = RequestMethod.GET)
    public ResponseEntity<?> downloadFile(@PathVariable String id, @PathVariable String idDoc){
        logger.info("download file");
        User user = userService.getUser(getCurrentNameUser());
        logger.info(user.getUsername());
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        ProductUser productUser = productUserService.findOne(Long.parseLong(id));
        if(productUser != null) {
            /**
             * search a document in the list
             */
            for(Document document : productUser.getDocuments()){
                /**
                 * document find and prepare to download
                 */
                if(document.getId().equals(Long.parseLong(idDoc))){

                    ByteArrayResource resource = new ByteArrayResource(document.getFile());
                    HttpHeaders headers = new HttpHeaders(); headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+document.getFileName());

                    return ResponseEntity.ok()
                            .header(String.valueOf(headers))
                            .contentLength(document.getFile().length)
                            .contentType(MediaType.parseMediaType("application/octet-stream"))
                            .body(resource);
                }
            }
        }
        return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
    }

    /**
     * get a location shop of product where the user bought the product
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/products/{id}/shops", method = RequestMethod.GET)
    public ResponseEntity<?> getLocatedShopProductOfUser(@PathVariable String id){
        logger.info("get a shop of productUser");
        User user = userService.getUser(getCurrentNameUser());
        logger.info(user.getUsername());
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        /**
         * the productUser is attached at one location shop
         */
        ProductUser productUser = productUserService.findOne(Long.parseLong(id));
        if(productUser != null && productUser.getPurchaseLocation() != null) {
            return new ResponseEntity<Shop>(productUser.getPurchaseLocation(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
    }

    /**
     * create a shop to attache at a productuser
     * @param id
     * @param shop
     * @return
     */
    @RequestMapping(value = "/users/products/{id}/shops", method = RequestMethod.POST)
    public ResponseEntity<?> createLocatedShopProductOfUser(@PathVariable String id, @RequestBody Shop shop){
        logger.info("create a located shop of productUser");
        User user = userService.getUser(getCurrentNameUser());
        logger.info(user.getUsername());
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        ProductUser productUser = productUserService.findOne(Long.parseLong(id));
        if(productUser == null){
            return new ResponseEntity<String>("Id product not found", HttpStatus.NOT_FOUND);
        }
        /**
         * if the productuser have an existing location shop
         */
        if(productUser.getPurchaseLocation() != null){
            return new ResponseEntity<String>("Location shop is alreadyExist", HttpStatus.CONFLICT);
        }
        if(productUser != null && shop != null) {
            shop = productUserService.saveShop(shop, productUser);
            return new ResponseEntity<Shop>(shop, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>( "Id not found.", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * update a shop
     * @param id
     * @param idShop
     * @param shop
     * @return
     */
    @RequestMapping(value = "/users/products/{id}/shops/{idShop}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateLocatedShopProductOfUser(@PathVariable String id, @PathVariable String idShop, @RequestBody Shop shop){
        logger.info("update a located shop of productUser");
        User user = userService.getUser(getCurrentNameUser());
        logger.info(user.getUsername());
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity<String>( "User not found.", HttpStatus.NOT_FOUND);
        }
        ProductUser productUser = productUserService.findOne(Long.parseLong(id));
        if(productUser != null && shop != null && (shop.getId() == productUser.getPurchaseLocation().getId())) {
            shop = productUserService.saveShop(shop, productUser);
            return new ResponseEntity<Shop>(shop, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>( "Id not found.", HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Get a username of session
     * @return
     */
    private String getCurrentNameUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if(principal instanceof String){
            return principal.toString();
        }
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
        return user.getUsername();
    }

    /**
     * convert file to bytes
     * @param file
     * @return
     */
    private byte[] getByteFromMultipartFile(MultipartFile file){
        try {
            return file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("convert multipartfile to byte : "+e.getMessage());
        }
        return null;
    }

}
