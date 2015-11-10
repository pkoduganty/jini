import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jeni.notes.Application;
import com.jeni.notes.domain.Note;
import com.jeni.notes.domain.repository.NotesCrudRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
public class NoteRepositoryTests {

	@Autowired
	NotesCrudRepository repository;

	@Test
	public void testSave() {
		Note note=new Note();
		note.setTitle("test1");
		note.setAuthor("praveen");
		note.setDescription("yatayatayata");
		
		repository.save(note);
	}

}
