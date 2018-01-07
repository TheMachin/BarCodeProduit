package barcodeproduct.service.BarCodeProduct.query;

import barcodeproduct.service.BarCodeProduct.model.ProductUser;
import org.springframework.data.repository.CrudRepository;

public interface ProductUserQuery extends CrudRepository<ProductUser, Long> {
}
