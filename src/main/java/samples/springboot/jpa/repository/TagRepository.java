package samples.springboot.jpa.repository;

import samples.springboot.jpa.domain.Tag;

import java.util.List;

/**
 * Created by izeye on 14. 12. 10..
 */
public interface TagRepository {

  List<Tag> findAll();

}
