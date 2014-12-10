package samples.springboot.jpa.repository;

import org.springframework.stereotype.Repository;
import samples.springboot.jpa.domain.Note;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by izeye on 14. 12. 10..
 */
@Repository
public class JpaNoteRepository implements NoteRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Note> findAll() {
    return entityManager.createQuery("SELECT n FROM Note n", Note.class).getResultList();
  }

}
