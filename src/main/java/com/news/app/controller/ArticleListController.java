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
public class ArticleListController {

	@Value("${article.url}")
	private String articleURL;

	@Value("${article.api.key}")
	private String articleApiKey;
	

	ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

	@GetMapping("/articleList")
	public String getNewsList( @RequestParam("value") String value) {

		try {
			HttpResponse<String> response = Unirest.get(articleURL).queryString("api-key", articleApiKey)
					.queryString("q", value).asString();
			String prettyResponse = objectMapper.writeValueAsString(objectMapper.readTree(response.getBody()));
			return prettyResponse;

		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
}
