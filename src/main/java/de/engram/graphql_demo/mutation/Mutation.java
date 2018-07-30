package de.engram.graphql_demo.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.engram.graphql_demo.entity.Author;
import de.engram.graphql_demo.entity.Book;
import de.engram.graphql_demo.exception.EntityNotFoundException;
import de.engram.graphql_demo.repository.AuthorRepository;
import de.engram.graphql_demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Component
public class Mutation implements GraphQLMutationResolver {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	public Author newAuthor(String firstName, String lastName) {
		Author author = new Author(firstName, lastName);
		return authorRepository.save(author);
	}

	public Book newBook(String title, String isbn, Integer pageCount, List<Long> authorIds) {
		List<Author> authors = authorRepository.findByIds(authorIds);

		Book book = new Book(title, isbn, pageCount != null ? pageCount : 0, authors);
		return bookRepository.save(book);
	}

	public boolean deleteBook(Long id) {
		bookRepository.deleteById(id);
		return true;
	}

	public Book updateBookPageCount(Integer pageCount, Long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("The book to be updated could not be found", bookId));
		book.setPageCount(pageCount);
		bookRepository.save(book);
		return book;
	}
}