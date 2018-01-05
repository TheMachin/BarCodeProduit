package barcodeproduct.service.BarCodeProduct.controller;

import barcodeproduct.service.BarCodeProduct.model.User;
import barcodeproduct.service.BarCodeProduct.service.UserService;
import barcodeproduct.service.BarCodeProduct.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService = new UserServiceImpl();

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);


    /**
     * Get all user
     * @return
     */
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUser(){
        logger.info("get all user");
        List<User> users = userService.findAll();
        if(users == null){
            logger.error("list user is null");
            return new ResponseEntity( "User not found.", HttpStatus.NOT_FOUND);
        }
        logger.info("send list user");
        return new ResponseEntity(users, HttpStatus.OK);
    }

    /**
     * Get one user
     * @param username
     * @return
     */
    @RequestMapping(value = "/user/{username}/", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable String username){
        logger.info("get user "+username);
        User user = userService.getUser(username);
        if(user == null){
            logger.error("user not found");
            return new ResponseEntity( "User not found.", HttpStatus.NOT_FOUND);
        }
        logger.info("send user");
        return new ResponseEntity(user, HttpStatus.OK);
    }

    /**
     * Create user
     * @param user (username, password)
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
        logger.info("Creating user ");
        //user exist
        if(userService.getUser(user.getUsername()) != null){
            logger.error("user exist");
            return new ResponseEntity( "Unable to create user "+user.getUsername() +" already exist.", HttpStatus.CONFLICT);
        }
        if(user.getPassword() == null || user.getPassword().isEmpty()){
            logger.error("user password missing");
            return new ResponseEntity( "Unable to create user "+user.getUsername() +" password is missing.", HttpStatus.BAD_REQUEST);
        }
        user.setEnable(true);
        userService.save(user);
        logger.info("user created");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/user/{username}").buildAndExpand(user.getUsername()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    /**
     * Update user password
     * @param user
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody User user,  UriComponentsBuilder ucBuilder){
        logger.info("Update User");
        //search user
        User userActual = userService.getUser(user.getUsername());
        //user not exist
        if(userActual == null){
            logger.error("user not found");
            return new ResponseEntity( "User not found.", HttpStatus.NOT_FOUND);
        }
        userActual.setPassword(user.getPassword());
        userService.save(userActual);
        if(user.getUsername().equals(userActual.getPassword())){
            logger.info("user updated");
            return new ResponseEntity<String>("User updated", HttpStatus.OK);
        }
        logger.error("user not updated");
        return new ResponseEntity<String>("Unable to update user", HttpStatus.BAD_REQUEST);
    }

}
