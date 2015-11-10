package com.jeni.notes.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "notes-index", shards = 1, replicas = 0, indexStoreType = "memory", refreshInterval = "-1")
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Field
	private String title;

	@Field
	private String description;

	@Field
	private List<String> subject = new ArrayList<String>();

	@Field
	private String author;

	@Field
	private Date createdOn=new Date();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getSubject() {
		return subject;
	}

	public void setSubject(List<String> subject) {
		this.subject = subject;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", description="
				+ description + ", subject=" + subject + ", createdOn="
				+ createdOn + "]";
	}
}
