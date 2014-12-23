package samples.springboot.gs.caching;

/**
 * Created by izeye on 14. 12. 23..
 */
public interface BookRepository {

	Book getByIsbn(String isbn);

	void update(Book book);

}
