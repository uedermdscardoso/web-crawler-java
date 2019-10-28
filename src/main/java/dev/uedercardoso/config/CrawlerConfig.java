package dev.uedercardoso.config;

import dev.uedercardoso.crawler.CrawlerWeb;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public abstract class CrawlerConfig {

	 public static void executeCrawler() throws Exception {
		 
		 String crawlStorageFolder = Parameters.DIRECTORY;
		 int numberOfCrawlers = 7;

		 CrawlConfig config = new CrawlConfig();
		 config.setCrawlStorageFolder(crawlStorageFolder);
    
		 PageFetcher pageFetcher = new PageFetcher(config);
		 RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		 RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		 CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

		 controller.addSeed(Parameters.URL_SITE);
	
		 CrawlController.WebCrawlerFactory<CrawlerWeb> factory = CrawlerWeb::new;
    
		 controller.start(factory, numberOfCrawlers);
	 
	 }
	
}
