package com.jeni.notes.rest;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
@Path("/dict")
public class DictionaryEndpoint {
    Gson gson=new Gson();
	Client restClient = ClientBuilder.newClient();
	
	@Value("${wikipedia.search.api.url}")
	private String wikipediaSearchApiUrl;
	
	@Value("${wikipedia.search.page.size}")
	private int wikipediaSearchPageSize;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWikipediaEntry(@QueryParam("term") final String term) {
		List response = restClient
				.target(wikipediaSearchApiUrl)
				.queryParam("limit", wikipediaSearchPageSize)
				.queryParam("search", term).request(MediaType.APPLICATION_JSON)
				.get(List.class);

    	if(null!=response && response.size()==4) {
    		Map<String,String> definitionsMap=new TreeMap<String,String>();
    		List<String> terms=(List<String>) response.get(1);
    		List<String> definitions=(List<String>) response.get(2);
    		
    		for(int i=0;i<terms.size();i++)
    			definitionsMap.put(terms.get(i), definitions.get(i));
    		return Response.ok(gson.toJson(definitionsMap),MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
    	}
    	else
    		return Response.noContent().build();
    }
}
