package barcodeproduct.service.BarCodeProduct.service;

import barcodeproduct.service.BarCodeProduct.model.Compagny;
import barcodeproduct.service.BarCodeProduct.query.CompagnyQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompagnyServiceImpl implements CompagnyService {

    @Autowired
    private CompagnyQuery compagnyQuery;

    @Override
    public Compagny findOne(String name) {
        return compagnyQuery.findOne(name);
    }

    @Override
    public List<Compagny> findAll() {
        return (List<Compagny>) compagnyQuery.findAll();
    }

    @Override
    public Compagny save(Compagny compagny) {
        return compagnyQuery.save(compagny);
    }
}
