package barcodeproduct.service.BarCodeProduct.service;

import barcodeproduct.service.BarCodeProduct.model.User;
import barcodeproduct.service.BarCodeProduct.query.ProductQuery;
import barcodeproduct.service.BarCodeProduct.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ProductQuery productQuery;

    @Autowired
    private UserQuery userQuery;

    @Override
    public User getUser(String username) {
        return userQuery.findOne(username);
    }

    @Override
    public void save(User user) {
        user.addUserRole();
        userQuery.save(user);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userQuery.findAll();
    }
}
