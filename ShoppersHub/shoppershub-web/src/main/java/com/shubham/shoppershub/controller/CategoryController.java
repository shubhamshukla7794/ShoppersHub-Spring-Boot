package com.shubham.shoppershub.controller;

import com.shubham.shoppershub.entity.Category;
import com.shubham.shoppershub.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping({"","/"})
    public String categoryPage(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        return "category/index";
    }

    @GetMapping("/add")
    public String newCategory(Model model) {
        model.addAttribute("category", new Category());
        return "category/categoryForm";
    }

    @PostMapping("/save")
    public String saveOrUpdateCategory(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/{categoryId}/update")
    public String updateCategory(@PathVariable String categoryId, Model model) {
        Category category = categoryService.findCategoryById(Long.valueOf(categoryId));
        model.addAttribute("category", category);
        return "category/categoryForm";
    }

    @GetMapping("/{categoryId}/delete")
    public String deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategoryById(Long.valueOf(categoryId));
        return "redirect:/admin/category";
    }
}
