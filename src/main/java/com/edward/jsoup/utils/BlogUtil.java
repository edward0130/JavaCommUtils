package com.edward.jsoup.utils;

import com.edward.jsoup.entity.Article;
import com.edward.jsoup.entity.Picture;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * 博客数据爬取工具
 *
 */

public class BlogUtil {


    /**
     * 获取博主文章数量
     * @param blogHome
     * @return
     */
    public static int getArticleNumber(String blogHome)
    {
        Document doc= null;

        try {
            doc = Jsoup.connect(blogHome).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Element numElement = doc.select("div.user-profile-statistics-num").get(1);
        String result = numElement.text();
        System.out.println(result);
        return Integer.valueOf(result);
    }

    /**
     * 获取博主文章信息
     * @param blogHome
     * @param bId
     * @return
     */
    public static ArrayList<Article> getArticleInfo(String blogHome, String bId)
    {

        ArrayList<Article> articles = new ArrayList<>();

        Document doc = null;
        try {
            doc = Jsoup.connect(blogHome).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //获取文章列表
        Element articleList = doc.select("div.mainContent").first();

        //
        Elements artEles = articleList.select("article.blog-list-box");
        for(Element artEle: artEles)
        {
            String url = artEle.select("a").attr("href");
            String title = artEle.select("div.blog-list-box-top>h4").first().text();
            String creatTime = artEle.select("div.blog-list-footer  div.view-time-box").text();

            Article article = new Article();
            article.setUrl(url);
            article.setTitle(title);
            article.setCreateTime(creatTime);
            article.setbId(bId);
            articles.add(article);
        }
        return articles;
    }

    public static ArrayList<Picture> getArticlePicture(String articleUrl){
        ArrayList<Picture> pictures = new ArrayList<>();

        Document doc = null;
        try {
            doc = Jsoup.connect(articleUrl).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Element main = doc.select("article.baidu_pl").first();
        Elements imgs = main.select("img");
        for (Element img : imgs)
        {
            String url = img.attr("src");
            Picture pic = new Picture();
            pic.setUrl(url);
            pictures.add(pic);
        }
        return pictures;
    }
}
