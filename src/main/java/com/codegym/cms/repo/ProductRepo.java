package com.codegym.cms.repo;

import com.codegym.cms.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {

    @Query(value = "select * from product where product.name like ?", nativeQuery = true)
    Page<Product> findByProductName(String name, Pageable pageable);


    @Query(value = "select * from product where product.category_id =?", nativeQuery = true)
    Page<Product> findProductByCategoryName(Long id, Pageable pageable);
}
