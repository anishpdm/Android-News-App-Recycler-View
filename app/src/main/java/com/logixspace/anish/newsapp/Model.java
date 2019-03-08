package com.logixspace.anish.newsapp;

public class Model {
    String newsimage, times;


    String nesatitle, newsdesc;


    public Model(String newsimage, String nesatitle, String newsdesc, String times) {
        this.newsimage = newsimage;
        this.nesatitle = nesatitle;
        this.newsdesc = newsdesc;
        this.times = times;
    }

    public String getNewsimage() {
        return newsimage;
    }

    public void setNewsimage(String newsimage) {
        this.newsimage = newsimage;
    }

    public String getNesatitle() {
        return nesatitle;
    }

    public void setNesatitle(String nesatitle) {
        this.nesatitle = nesatitle;
    }

    public String getNewsdesc() {
        return newsdesc;
    }

    public void setNewsdesc(String newsdesc) {
        this.newsdesc = newsdesc;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}





