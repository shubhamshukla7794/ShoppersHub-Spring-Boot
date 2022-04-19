package com.shubham.shoppershub.controller;

import com.shubham.shoppershub.entity.Category;
import com.shubham.shoppershub.service.CategoryService;
import com.shubham.shoppershub.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/{adminId}/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    public CategoryController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping({"","/"})
//    @GetMapping("/admin/{adminId}/category")
    public String categoryPage(@PathVariable Long adminId, Model model) {
        model.addAttribute("admin", userService.getUserById(adminId));
        model.addAttribute("categories", categoryService.findAllCategories());
        return "category/index";
    }

    @GetMapping("/add")
    public String newCategory(@PathVariable Long adminId, Model model) {
        model.addAttribute("admin", userService.getUserById(adminId));
        model.addAttribute("category", new Category());
        return "category/categoryForm";
    }

    @PostMapping("/save")
    public String saveOrUpdateCategory(@PathVariable Long adminId, @ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin/"+adminId+"/category";
    }

    @GetMapping("/{categoryId}/update")
    public String updateCategory(@PathVariable Long adminId, @PathVariable Long categoryId, Model model) {
        Category category = categoryService.findCategoryById(categoryId);
        model.addAttribute("admin", userService.getUserById(adminId));
        model.addAttribute("category", category);
        return "category/categoryForm";
    }

    @GetMapping("/{categoryId}/delete")
    public String deleteCategory(@PathVariable Long adminId, @PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return "redirect:/admin/"+adminId+"/category";
    }
}
