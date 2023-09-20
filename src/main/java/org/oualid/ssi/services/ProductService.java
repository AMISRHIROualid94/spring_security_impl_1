package org.oualid.ssi.services;

import org.oualid.ssi.models.Product;
import org.oualid.ssi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> findAllProduct(){
        return productRepository.findAll();
    }
}
