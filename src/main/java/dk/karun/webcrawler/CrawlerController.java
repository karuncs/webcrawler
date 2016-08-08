package dk.karun.webcrawler;

import dk.karun.webcrawler.persistance.CrawlResultsSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by karun on 24 May 2016
 */
@RestController
@RequestMapping("/crawl")
public class CrawlerController {
    @Autowired private ThreadPoolTaskExecutor taskExecutor;
    @Autowired private CrawlResultsSaveRepository repository;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> crawl(@RequestBody final WebCrawlerData webCrawlerData) {
        taskExecutor.execute(new CrawlTask(webCrawlerData,repository));
        return new ResponseEntity<String>("Request Accepted", HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String status() {
        return "Crawl Server is Up and Running";
    }





}
