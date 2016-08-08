package dk.karun.webcrawler;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.Map;

/**
 * Created by karun on 24 May 2016
 */
@Document(indexName = "crawl-search-results", type = "crawl-search-result", shards = 1, replicas = 0, refreshInterval = "-1")
public class CrawlSearchResult {
    @Id
    private String id;
    private String url;
    private List<String> links;
    private String keyword;
    private Map<String,String> headers;

    private CrawlSearchResult() {
    }


    public CrawlSearchResult(String id, String url, List<String> links, String keyword, Map<String, String> headers) {
        this.id = id;
        this.url = url;
        this.links = links;
        this.keyword = keyword;
        this.headers = headers;
    }


    public String getUrl() {
        return url;
    }

    public List<String> getLinks() {
        return links;
    }

    public String getKeyword() {
        return keyword;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CrawlSearchResult{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", links=" + links +
                ", keyword='" + keyword + '\'' +
                ", headers=" + headers +
                '}';
    }
}
