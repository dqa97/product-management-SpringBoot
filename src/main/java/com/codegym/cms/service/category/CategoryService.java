package com.codegym.cms.service.category;

import com.codegym.cms.model.Category;
import com.codegym.cms.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepo.findAll(pageable);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepo.findById(id).get();
    }

    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepo.deleteById(id);
    }


}
