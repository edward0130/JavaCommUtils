package com.edward.jsoup;

import com.edward.jsoup.entity.Article;
import com.edward.jsoup.entity.Picture;
import com.edward.jsoup.utils.BlogUtil;
import com.edward.jsoup.utils.Constants;
import com.edward.jsoup.utils.JDBCUtils;
import com.edward.jsoup.utils.StringUtil;

import java.util.ArrayList;

public class BlogMain {

    public static void main(String[] args) {

        String bloggerSql = "insert into t_blogger(id, count) values(?,?)";

        String articleSql = "insert into t_article(bid, url, createtime, title) values(?,?,?,?)";

        String pictureSql = "insert into t_picture(aid, url) values(?,?)";

        int num = BlogUtil.getArticleNumber(Constants.BLOG_HOME);

        JDBCUtils.executeUpdateWithID(bloggerSql, StringUtil.getSubId(Constants.BLOG_HOME), num);

        ArrayList<Article> articleInfo = BlogUtil.getArticleInfo(Constants.BLOG_HOME, StringUtil.getSubId(Constants.BLOG_HOME));
        for(Article article : articleInfo)
        {
            System.out.println(article.toString());

            Integer id = JDBCUtils.executeUpdateWithID(articleSql, StringUtil.getSubId(Constants.BLOG_HOME), article.getUrl(), article.getCreateTime(), article.getTitle());

            ArrayList<Picture> articlePicture = BlogUtil.getArticlePicture(article.getUrl());
            for(Picture pic : articlePicture)
            {
                System.out.println(pic.toString());
                JDBCUtils.executeUpdateWithID(pictureSql, id, pic.getUrl());
            }
        }
    }
}
