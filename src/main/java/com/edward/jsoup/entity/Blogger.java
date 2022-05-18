package com.edward.jsoup.entity;


/**
 *
 * 博主实体类
 *
 * CREATE TABLE `DBTest`.`t_blogger`  (
 *   `id` varchar(100) NULL ,
 * 	`count` int	not null
 * );
 *
 */
public class Blogger {

    //博主ID
    private String id;
    //博客文章数量
    private Integer articleCount;
    //博客文章总页面数量
    private Integer pageCount;

    public Blogger() {
    }

    public Blogger(String id, Integer articleCount, Integer pageCount) {
        this.id = id;
        this.articleCount = articleCount;
        this.pageCount = pageCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Blogger{" +
                "id='" + id + '\'' +
                ", articleCount=" + articleCount +
                ", pageCount=" + pageCount +
                '}';
    }
}
