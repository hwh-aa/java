package com.etc.controller;

import com.etc.entity.Article;
import com.etc.feigninters.UserFeignClient;
import com.etc.service.ArticleService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private UserFeignClient userFeignClient;

    //断路器的设置
    @HystrixCommand(fallbackMethod = "getDefaultUser")
    //从文章微服务访问用户微服务
    @RequestMapping("/getuser/{uid}")
    public Map<String,Object> getUser(@PathVariable Integer uid) {
        Map<String,Object> map = restTemplate.getForObject("http://localhost:8762/user/get/"+uid,Map.class);
        return map;
    }

    private Map<String,Object> getDefaultUser(Integer uid) {
        Map<String,Object> map = new HashMap<>();
        map.put("uname","-1");
        map.put("password","-1");
        return map;
    }
    //使用feign调用用户的微服务
    @RequestMapping("/getdetailbyfeign/{articleId}")
    public Map<String,Object> getArticleDetailByFeign(@PathVariable Integer articleId, HttpSession session) {
        Object user = session.getAttribute("user");
        System.out.println("从springcloudzuul中取："+user);
        Article a = articleService.getById(articleId);
        //调用feign接口中定义的方法
        Map<String,Object> map = userFeignClient.getUserById(a.getAuthorId());
        map.put("articleId",a.getArticleId());
        map.put("articleTitle",a.getArticleTitle());
        map.put("articleContent",a.getArticleContent());
        map.put("articleDt",a.getArticleDt());
        return map;
    }

 /*   //从文章微服务访问用户微服务
    @RequestMapping("/getuser/{uid}")
    public Map<String,Object> getUser(@PathVariable Integer uid){
        Map<String,Object> map = restTemplate.getForObject("http://localhost:8762/user/get/"+uid,Map.class);
        return map;
    }
*/
    @RequestMapping("/get/{articleId}")
    public Article getArticle(@PathVariable Integer articleId){
        return articleService.getById(articleId);
    }

    @RequestMapping("/getdetail/{articleId}")
    public Map<String,Object> getArticleDetail(@PathVariable Integer articleId){
        Article a = articleService.getById(articleId);
        Map<String,Object> map = new HashMap<>();
        if(a.getAuthorId() != null){//文章作者不为空
            map = restTemplate.getForObject("http://localhost:8762/user/get/"+a.getAuthorId(),Map.class);
        }
        if(a != null){//把文章中的属性设置到map中
            map.put("articleId",a.getArticleId());
            map.put("articleTitle",a.getArticleTitle());
            map.put("articleContent",a.getArticleContent());
            map.put("articleDt",a.getArticleDt());
        }
        return map;
    }
}
