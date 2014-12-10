package samples.springboot.jpa.repository;

import org.springframework.stereotype.Repository;
import samples.springboot.jpa.domain.Tag;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by izeye on 14. 12. 10..
 */
@Repository
public class JpaTagRepository implements TagRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Tag> findAll() {
    return entityManager.createQuery("SELECT t FROM Tag t", Tag.class).getResultList();
  }

}
