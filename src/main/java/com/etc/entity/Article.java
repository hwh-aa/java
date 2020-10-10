package com.etc.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Integer articleId;
    @Column(name = "article_title")
    private String articleTitle;
    @Column(name = "article_content")
    private String articleContent;
    @Column(name = "article_dt")
    private String articleDt;
    @Column(name = "author_id")
    private Integer authorId;
}
