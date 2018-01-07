package barcodeproduct.service.BarCodeProduct.query;

import barcodeproduct.service.BarCodeProduct.model.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentQuery extends CrudRepository<Document, Long>{
}
