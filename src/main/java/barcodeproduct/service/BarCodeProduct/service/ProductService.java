package barcodeproduct.service.BarCodeProduct.service;

import barcodeproduct.service.BarCodeProduct.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    public Product getProductByGtin(Long gtin);
}
