package dev.uedercardoso.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1638509400294059960L;

	 private String id;
	 private String name;
	 private String description;
	 private String price;
	 private String url;
	 private String img;
	 private String parcel; 
	 
	 public Product() {
		 
	 }
	 	
}
