package com.example.demo.controller;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/categories") @RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping public ResponseEntity<Category> create(@RequestBody Category category) { return ResponseEntity.ok(categoryService.createCategory(category)); }
    @GetMapping public ResponseEntity<?> getAll() { return ResponseEntity.ok(categoryService.getAllCategories()); }
    @GetMapping("/{id}") public ResponseEntity<Category> getOne(@PathVariable Long id) { return ResponseEntity.ok(categoryService.getCategory(id)); }
}