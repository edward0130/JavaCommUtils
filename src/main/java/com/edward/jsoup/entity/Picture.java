package com.edward.jsoup.entity;

/**
 * 图片类
 *
 * CREATE TABLE `DBTest`.`t_picture`  (
 *   `id` tinyint(255) NOT NULL AUTO_INCREMENT,
 * 	`aid` tinyint(255) NOT NULL,
 *   `url` varchar(500) NULL,
 * 	PRIMARY KEY (`id`)
 * );
 */
public class Picture {

    //图片ID
    private Integer id;
    //文章ID
    private Integer aId;
    //文章url
    private String url;

    public Picture() {
    }

    public Picture(Integer id, Integer aId, String url) {
        this.id = id;
        this.aId = aId;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", aId=" + aId +
                ", url='" + url + '\'' +
                '}';
    }
}
