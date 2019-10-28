package dev.uedercardoso.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.uedercardoso.config.CrawlerConfig;

@RestController
@RequestMapping("/crawler")
public class CrawlerController {

	@GetMapping
	public ResponseEntity<Void> crawler(){
		
		try {
			
			CrawlerConfig.executeCrawler();
			
			return ResponseEntity.ok().build();
			
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
				
	}
}
