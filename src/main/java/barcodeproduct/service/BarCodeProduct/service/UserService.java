package barcodeproduct.service.BarCodeProduct.service;

import barcodeproduct.service.BarCodeProduct.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User getUser(String username);
    public void save(User user);
    public List<User> findAll();

}
