package com.etc.dao;

import com.etc.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface ArticleDao extends JpaRepository<Article,Integer>, JpaSpecificationExecutor<Article>, Serializable {
}
