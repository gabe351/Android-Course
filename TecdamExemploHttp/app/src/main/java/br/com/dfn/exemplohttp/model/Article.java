package br.com.dfn.exemplohttp.model;

public class Article {
    public String author;
    public String description;
    public String title;
    public String urlToImage;

    public Article(String author,
                   String description,
                   String title,
                   String urlToImage) {
        this.author = author;
        this.description = description;
        this.title = title;
        this.urlToImage = urlToImage;
    }
}
