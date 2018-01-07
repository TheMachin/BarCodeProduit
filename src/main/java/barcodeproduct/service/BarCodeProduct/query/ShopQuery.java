package barcodeproduct.service.BarCodeProduct.query;

import barcodeproduct.service.BarCodeProduct.model.Shop;
import org.springframework.data.repository.CrudRepository;

public interface ShopQuery extends CrudRepository<Shop,Long>{

}
