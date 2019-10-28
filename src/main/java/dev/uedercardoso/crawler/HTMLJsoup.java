package dev.uedercardoso.crawler;

import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import dev.uedercardoso.config.Cache;
import dev.uedercardoso.config.Parameters;
import dev.uedercardoso.models.Product;

public class HTMLJsoup {

	 public HTMLJsoup(String html) {
		 if (Cache.productsCache == null) {
			 Cache.productsCache = new LinkedList<Product>();
		 }
		 
		 try {
			 Document doc = Jsoup.parse(html);
//			 Elements body = doc.body().children();
			 Elements product = doc.select(Parameters.TAG_PRODUCT_TITLE);
			 Elements price = doc.select(Parameters.TAG_PRICE);
			 Elements url = doc.select(Parameters.TAG_URL);
			 Elements image = doc.select(Parameters.TAG_IMAGE);
			 Elements parcel = doc.select(Parameters.TAG_PARCEL);
			 
			 for (int i = 0; i < product.size(); i++) {
				 Product productObj = new Product();

				 if (i < (product.size() - 1))
					 productObj.setName(product.get(i).val());
				 else
					 productObj.setName("");
				 
				 productObj.setDescription(product.get(i).text());
				 
				 if (i < (image.size() - 1))
					 productObj.setImg(image.get(i).attr("abs:src"));
				 else
					 productObj.setImg("");
				 
				 if (i < (price.size() - 1))
					 productObj.setPrice(price.get(i).text());
				 else
					 productObj.setPrice("");
				 
				 if(i < (url.size() - 1))
					 productObj.setUrl(price.get(i).text());
				 else
					 productObj.setUrl("");
				 
				 if(i < (url.size() - 1)) 
					 productObj.setParcel(parcel.get(i).text());
				 else 
					 productObj.setParcel("");
				 
				 if (!productObj.getName().isEmpty() && !productObj.getDescription().isEmpty() && !productObj.getPrice().isEmpty())	 
					 Cache.productsCache.add(productObj);
				
			 }
		 } catch (Exception error) {
			 error.printStackTrace();
		 }
	 }	 
	
}
