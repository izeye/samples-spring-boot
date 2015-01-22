package samples.springboot.gs.caching;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import samples.springboot.gs.caching.Application;
import samples.springboot.gs.caching.Book;
import samples.springboot.gs.caching.BookRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;

/**
 * Created by izeye on 14. 12. 23..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CachingTests {

	@Autowired
	BookRepository bookRepository;

	@Test
	public void testCacheable() {
		String isbn = "1234";
		String anotherIsbn = "1111";

		Book book = bookRepository.getByIsbn(isbn);
		assertThat(bookRepository.getByIsbn(isbn), is(sameInstance(book)));
		assertThat(bookRepository.getByIsbn(anotherIsbn), is(not(sameInstance(book))));
	}

	@Test
	public void testCacheEvict() {
		String isbn = "1234";
		String anotherIsbn = "1111";

		Book book = bookRepository.getByIsbn(isbn);
		Book anotherBook = bookRepository.getByIsbn(anotherIsbn);

		assertThat(bookRepository.getByIsbn(isbn), is(sameInstance(book)));
		assertThat(bookRepository.getByIsbn(anotherIsbn), is(sameInstance(anotherBook)));

		bookRepository.save(book);
		assertThat(bookRepository.getByIsbn(isbn), is(not(sameInstance(book))));
		assertThat(bookRepository.getByIsbn(anotherIsbn), is(sameInstance(anotherBook)));
	}

	@Test
	public void testWithNullKey() {
		bookRepository.save(new Book());
	}

}
