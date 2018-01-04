package barcodeproduct.service.BarCodeProduct.query;

import barcodeproduct.service.BarCodeProduct.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductQuery extends CrudRepository<Product, Long> {
}
