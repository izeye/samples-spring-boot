package samples.springboot.jpa.repository;

import samples.springboot.jpa.domain.Note;

import java.util.List;

/**
 * Created by izeye on 14. 12. 10..
 */
public interface NoteRepository {

  List<Note> findAll();

}
