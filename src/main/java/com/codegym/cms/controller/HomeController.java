package com.codegym.cms.controller;

import com.codegym.cms.model.Category;
import com.codegym.cms.model.Product;
import com.codegym.cms.service.category.ICategoryService;
import com.codegym.cms.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/products")
public class HomeController {
    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IProductService iProductService;

    @ModelAttribute("listCategory")
    public Iterable<Category> showAll() {
        return iCategoryService.findAll();
    }

    @GetMapping("/")
    public ModelAndView showAllProducts(@PageableDefault(size = 5) Pageable pageable) {
        Iterable<Product> products = iProductService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("productList", products);
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Product product) {
        iProductService.save(product);
        ModelAndView modelAndView = new ModelAndView("create", "product", new Product());
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView showFormEdit(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = iProductService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView edit(@RequestParam Long id, @ModelAttribute Product product) {
        product.setId(id);
        iProductService.save(product);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam Long id) {
        iProductService.remove(id);
        return new ModelAndView("redirect:/products");
    }

    @PostMapping("/search")
    public ModelAndView searchByProductName(@RequestParam String name, @PageableDefault(size = 5) Pageable pageable) {
        name = "%" + name + "%";
        Page<Product> products = iProductService.findByProductName(name, pageable);
        if (products.getSize() == 0) return new ModelAndView("error.404");
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("productList", products);
        return modelAndView;
    }

    @PostMapping("/searchCate")
    public ModelAndView searchByCategoryName(@RequestParam Long id, @PageableDefault(size = 5) Pageable pageable) {
        Page<Product> products = iProductService.findByCategoryName(id, pageable);
        if (products.getSize() == 0) return new ModelAndView("error.404");
        else return new ModelAndView("list", "productList" , products);
    }
}
