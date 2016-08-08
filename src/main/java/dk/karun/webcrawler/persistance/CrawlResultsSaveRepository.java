package dk.karun.webcrawler.persistance;

import dk.karun.webcrawler.CrawlSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by karun on 24 May 2016
 */
public interface CrawlResultsSaveRepository extends ElasticsearchRepository<CrawlSearchResult, String> {

}
