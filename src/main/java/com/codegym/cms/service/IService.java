package com.codegym.cms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<T> {

    Iterable<T> findAll();

    Page<T> findAll(Pageable pageable);

    T findById(Long id);

    void save(T t);

    void remove(Long id);

}
