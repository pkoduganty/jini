package com.jeni.notes.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jeni.notes.domain.Note;


/** @author Praveenk */
public interface NotesCrudRepository extends ElasticsearchRepository<Note, String> {

	Page<Note> findByAuthorIgnoreCase(String author, Pageable page);

}
