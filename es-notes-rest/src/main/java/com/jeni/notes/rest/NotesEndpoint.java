package com.jeni.notes.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.jeni.notes.domain.Note;
import com.jeni.notes.domain.repository.NotesCrudRepository;

@Service
@Path("/notes")
public class NotesEndpoint {
    @Resource
    NotesCrudRepository repository;
    
    Gson gson=new Gson();
    
    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("term") String term, @QueryParam("rows") @DefaultValue("10") int rows) {
    	PageRequest page = new PageRequest(0, rows);
        return Response.ok(repository.search(term, page), MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "*").build();    	
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final String id) {
        return Response.ok(repository.findOne(id), MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByAuthor(@QueryParam("author") final String author) {
        PageRequest page = new PageRequest(0, 100);
        return Response.ok(gson.toJson(repository.findByAuthorIgnoreCase(author, page).getContent(), List.class), MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response save(final Note note) {
        return Response.ok(repository.save(note), MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "*").build();
    }

    @DELETE
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response delete(@PathParam("id") final String id) {
        repository.delete(id);
        return Response.noContent().header("Access-Control-Allow-Origin", "*").build();
    }

	public NotesCrudRepository getRepository() {
		return repository;
	}

	public void setRepository(NotesCrudRepository repository) {
		this.repository = repository;
	}
}
