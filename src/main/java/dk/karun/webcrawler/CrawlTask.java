package dk.karun.webcrawler;

import dk.karun.webcrawler.persistance.CrawlResultsSaveRepository;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.io.IOException;
import java.util.*;

import static dk.karun.webcrawler.G.CRAWL_SEARCH_RESULTS_INDEX_NAME;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by karun on 24 May 2016
 */
public class CrawlTask implements Runnable {

    final Set<String> pagesAlreadyVisited = new HashSet<String>();
    final LinkedList<String> pagesToBeVisited = new LinkedList<String>();
    private final Logger logger = LoggerFactory.getLogger(CrawlerCollection.class);
    private WebCrawlerData webCrawlerData;
    private CrawlResultsSaveRepository repository;

    private CrawlTask() {
    }


    public CrawlTask(WebCrawlerData webCrawlerData, CrawlResultsSaveRepository repository) {
        this.webCrawlerData = webCrawlerData;
        this.repository = repository;
    }

    private String nextUrl() {
        String nextUrl;
        do {
            nextUrl = this.pagesToBeVisited.remove(0);
        } while (this.pagesAlreadyVisited.contains(nextUrl));
        this.pagesAlreadyVisited.add(nextUrl);
        return nextUrl;
    }

    private void searchForGivenWord(String url, List<String> keywords, int depth) {

        while (this.pagesAlreadyVisited.size() < depth) {
            String currentUrl;

            final CrawlerCollection collection = new CrawlerCollection();
            if (this.pagesToBeVisited.isEmpty()) {
                currentUrl = url;
                this.pagesAlreadyVisited.add(url);
            } else {
                currentUrl = this.nextUrl();
            }

            collection.crawl(currentUrl);
            // SpiderLeg

            for (String keyword : keywords) {
                boolean success = collection.searchForWord(keyword);
                if (success) {
                    logger.info(String.format("**Success** Word '%s' found at %s", keyword, currentUrl));
                    //Add documents
                    repository.save(new CrawlSearchResult(UUID.randomUUID().toString(),url, collection.getLinks(), keyword, collection.getHeaders()));
                    break;
                }
            }
            this.pagesToBeVisited.addAll(collection.getLinks());
        }


        logger.info("the pages are visited " + this.pagesAlreadyVisited.size() + " web pages");
    }

    @Override
    public void run() {
        searchForGivenWord(webCrawlerData.getUrl(), webCrawlerData.getKeywords(), webCrawlerData.getDepth());
    }
}

