package com.news.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

@RestController
@CrossOrigin("http://localhost:3000")
public class ContentListController {

	@Value("${content.url}")
	private String contentURL;

	@Value("${content.api.key}")
	private String contentApiKey;
	

	ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

	@GetMapping("/contentList")
	public String getNewsList( @RequestParam("value") String value) {

		try {
			HttpResponse<String> response = Unirest.get(contentURL).queryString("api-key", contentApiKey)
					.queryString("q", value).asString();
			String prettyResponse = objectMapper.writeValueAsString(objectMapper.readTree(response.getBody()));
			return prettyResponse;

		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
}
