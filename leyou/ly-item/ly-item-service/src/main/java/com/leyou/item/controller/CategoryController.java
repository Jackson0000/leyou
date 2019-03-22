package com.leyou.item.controller;

import com.leyou.item.service.CategoryService;
import com.leyou.item.pojo.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoryListByParentId(
            @RequestParam(value = "pid", defaultValue = "0") Long pid) {
        try {
            if (pid == null || pid.longValue() < 0) {
                return ResponseEntity.badRequest().build();
            }
            List<Category> categoryList =  categoryService.queryCategoryListByParentId(1L);
            if (CollectionUtils.isEmpty(categoryList)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
