package com.codegym.cms.service.product;

import com.codegym.cms.model.Product;
import com.codegym.cms.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IService<Product> {
    Page<Product> findByProductName(String name, Pageable pageable);
    Page<Product> findByCategoryName(Long id, Pageable pageable);
}

