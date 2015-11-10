package com.jeni.notes;

import java.util.Arrays;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import com.jeni.notes.domain.Note;
import com.jeni.notes.domain.repository.NotesCrudRepository;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Autowired
	ElasticsearchOperations operations;
	@Autowired
	NotesCrudRepository repository;

	public static void main(final String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
		return new TomcatEmbeddedServletContainerFactory(8090);
	}

	@Bean
	public FilterRegistrationBean jersey() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new ServletContainer());
		bean.addInitParameter("jersey.config.server.provider.packages",
				"com.sun.jersey;com.jeni.notes");
		return bean;
	}
	
	@PostConstruct
	public void insertSampleNotes() {

		// Remove all documents
		repository.deleteAll();
		operations.refresh(Note.class, true);

		Note note=new Note();
		note.setAuthor("praveen");
		note.setTitle("elasticsearch - repository for notes");
		note.setDescription("setup elasticsearch as notes store");
		note.setCreatedOn(new Date());
		note.setSubject(Arrays.asList(new String[]{"search-engine","document-oriented","nosql"}));
		// Save data sample
		repository.save(note);
	}
}