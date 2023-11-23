package com.szw.chatblog.controller;

import com.szw.chatblog.base.Result;
import com.szw.chatblog.pojo.Category;
import com.szw.chatblog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


/**
 * 分类目录
 * @author ChatViewer
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    CategoryService categoryService;

    /**
     * 查询所有目录
     * 返回组织好的category树，即以列表形式，返回根节点的所有子节点
     * 如果Redis中存在，则直接从Redis中读取，不必再进行MySQL查询
     */
    @GetMapping("/allCategory")
    public Result<Object> allCategory() {
        return Result.success(categoryService.allCategory());
    }

    /**
     * 添加目录
     * 插入路径上的所有分类节点，约定一个分类下的孩子分类不重名
     */
    @PostMapping("/insertPath")
    public Result<Object> insertPath(@RequestBody List<String> categoryPath) {
        categoryService.insertPath(categoryPath);
        return Result.success();
    }

    /**
     * 更新目录
     * 根据父节点与分类名，插入分类，更新数据库并令缓存失效
     */
    @PostMapping("/insertAfter")
    public Result<Object> insertAfter(Long parentId, String categoryName) {
        Category category = categoryService.insertAfter(parentId, categoryName);
        return Result.success(category);
    }

    /**
     * 删除目录子目录
     * 删除category下的所有节点
     */
    @DeleteMapping("")
    public Result<Object> delete(Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return Result.success();
    }
}
