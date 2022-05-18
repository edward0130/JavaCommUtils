package com.edward.jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Jsoup 使用demo
 */
public class JsoupDemo {

    /**
     * jsoup 搜索数据源的三种方式
     * 1、通过url地址获取
     * 2、通过字符串信息
     * 3、通过本地文件
     */
    @Test
    public void getData()
    {
        try {
            //1.通过url获取数据
            Document document = Jsoup.connect("http://example.com").get();
            String t = document.title();
            System.out.println(t);

            //2.通过字符串获取数据
            String s = FileUtils.readFileToString(new File("E:\\jsoup.html"), "utf-8");
            Document doc = Jsoup.parse(s);
            System.out.println(doc.title());

            //3.通过文件获取数据   通过资源加载文件
            URL resource = JsoupDemo.class.getClassLoader().getResource("jsoup.html");
            String path = resource.getPath();
            Document parse = Jsoup.parse(new File(path), "utf-8");
            System.out.println(path);
            System.out.println(parse.title());
            //

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * select 选择器
     *
     * tagname : 按照标签查找元素
     * #id :  通过ID查找元素
     * .class : 通过类名查找元素
     * [attribute] : 通过属性名查找元素
     * [attr=value] : 通过属性值查找元素
     *
     */
    @Test
    public void selectFunc() throws IOException {
        //1.通过url获取数据
        Document document = Jsoup.connect("http://baidu.com").get();

        //通过tagname方式获取元素 如 <p>
        Elements elements = document.select("p");
        Element element = elements.first();
        System.out.println(element.text());
        for(Element el: elements)
        {
            System.out.println("tag:"+el.text());
        }

        //#id :  通过ID查找元素 如id="u"
        Elements ids = document.select("#u");

        for(Element id: ids){
            System.out.println("id:"+id.text());
        }
        //.class : 通过类名查找元素 如 class=login-info
        Elements clas = document.select(".login-info");
        for(Element cla : clas)
        {
            System.out.println("class:"+cla.text());
        }
        //[attribute] : 通过属性名查找元素  name="tj-settingicon"
        Elements attrs = document.select("[name]");
        for(Element attr : attrs)
        {
            System.out.println("attr:"+attr.text());
        }

        //[attr=value] : 通过属性值查找元素 name="tj-settingicon"
        Elements kvs = document.select("[name=tj_settingicon]");
        for(Element kv : kvs)
        {
            System.out.println("kv:"+kv.text());
        }
    }

    /**
     *
     * select 组合标签使用
     * el#id  元素+id
     * el.class 元素+class
     * el[attr] 元素+属性
     * el[attr].class  任意组合
     * ancestor chile  查找某个元素下子元素
     * parent > child   查找父元素下直接子元素 (元素之间不能有中间的节点)
     *
     * @throws IOException
     */

    @Test
    public void selectCombine() throws IOException {




    }

    /**
     *  select 获取元素的属性值
     *  id()    获取id值
     *  className()  获取classname
     *  attr(String str)  获取指定属性str的值
     *  attribute()   获取所有属性的值
     *  text()     获取元素的文本内容
     */
    @Test
    public void attrsTest() throws IOException {
        Document document = Jsoup.connect("http://baidu.com").get();
        Element element = document.select("#s_top_wrap").first();

        String str = "";
        str = element.id();
        System.out.println(str);

        str = element.className();
        System.out.println(str);

        String cla = element.attr("class");
        System.out.println(cla);

        Attributes attributes = element.attributes();
        System.out.println(attributes.toString());

    }

}
