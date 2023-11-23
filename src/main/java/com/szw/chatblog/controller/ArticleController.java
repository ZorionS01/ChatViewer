package com.szw.chatblog.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.szw.chatblog.base.Result;
import com.szw.chatblog.pojo.Article;
import com.szw.chatblog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 文章接口
 * @author ChatViewer
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    ArticleService articleService;


    /**
     * 根据文章ID查询信息
     * 根据articleId，
     * 返回文章信息、作者信息、Redis点赞数、如果用户已登录，同时返回文章的点赞状态
     * @param articleId 文章Id
     * @return ArticleDto
     */
    @GetMapping("")
    public Result<Object> getWithAuthor(Long articleId) throws ExecutionException, InterruptedException {
        return Result.success(articleService.getWithAuthor(articleId));
    }


    /**
     * 查询分页信息与作者信息
     * @param page 页面id
     * @param pageSize 每页item数
     * @param categoryId 是否需要查询分类
     * @return 带author信息的当前页面列表
     */
    @GetMapping("/page")
    public Result<Object> pageWithAuthor(int page, int pageSize, Long categoryId) {
        return Result.success(articleService.pageWithAuthor(page, pageSize, categoryId));
    }


    /**
     * 上传文件文章图像录音
     * 上传文件，上传文章图像、录音音频均调用此接口
     * @param file File[]
     * @return 包含上传路径信息，详见ArticleServiceImpl
     */
    @PostMapping("/uploadFile")
    public Result<Object> uploadFile(MultipartFile file) {
        return articleService.uploadFile(file);
    }


    /**
     * 添加文章
     * @param map 属性基本与Article相同，但没有categoryId，而是articleCategoryList，形如["123", "456", "789"]，表示有层次的目录列表
     * @return 操作是否成功
     */
    @PostMapping("/add")
    public Result<Object> addArticle(@RequestBody Map<String, Object> map) {
        //将一个 Map 转换为一个 Java Bean 对象
        /**
         * map: 这是要转换为 Java Bean 的源 Map 对象，其中包含了字段名和对应的值。
         * beanClass: 这是目标 Java Bean 的类，即你要将 Map 转换为的对象类型。
         * isIgnoreCase: 这是一个布尔值，用于指定是否忽略字段名的大小写。如果设置为 true，则字段名的大小写将被忽略，否则将保留大小写敏感性。
         * copyOptions: 这是一个配置参数，用于控制复制的行为。CopyOptions.create() 创建了一个默认配置的 CopyOptions 对象，你可以根据需要自定义配置
         */
        Article article = BeanUtil.mapToBean(map, Article.class, false, CopyOptions.create());
        // 从分类列表取得分类Id
        Object tmp = map.get("articleCategoryList");
        if (!(tmp instanceof List)) {
            return Result.fail("articleCategoryList不是字符串列表，参数格式错误");
        }
        // 分类id列表格式转换
        List<String> articleCategoryList = (List<String>) tmp;
        // String转换为Long
        article.setCategoryId(Long.parseLong(articleCategoryList.get(articleCategoryList.size() - 1)));
        articleService.addArticle(article);
        return Result.success();
    }


}
