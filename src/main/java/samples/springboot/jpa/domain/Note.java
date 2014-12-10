package samples.springboot.jpa.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by izeye on 14. 12. 10..
 */
@Entity
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String title;

  private String body;

  @ManyToMany
  private List<Tag> tags;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  @Override
  public String toString() {
    return "Note{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", body='" + body + '\'' +
        ", tags=" + tags +
        '}';
  }

}
