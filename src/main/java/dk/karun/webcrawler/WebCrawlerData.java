package dk.karun.webcrawler;

import java.util.List;

/**
 * Created by karun on 24 May 2016
 */
public class WebCrawlerData {
    private String url;
    private int depth;
    private List<String> keywords;


    private WebCrawlerData() {
    }

    public WebCrawlerData(String url, int depth, List<String> keywords) {
        this.url = url;
        this.depth = depth;
        this.keywords = keywords;
    }

    public String getUrl() {
        return url;
    }

    public int getDepth() {
        return depth;
    }

    public List<String> getKeywords() {
        return keywords;
    }
}
