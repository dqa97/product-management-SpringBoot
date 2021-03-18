package com.codegym.cms.service.product;

import com.codegym.cms.model.Product;
import com.codegym.cms.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Override
    public Page<Product> findByProductName(String name, Pageable pageable) {
        return productRepo.findByProductName(name, pageable);
    }

    @Override
    public Page<Product> findByCategoryName(Long id, Pageable pageable) {
        return productRepo.findProductByCategoryName(id, pageable );
    }

    @Override
    public Product findById(Long id) {
        return productRepo.findById(id).get();
    }

    @Override
    public void save(Product product) {
        productRepo.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepo.deleteById(id);
    }
}
