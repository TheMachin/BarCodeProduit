package barcodeproduct.service.BarCodeProduct.service;

import barcodeproduct.service.BarCodeProduct.model.Compagny;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompagnyService {

    public Compagny findOne(String name);
    public List<Compagny> findAll();
    public Compagny save(Compagny compagny);

}
