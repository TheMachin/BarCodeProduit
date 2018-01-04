package barcodeproduct.service.BarCodeProduct.controller;

import barcodeproduct.service.BarCodeProduct.logger.ConsoleLogger;
import barcodeproduct.service.BarCodeProduct.logger.Logger;
import barcodeproduct.service.BarCodeProduct.model.User;
import barcodeproduct.service.BarCodeProduct.service.UserService;
import barcodeproduct.service.BarCodeProduct.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService = new UserServiceImpl();

    public static final Logger logger = new ConsoleLogger();


    /**
     * Get all user
     * @return
     */
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUser(){
        logger.log("get all user");
        List<User> users = userService.findAll();
        if(users == null){
            logger.log("list user is null");
            return new ResponseEntity( "User not found.", HttpStatus.NOT_FOUND);
        }
        logger.log("send list user");
        return new ResponseEntity(users, HttpStatus.OK);
    }

    /**
     * Get one user
     * @param username
     * @return
     */
    @RequestMapping(value = "/user/{username}/", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable String username){
        logger.log("get user "+username);
        User user = userService.getUser(username);
        if(user == null){
            return new ResponseEntity( "User not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    /**
     * Create user
     * @param user (username, password)
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
        logger.log("Creating user ");
        //user exist
        if(userService.getUser(user.getUsername()) != null){
            logger.log("user exist");
            return new ResponseEntity( "Unable to create user "+user.getUsername() +" already exist.", HttpStatus.CONFLICT);
        }
        if(user.getPassword() == null || user.getPassword().isEmpty()){
            logger.log("user password missing");
            return new ResponseEntity( "Unable to create user "+user.getUsername() +" password is missing.", HttpStatus.NOT_ACCEPTABLE);
        }
        user.setEnable(true);
        userService.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/user/{username}").buildAndExpand(user.getUsername()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

}
