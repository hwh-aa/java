package com.etc.service.impl;

import com.etc.dao.ArticleDao;
import com.etc.entity.Article;
import com.etc.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;

    @Override
    public Article getById(Integer articleId) {
        Optional<Article> optional = articleDao.findById(articleId);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
