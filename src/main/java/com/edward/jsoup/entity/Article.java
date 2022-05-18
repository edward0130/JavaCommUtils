package com.edward.jsoup.entity;


/**
 *
 * 文章实体类
 *
 *
 *
 * CREATE TABLE `DBTest`.`t_article`  (
 *   `id` tinyint(255) NOT NULL AUTO_INCREMENT,
 * 	`bid` varchar(100) NULL,
 *   `url` varchar(200) NULL,
 *   `createtime` varchar(200) NULL,
 *   `title` varchar(200) NULL,
 * 	PRIMARY KEY (`id`)
 * );
 *
 */

public class Article {

    //文章ID
    private Integer id;
    //博主ID
    private String bId;
    //文章url
    private String url;
    //文章创建时间
    private String createTime;
    //文章标题
    private String title;

    public Article() {
    }

    public Article(Integer id, String bId, String url, String createTime, String title) {
        this.id = id;
        this.bId = bId;
        this.url = url;
        this.createTime = createTime;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", bId=" + bId +
                ", url='" + url + '\'' +
                ", createTime='" + createTime + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
