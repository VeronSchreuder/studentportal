package com.example.veronschreuder.studentportal;

import java.io.Serializable;

public class Portal implements Serializable {
    String title;
    String url;

    public Portal(String pTitle, String pUrl) {
        title = pTitle;
        url = pUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String pTitle) {
        title = pTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String pUrl) {
        url = pUrl;
    }
}
