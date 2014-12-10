package samples.springboot.jpa.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import samples.springboot.jpa.SampleJpaApplication;
import samples.springboot.jpa.domain.Note;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by izeye on 14. 12. 10..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleJpaApplication.class)
public class JpaNoteRepositoryIntegrationTests {

  @Autowired
  JpaNoteRepository repository;

  @Test
  public void findsAllNotes() {
    List<Note> notes = repository.findAll();
    assertEquals(4, notes.size());
  }

}
