package com.example.newsaggregator.helpers;

import android.text.Html;
import android.text.Spanned;

public class TextChanger {

    public static String fixText(String text){
        text = Html.fromHtml(text).toString();
        text = text.replace("[\\s]+", " ");
        return text;
    }

    public static Spanned addLinkToContent(String sourceLink){
        String link = "<a href=\"" + sourceLink + "\"> В источнике</a>";
        Spanned spanned = Html.fromHtml(sourceLink, null, null);
        return spanned;
    }

    public static String fixDate(String date){
        //Сделаю потом
        return null;
    }
}
