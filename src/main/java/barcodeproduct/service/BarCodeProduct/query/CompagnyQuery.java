package barcodeproduct.service.BarCodeProduct.query;

import barcodeproduct.service.BarCodeProduct.model.Compagny;
import barcodeproduct.service.BarCodeProduct.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface CompagnyQuery extends CrudRepository<Compagny, String> {
}
