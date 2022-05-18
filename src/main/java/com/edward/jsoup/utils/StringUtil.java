package com.edward.jsoup.utils;

public class StringUtil {

    public static String getSubId(String url)
    {
        if(url != null && url.trim().length()!=0)
        {
            int index = url.lastIndexOf("/");
            String substring = url.substring(index + 1);
            return substring;
        }
        return "";
    }
}
