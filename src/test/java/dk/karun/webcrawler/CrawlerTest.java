package dk.karun.webcrawler;

import dk.karun.webcrawler.persistance.CrawlResultsSaveRepository;
import org.elasticsearch.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;

@IntegrationTest
public class CrawlerTest {

    @Autowired
    CrawlResultsSaveRepository repository;

    @Test
    public void crawl() {
        final CrawlTask spider = new CrawlTask(new WebCrawlerData("http://arstechnica.com/", 1, Lists.newArrayList("computer")), repository);
        spider.run();
    }
}
