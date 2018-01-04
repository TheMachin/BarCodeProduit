package barcodeproduct.service.BarCodeProduct.query;

import barcodeproduct.service.BarCodeProduct.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserQuery extends CrudRepository<User,String> {



}
