package samples.springboot.jpa.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by izeye on 14. 12. 10..
 */
@Entity
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String name;

  @ManyToMany(mappedBy = "tags")
  private List<Note> notes;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Note> getNotes() {
    return notes;
  }

  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  @Override
  public String toString() {
    return "Tag{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", notes=" + notes +
        '}';
  }

}
