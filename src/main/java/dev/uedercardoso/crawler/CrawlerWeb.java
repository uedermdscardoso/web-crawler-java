package dev.uedercardoso.crawler;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.http.Header;

import dev.uedercardoso.config.Parameters;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

//I study crawler
public class CrawlerWeb extends WebCrawler {

	 //https://wandersonalvesdev.wordpress.com/2013/04/20/crawler4j-web-robot-em-java/
	 //https://github.com/yasserg/crawler4j
	
	 private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" + "|png|tiff?|mid|mp2|mp3|mp4"
			 + "|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
			 
	 /* Voc� deve implementar esta fun��o para especificar se o dado url deve ser rastreado ou n�o (com base na sua l�gica de rastreamento). */
	 @Override
	 public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
	 	return !FILTERS.matcher(href).matches() && href.startsWith(Parameters.URL_SITE);
	 }
	 
	 /* Esta fun��o � chamada quando uma p�gina � buscado e pronto para ser processado pelo seu programa. */
	 @Override
	 public void visit(Page page) {
		 int docid = page.getWebURL().getDocid();
		 
		 String url = page.getWebURL().getURL();
		 String domain = page.getWebURL().getDomain();
		 String path = page.getWebURL().getPath();
		 String subDomain = page.getWebURL().getSubDomain();
		 String parentUrl = page.getWebURL().getParentUrl();
		 String anchor = page.getWebURL().getAnchor();
		 
		 System.out.println("Docid: " + docid);
		 System.out.println("URL: " + url);
		 System.out.println("Domain: '" + domain + "'");
		 System.out.println("Sub-domain: '" + subDomain + "'");
		 System.out.println("Path: '" + path + "'");
		 System.out.println("Parent page: " + parentUrl);
		 System.out.println("Anchor text: " + anchor);
		 
		 if (page.getParseData() instanceof HtmlParseData) {
		 
			 HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			 String text = htmlParseData.getText();
			 String html = htmlParseData.getHtml();
		 
			 Set<WebURL> links = htmlParseData.getOutgoingUrls();
			 System.out.println("Text length: " + text.length());
			 System.out.println("Html length: " + html.length());
			 System.out.println("Number of outgoing links: " + links.size());
			 new HTMLJsoup(html);
		 }
		 
		 Header[] responseHeaders = page.getFetchResponseHeaders();
		 
		 if (responseHeaders != null) {
			 System.out.println("Response headers:");
			 
			 for (Header header : responseHeaders) {
				 System.out.println("\t" + header.getName() + ": " + header.getValue());
			 }
		 }
		 
		 System.out.println("=============");
	}
	
}
