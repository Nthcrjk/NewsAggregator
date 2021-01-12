package com.example.newsaggregator.helpers;

import android.text.Html;
import android.text.Spanned;

public class TextChanger {

    public static String fixText(String text){
        text = Html.fromHtml(text).toString();
        text = text.replace("[\\s]+", " ");
        return text;
    }

    public static String fixContent(String content){
        content = content.replaceAll("\\[[+]\\d+\\schars\\]", "");
        String link = "<a href=\"sourceLink\"> В источнике</a>";
        Spanned newLink = Html.fromHtml(link, null, null);
        return content;
    }

    public static String fixDate(String date){
        //Сделаю потом
        return null;
    }
}
