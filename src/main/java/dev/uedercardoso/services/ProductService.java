package dev.uedercardoso.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import dev.uedercardoso.models.Product;

@Service
public class ProductService {

	public void writeXmlFile(List<Product> products, String path) {
		
		XStream xStream = new XStream(new DomDriver());
		xStream.alias("products", List.class);
		
		File file = new File(path + "/products.xml");
		FileOutputStream write;
		
		try {
			write = new FileOutputStream(file);
			write.write(xStream.toXML(products).getBytes());
			write.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
